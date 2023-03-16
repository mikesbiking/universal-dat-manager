package com.shinysideup.udatm.lib;

import com.shinysideup.udatm.lib.dao.DeviceOrThingDAO;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionCommandDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;

/**
 * @author Mike Worley
 */
public class DeviceOrThing {

	private final DeviceOrThingDAO datDAO;

	public DeviceOrThing(DeviceOrThingDAO datDAO) throws NoDataFoundException {
		this.datDAO = datDAO;
	}

	public Handle runDOTScript(DOTModelFirmwareVersionScriptDetails scriptDetails)
			throws NoDataFoundException, DeviceOrThingException {
		// TODO
		return null;
	}

	public Handle runDOTCommand(DOTModelFirmwareVersionCommandDetails commandDetails)
			throws NoDataFoundException, DeviceOrThingException {
		// TODO
		return null;
	}

	public Result getResult(Handle handle) throws NoDataFoundException, DeviceOrThingException {
		// TODO
		return null;
	}

	public void disconnectConnections() {
		// TODO
	}

	public void shutdown() {
		// TODO
	}

	public void update() {
		// TODO
	}

}
