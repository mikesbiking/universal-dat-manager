package com.shinysideup.udatm.lib.dao;

import com.shinysideup.udatm.lib.NoDataFoundException;
import com.shinysideup.udatm.lib.pojo.DOTContent;
import com.shinysideup.udatm.lib.pojo.DOTDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionDetails;

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
	}

	public DOTDetails getDOTDetails() throws NoDataFoundException {
		return datDAO.getDOTDetails(dotId);
	}

	public DOTContent getDOTContent() throws NoDataFoundException {
		return datDAO.getDOTContent(dotId);
	}

	public String getDOTConfiguration(Long configId) throws NoDataFoundException {
		return datDAO.getDOTConfiguration(configId);
	}

	public DOTModelFirmwareVersionDetails getDOTModelFirmwareVersionDetails(Long dotModelFirmwareVersionId)
			throws NoDataFoundException {
		return datDAO.getDOTModelFirmwareVersionDetails(dotModelFirmwareVersionId);
	}

	public String getDOTModelFirmwareVersionConfiguration(Long configId) throws NoDataFoundException {
		return datDAO.getDOTModelFirmwareVersionConfiguration(configId);
	}

	public String getDOTModelFirmwareVersionScript(Long scriptId) throws NoDataFoundException {
		return datDAO.getDOTModelFirmwareVersionScript(scriptId);
	}
}
