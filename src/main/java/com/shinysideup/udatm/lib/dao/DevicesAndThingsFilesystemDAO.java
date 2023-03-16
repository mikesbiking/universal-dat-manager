package com.shinysideup.udatm.lib.dao;

import java.util.List;

import com.shinysideup.udatm.lib.NoDataFoundException;
import com.shinysideup.udatm.lib.pojo.DATDetails;
import com.shinysideup.udatm.lib.pojo.DOTConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTContent;
import com.shinysideup.udatm.lib.pojo.DOTDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelContent;
import com.shinysideup.udatm.lib.pojo.DOTModelDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptContent;

public class DevicesAndThingsFilesystemDAO implements DevicesAndThingsDAO {

	@Override
	public DATDetails getDATManagerDetails() throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DATDetails updateDATManagerDetails(DATDetails details) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DOTDetails> getAllDOTDetails() throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DOTDetails addDOT(DOTContent info) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DOTDetails updateDOT(Long dotId, DOTContent content) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DOTDetails getDOTDetails(Long dotId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DOTContent getDOTContent(Long dotId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDOT(Long dotId) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDOTConfiguration(Long dotModelId, DOTConfigurationContent config) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateConfiguration(DOTConfigurationContent config) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDOTConfiguration(Long configId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDOTConfiguration(Long configId) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDOTModel(DOTModelContent content) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDOTModel(Long dotModelId, DOTModelContent content) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeDOTModel(Long dotModelId) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DOTModelDetails> getAllDOTModelDetails() throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDOTModelFirmwareVersion(Long dotModelId, DOTModelFirmwareVersionContent config)
			throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeDOTModelFirmwareVersion(Long dotModelFirmwareVersionId) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public DOTModelFirmwareVersionDetails getDOTModelFirmwareVersionDetails(Long dotModelFirmwareVersionId)
			throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDOTModelFirmwareVersionConfiguration(Long dotModelId,
			DOTModelFirmwareVersionConfigurationContent config) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDOTModelFirmwareVersionConfiguration(DOTModelFirmwareVersionConfigurationContent config)
			throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDOTModelFirmwareVersionConfiguration(Long configId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDOTModelFirmwareVersionConfiguration(Long configId) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDOTModelSFirmwareVersioncript(Long dotModelId, DOTModelFirmwareVersionScriptContent script)
			throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDOTModelSFirmwareVersioncript(DOTModelFirmwareVersionScriptContent script)
			throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDOTModelFirmwareVersionScript(Long scriptId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDOTModelFirmwareVersionScript(Long scriptId) throws NoDataFoundException {
		// TODO Auto-generated method stub

	}

}
