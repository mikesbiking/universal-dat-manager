package com.shinysideup.udatm.lib;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shinysideup.udatm.lib.dao.DeviceOrThingDAO;
import com.shinysideup.udatm.lib.io.IOProtocol;
import com.shinysideup.udatm.lib.io.IOProtocolDescriptor;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionCommandDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;

/**
 * @author Mike Worley
 */
public class DeviceOrThing {

	private static final Log logger = LogFactory.getLog(DeviceOrThing.class);
	private final DeviceOrThingDAO datDAO;
	private final ArrayList<IOProtocol> iops = new ArrayList<>();

	public DeviceOrThing(DeviceOrThingDAO datDAO) throws NoDataFoundException {
		if (datDAO == null)
			throw new NoDataFoundException("DeviceOrThingDAO is null");
		this.datDAO = datDAO;
	}

	public Handle runDOTScript(DOTModelFirmwareVersionScriptDetails scriptDetails)
			throws NoDataFoundException, DeviceOrThingException {
		synchronized (iops) {
			// TODO
			return null;
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
			// TODO
			return null;
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
