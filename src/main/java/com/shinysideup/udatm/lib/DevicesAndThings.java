package com.shinysideup.udatm.lib;

import com.shinysideup.udatm.lib.dao.DevicesAndThingsDAO;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionCommandDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;

/**
 * @author Mike Worley
 */
public interface DevicesAndThings extends DevicesAndThingsDAO {

	public Handle runDOTScript(Long dotId, DOTModelFirmwareVersionScriptDetails scriptDetails)
			throws NoDataFoundException, DeviceOrThingException;

	public String runDOTCommand(Long dotId, DOTModelFirmwareVersionCommandDetails commandDetails)
			throws NoDataFoundException, DeviceOrThingException;

	public Result getResult(Handle handle) throws NoDataFoundException, DeviceOrThingException;

	/**
	 * Shuts down this application/library
	 */
	public void shutdown();

}
