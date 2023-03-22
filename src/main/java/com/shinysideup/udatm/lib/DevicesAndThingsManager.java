package com.shinysideup.udatm.lib;

import java.util.List;

import com.shinysideup.udatm.lib.dao.DevicesAndThingsDAO;
import com.shinysideup.udatm.lib.pojo.DATDetails;
import com.shinysideup.udatm.lib.pojo.DOTConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTContent;
import com.shinysideup.udatm.lib.pojo.DOTDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelContent;
import com.shinysideup.udatm.lib.pojo.DOTModelDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionCommandDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;

/**
 * @author Mike Worley
 */
public class DevicesAndThingsManager implements DevicesAndThings {

	private DevicesAndThingsDAO datDAO;
	private DeviceOrThingCollection datCollection;

	public DevicesAndThingsManager(DevicesAndThingsDAO datDAO) {
		this.datDAO = datDAO;
		datCollection = new DeviceOrThingCollection(datDAO);
	}

	@Override
	public DATDetails getDATManagerDetails() throws NoDataFoundException {
		return this.datDAO.getDATManagerDetails();
	}

	@Override
	public DATDetails updateDATManagerDetails(DATDetails details) throws NoDataFoundException {
		return this.datDAO.getDATManagerDetails();
	}

	@Override
	public List<DOTDetails> getAllDOTDetails() throws NoDataFoundException {
		return this.datDAO.getAllDOTDetails();
	}

	@Override
	public DOTDetails addDOT(DOTContent info) throws NoDataFoundException {
		return this.datDAO.addDOT(info);
	}

	@Override
	public DOTDetails updateDOT(Long dotId, DOTContent content) throws NoDataFoundException {
		return this.datDAO.updateDOT(dotId, content);
	}

	@Override
	public DOTDetails getDOTDetails(Long dotId) throws NoDataFoundException {
		return this.datDAO.getDOTDetails(dotId);
	}

	@Override
	public DOTContent getDOTContent(Long dotId) throws NoDataFoundException {
		return this.datDAO.getDOTContent(dotId);
	}

	@Override
	public void removeDOT(Long dotId) throws NoDataFoundException {
		this.datDAO.removeDOT(dotId);
		this.datCollection.removeDeviceOrThing(dotId);
	}

	@Override
	public void addDOTConfiguration(Long dotModelId, DOTConfigurationContent config) throws NoDataFoundException {
		this.datDAO.addDOTConfiguration(dotModelId, config);
	}

	@Override
	public void updateConfiguration(DOTConfigurationContent config) throws NoDataFoundException {
		this.datDAO.updateConfiguration(config);
	}

	@Override
	public DOTConfigurationContent getDOTConfiguration(Long configId) throws NoDataFoundException {
		return this.datDAO.getDOTConfiguration(configId);
	}

	@Override
	public void removeDOTConfiguration(Long configId) throws NoDataFoundException {
		this.datDAO.removeDOTConfiguration(configId);
	}

	@Override
	public Handle runDOTScript(Long dotId, DOTModelFirmwareVersionScriptDetails scriptDetails)
			throws NoDataFoundException, DeviceOrThingException {
		return this.datCollection.getDeviceOrThing(dotId).runDOTScript(scriptDetails);
	}

	@Override
	public String runDOTCommand(Long dotId, DOTModelFirmwareVersionCommandDetails commandDetails)
			throws NoDataFoundException, DeviceOrThingException {
		return this.datCollection.getDeviceOrThing(dotId).runDOTCommand(commandDetails);
	}

	@Override
	public Result getResult(Handle handle) throws NoDataFoundException, DeviceOrThingException {
		return this.datCollection.getDeviceOrThing(handle.getDotId()).getResult(handle);
	}

	@Override
	public void addDOTModel(DOTModelContent content) throws NoDataFoundException {
		this.datDAO.addDOTModel(content);
	}

	@Override
	public void updateDOTModel(Long dotModelId, DOTModelContent content) throws NoDataFoundException {
		this.datDAO.updateDOTModel(dotModelId, content);
	}

	@Override
	public void removeDOTModel(Long dotModelId) throws NoDataFoundException {
		this.datDAO.removeDOTModel(dotModelId);
	}

	@Override
	public List<DOTModelDetails> getAllDOTModelDetails() throws NoDataFoundException {
		return this.datDAO.getAllDOTModelDetails();
	}

	@Override
	public void addDOTModelFirmwareVersion(Long dotModelId, DOTModelFirmwareVersionContent config)
			throws NoDataFoundException {
		this.datDAO.addDOTModelFirmwareVersion(dotModelId, config);
	}

	@Override
	public void removeDOTModelFirmwareVersion(Long dotModelFirmwareVersionId) throws NoDataFoundException {
		this.datDAO.removeDOTModelFirmwareVersion(dotModelFirmwareVersionId);
	}

	@Override
	public DOTModelFirmwareVersionDetails getDOTModelFirmwareVersionDetails(Long dotModelFirmwareVersionId)
			throws NoDataFoundException {
		return this.datDAO.getDOTModelFirmwareVersionDetails(dotModelFirmwareVersionId);
	}

	@Override
	public void addDOTModelFirmwareVersionConfiguration(Long dotModelId,
			DOTModelFirmwareVersionConfigurationContent config) throws NoDataFoundException {
		this.datDAO.addDOTModelFirmwareVersionConfiguration(dotModelId, config);
	}

	@Override
	public void updateDOTModelFirmwareVersionConfiguration(DOTModelFirmwareVersionConfigurationContent config)
			throws NoDataFoundException {
		this.datDAO.updateDOTModelFirmwareVersionConfiguration(config);
	}

	@Override
	public DOTModelFirmwareVersionConfigurationContent getDOTModelFirmwareVersionConfiguration(Long configId)
			throws NoDataFoundException {
		return this.datDAO.getDOTModelFirmwareVersionConfiguration(configId);
	}

	@Override
	public void removeDOTModelFirmwareVersionConfiguration(Long configId) throws NoDataFoundException {
		this.datDAO.removeDOTModelFirmwareVersionConfiguration(configId);
	}

	@Override
	public void addDOTModelSFirmwareVersioncript(Long dotModelId, DOTModelFirmwareVersionScriptContent script)
			throws NoDataFoundException {
		this.datDAO.addDOTModelSFirmwareVersioncript(dotModelId, script);
	}

	@Override
	public void updateDOTModelSFirmwareVersioncript(DOTModelFirmwareVersionScriptContent script)
			throws NoDataFoundException {
		this.datDAO.updateDOTModelSFirmwareVersioncript(script);
	}

	@Override
	public DOTModelFirmwareVersionScriptContent getDOTModelFirmwareVersionScript(Long scriptId)
			throws NoDataFoundException {
		return this.datDAO.getDOTModelFirmwareVersionScript(scriptId);
	}

	@Override
	public void removeDOTModelFirmwareVersionScript(Long scriptId) throws NoDataFoundException {
		this.datDAO.removeDOTModelFirmwareVersionScript(scriptId);
	}

	@Override
	public void shutdown() {
		this.datCollection.shutdown();
	}

}
