package com.shinysideup.udatm.lib;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shinysideup.udatm.lib.dao.DeviceOrThingDAO;
import com.shinysideup.udatm.lib.io.IOProtocol;
import com.shinysideup.udatm.lib.io.IOProtocolDescriptor;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionCommandDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails.ScriptProcessorType;
import com.shinysideup.udatm.lib.pojo.Parameter;
import com.shinysideup.udatm.lib.util.DATUtil;
import com.shinysideup.udatm.lib.util.GroovyScriptProcessor;
import com.shinysideup.udatm.lib.util.ScriptProcessor;

/**
 * @author Mike Worley
 */
public class DeviceOrThing {

	private static final Log logger = LogFactory.getLog(DeviceOrThing.class);
	private final DeviceOrThingDAO datDAO;
	private final Long dotId;
	private final ArrayList<IOProtocol> iops = new ArrayList<>();
	private final Hashtable<String, ScriptProcessor> scriptProcessors = new Hashtable<>();

	ExecutorService executor = Executors.newFixedThreadPool(10);

	public DeviceOrThing(DeviceOrThingDAO datDAO) throws NoDataFoundException, DeviceOrThingException {
		if (datDAO == null)
			throw new NoDataFoundException("DeviceOrThingDAO is null");
		this.datDAO = datDAO;
		this.dotId = datDAO.getDOTDetails().getId();
		instantiateIOProtocols();
	}

	public Handle runDOTScript(DOTModelFirmwareVersionScriptDetails scriptDetails)
			throws NoDataFoundException, DeviceOrThingException {
		synchronized (iops) {
			String script = this.datDAO.getDOTModelFirmwareVersionScript(scriptDetails.getId());
			Handle handle = new Handle();
			handle.setDotId(this.dotId);
			handle.setHandleUUID(DATUtil.generateUUID());

			Hashtable<String, Object> params = new Hashtable<>();
			for (Parameter param : scriptDetails.getParamaters()) {
				params.put(param.getName(), param.getValue());
			}
			ScriptProcessor scriptProcessor;
			if (scriptDetails.getScriptProcessorType().equals(ScriptProcessorType.Groovy)) {
				scriptProcessor = new GroovyScriptProcessor(script, params);
			} else {
				throw new NoDataFoundException(scriptDetails.getScriptProcessorType() + " : is not supported");
			}
			scriptProcessors.put(handle.getHandleUUID(), scriptProcessor);
			executor.execute(scriptProcessor);
			return handle;
		}
	}

	public Handle runDOTCommand(DOTModelFirmwareVersionCommandDetails commandDetails)
			throws NoDataFoundException, DeviceOrThingException {
		synchronized (iops) {
			// TODO
			return null;
		}
	}

	public Result getResult(Handle handle) throws NoDataFoundException, DeviceOrThingException {
		synchronized (iops) {
			ScriptProcessor scriptProcessor = this.scriptProcessors.get(handle.getHandleUUID());
			if (scriptProcessor == null)
				throw new NoDataFoundException("Result was not found");
			return scriptProcessor.getCurrentResult();
		}
	}

	public void disconnectConnections() {
		synchronized (iops) {
			for (IOProtocol iop : iops) {
				try {
					iop.reset();
				} catch (Exception e) {
					logger.error("Failed to disconnect " + iop.getIOProtocolDescriptor(), e);
				}
			}
		}
	}

	public void shutdown() {
		synchronized (iops) {
			this.disconnectConnections();
			this.iops.clear();
		}
	}

	public void update() throws NoDataFoundException, DeviceOrThingException {
		synchronized (iops) {
			this.disconnectConnections();
			this.iops.clear();
			instantiateIOProtocols();
		}
	}

	private void instantiateIOProtocols() throws NoDataFoundException, DeviceOrThingException {
		synchronized (iops) {
			if (this.datDAO.getDOTContent() == null)
				throw new NoDataFoundException("DOTContent is null");
			List<IOProtocolDescriptor> iopds = this.datDAO.getDOTContent().getIoProtocolDescriptors();
			if (iopds == null)
				return;
			for (IOProtocolDescriptor iopd : iopds) {
				instantiateIOProtocol(iopd);
			}
		}
	}

	private void instantiateIOProtocol(IOProtocolDescriptor iopd) throws DeviceOrThingException {
		synchronized (iops) {
			try {
				Class<?> clazz = Class.forName(iopd.getIOProtocolClass());
				Constructor<?> constructor = clazz.getConstructor(iopd.getClass());
				Object[] params = { iopd };
				IOProtocol iop = (IOProtocol) constructor.newInstance(params);
				this.iops.add(iop);
			} catch (Exception e) {
				logger.warn("Failed to instantiate " + iopd.getType() + " : " + e.getLocalizedMessage());
			}
		}
	}
}
