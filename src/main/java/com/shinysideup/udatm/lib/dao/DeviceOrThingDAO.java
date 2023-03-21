package com.shinysideup.udatm.lib.dao;

import com.shinysideup.udatm.lib.NoDataFoundException;
import com.shinysideup.udatm.lib.pojo.DOTConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTConfigurationDetails;
import com.shinysideup.udatm.lib.pojo.DOTContent;
import com.shinysideup.udatm.lib.pojo.DOTDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionConfigurationDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;

public class DeviceOrThingDAO {

	DevicesAndThingsDAO datDAO;
	Long dotId;

	/**
	 * @author Mike Worley
	 */
	public DeviceOrThingDAO(DevicesAndThingsDAO datDAO, Long dotId) throws NoDataFoundException {
		// Check to see if this dot is valid
		datDAO.getDOTDetails(dotId);
		this.datDAO = datDAO;
		this.dotId = dotId;
		datDAO.getDOTDetails(dotId).getModel();
		datDAO.getDOTDetails(dotId).getFirmwareVersionDetails();
	}

	public DOTDetails getDOTDetails() throws NoDataFoundException {
		return datDAO.getDOTDetails(dotId);
	}

	public DOTContent getDOTContent() throws NoDataFoundException {
		return datDAO.getDOTContent(dotId);
	}

	public DOTConfigurationContent getDOTConfiguration(String configName) throws NoDataFoundException {
		Long configId = null;
		for (DOTConfigurationDetails config : this.datDAO.getDOTDetails(this.dotId).getAllConfigurationDetails()) {
			if (config.getName().equals(configName)) {
				configId = config.getId();
			}
		}
		if (configId == null)
			throw new NoDataFoundException("Configuration not found");
		return datDAO.getDOTConfiguration(configId);
	}

	public DOTModelFirmwareVersionConfigurationContent getDOTModelFirmwareVersionConfiguration(String configName)
			throws NoDataFoundException {
		Long configId = null;
		DOTModelFirmwareVersionDetails version = this.datDAO.getDOTDetails(this.dotId).getFirmwareVersionDetails();
		for (DOTModelFirmwareVersionConfigurationDetails config : version.getAllConfigurationDetails()) {
			if (config.getName().equals(configName))
				configId = config.getId();
		}
		if (configId == null)
			throw new NoDataFoundException("Configuration not found");
		return datDAO.getDOTModelFirmwareVersionConfiguration(configId);
	}

	public DOTModelFirmwareVersionScriptContent getDOTModelFirmwareVersionScriptContent(Long scriptId)
			throws NoDataFoundException {
		return datDAO.getDOTModelFirmwareVersionScript(scriptId);
	}

	public DOTModelFirmwareVersionScriptContent getDOTModelFirmwareVersionScriptContent(String scriptName)
			throws NoDataFoundException {
		Long scriptId = null;
		DOTModelFirmwareVersionDetails version = this.datDAO.getDOTDetails(this.dotId).getFirmwareVersionDetails();
		for (DOTModelFirmwareVersionScriptDetails script : version.getAllScriptDetails()) {
			if (script.getName().equals(scriptName))
				scriptId = script.getId();
		}
		if (scriptId == null)
			throw new NoDataFoundException("Script not found");
		return datDAO.getDOTModelFirmwareVersionScript(scriptId);
	}
}
