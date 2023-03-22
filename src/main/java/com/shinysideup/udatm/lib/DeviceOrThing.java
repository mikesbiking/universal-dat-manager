package com.shinysideup.udatm.lib;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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
	// ?? These only get cleared when a getResult show it's complete. Could have
	// issues if getResult is never called. Might need a clean up action thread??
	private final Map<String, ScriptProcessor> scriptProcessors = new Hashtable<>();
	private final ExecutorService executor = Executors.newFixedThreadPool(10);

	public DeviceOrThing(DeviceOrThingDAO datDAO) throws NoDataFoundException, DeviceOrThingException {
		if (datDAO == null)
			throw new NoDataFoundException("DeviceOrThingDAO is null");
		this.datDAO = datDAO;
		this.dotId = datDAO.getDOTDetails().getId();
	}

	public Handle runDOTScript(DOTModelFirmwareVersionScriptDetails scriptDetails)
			throws NoDataFoundException, DeviceOrThingException {

		String script = this.datDAO.getDOTModelFirmwareVersionScriptContent(scriptDetails.getId()).getScript();
		Handle handle = new Handle();
		handle.setDotId(this.dotId);
		handle.setHandleUUID(DATUtil.generateUUID());
		List<Parameter> parameters = scriptDetails.getParamaters();
		Map<String, Object> params = new Hashtable<>();
		if (parameters != null) {
			for (Parameter param : parameters) {
				params.put(param.getName(), param.getValue());
			}
		}
		DeviceOrThingScriptAccess access = new DeviceOrThingScriptAccess(this.datDAO, getIOProtocols());
		params.put(DeviceOrThingScriptAccess.SCRIPT_ACCESS_NAME_KEY, access);
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

	public String runDOTCommand(DOTModelFirmwareVersionCommandDetails commandDetails)
			throws NoDataFoundException, DeviceOrThingException {
		DeviceOrThingScriptAccess access = new DeviceOrThingScriptAccess(this.datDAO, getIOProtocols());
		try {
			List<Parameter> parameters = commandDetails.getParameters();
			Map<String, Object> params = new Hashtable<>();
			if (parameters != null) {
				for (Parameter param : parameters) {
					params.put(param.getName(), param.getValue());
				}
			}
			return access.runCommand(commandDetails.getName(), params);
		} finally {
			resetIOPs(access.getIOProtocols());
		}
	}

	public Result getResult(Handle handle) throws NoDataFoundException, DeviceOrThingException {
		ScriptProcessor scriptProcessor = this.scriptProcessors.get(handle.getHandleUUID());
		if (scriptProcessor == null)
			throw new NoDataFoundException("Result was not found");
		Result result = scriptProcessor.getCurrentResult();
		if (result.isComplete()) {
			this.scriptProcessors.remove(handle.getHandleUUID());
			resetIOProtocols(scriptProcessor);
		}
		return result;
	}

	public void shutdown() {
		for (String key : this.scriptProcessors.keySet()) {
			this.resetIOProtocols(this.scriptProcessors.remove(key));
		}
		this.executor.shutdown();
	}

	private List<IOProtocol> getIOProtocols() throws NoDataFoundException, DeviceOrThingException {
		if (this.datDAO.getDOTContent() == null)
			throw new NoDataFoundException("DOTContent is null");
		List<IOProtocolDescriptor> iopds = this.datDAO.getDOTContent().getIoProtocolDescriptors();
		List<IOProtocol> iops = new ArrayList<>();
		if (iopds != null) {
			for (IOProtocolDescriptor iopd : iopds) {
				iops.add(getIOProtocol(iopd));
			}
		}
		return iops;
	}

	private IOProtocol getIOProtocol(IOProtocolDescriptor iopd) throws DeviceOrThingException {
		try {
			Class<?> clazz = Class.forName(iopd.getIOProtocolClass());
			Constructor<?> constructor = clazz.getConstructor(iopd.getClass());
			Object[] params = { iopd };
			return (IOProtocol) constructor.newInstance(params);
		} catch (Exception e) {
			throw new DeviceOrThingException(e);
		}
	}

	private void resetIOProtocols(ScriptProcessor scriptProcessor) {
		if (scriptProcessor == null)
			return;
		Map<String, Object> parameters = scriptProcessor.getParameters();
		if (parameters == null)
			return;
		Object scriptAccessObj = parameters.get(DeviceOrThingScriptAccess.SCRIPT_ACCESS_NAME_KEY);
		if (scriptAccessObj != null && scriptAccessObj instanceof DeviceOrThingScriptAccess) {
			DeviceOrThingScriptAccess scriptAccess = ((DeviceOrThingScriptAccess) scriptAccessObj);
			this.resetIOPs(scriptAccess.getIOProtocols());
		}
	}

	private void resetIOPs(List<IOProtocol> ioProtocols) {
		if (ioProtocols == null)
			return;
		for (IOProtocol iop : ioProtocols) {
			try {
				iop.reset();
			} catch (IOException e) {
				logger.warn(e.getLocalizedMessage());
			}
		}
	}
}
