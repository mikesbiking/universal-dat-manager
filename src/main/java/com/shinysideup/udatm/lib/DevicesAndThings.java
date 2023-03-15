package com.shinysideup.udatm.lib;

import java.util.List;

import com.shinysideup.udatm.lib.pojo.DOTContent;
import com.shinysideup.udatm.lib.pojo.DOTModelDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionCommandDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionConfigurationContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionContent;
import com.shinysideup.udatm.lib.pojo.DOTDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionDetails;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptContent;
import com.shinysideup.udatm.lib.pojo.DOTModelFirmwareVersionScriptDetails;
import com.shinysideup.udatm.lib.pojo.DATDetails;
import com.shinysideup.udatm.lib.pojo.DOTConfigurationContent;

/**
 * @author Mike Worley
 */
public interface DevicesAndThings {

	public DATDetails getDATManagerDetails() throws NoDataFoundException;

	public DATDetails updateDATManagerDetails(DATDetails details) throws NoDataFoundException;

	public List<DOTDetails> getAllDOTDetails() throws NoDataFoundException;

	public DOTDetails addDOT(DOTContent info) throws NoDataFoundException;

	public DOTDetails updateDOT(Long dotId, DOTContent content) throws NoDataFoundException;

	public DOTDetails getDOTDetails(Long dotId) throws NoDataFoundException;

	public DOTContent getDOTContent(Long dotId) throws NoDataFoundException;

	public void removeDOT(Long dotId) throws NoDataFoundException;

	public void addDOTConfiguration(Long dotModelId, DOTConfigurationContent config) throws NoDataFoundException;

	public void updateConfiguration(DOTConfigurationContent config) throws NoDataFoundException;

	public String getDOTConfiguration(Long configId) throws NoDataFoundException;

	public void removeDOTConfiguration(Long configId) throws NoDataFoundException;

	public Handle runDOTScript(Long dotId, DOTModelFirmwareVersionScriptDetails scriptDetails)
			throws NoDataFoundException, DeviceOrThingException;

	public Handle runDOTCommand(Long dotId, DOTModelFirmwareVersionCommandDetails commandDetails)
			throws NoDataFoundException, DeviceOrThingException;

	public Result getResult(Handle handle) throws NoDataFoundException, DeviceOrThingException;

	public void addDOTModel(DOTModelContent content) throws NoDataFoundException;

	public void updateDOTModel(Long dotModelId, DOTModelContent content) throws NoDataFoundException;

	public void removeDOTModel(Long dotModelId) throws NoDataFoundException;

	public List<DOTModelDetails> getAllDOTModelDetails() throws NoDataFoundException;

	public void addDOTModelFirmwareVersion(Long dotModelId, DOTModelFirmwareVersionContent config)
			throws NoDataFoundException;

	public void removeDOTModelFirmwareVersion(Long dotModelFirmwareVersionId) throws NoDataFoundException;

	public DOTModelFirmwareVersionDetails getDOTModelFirmwareVersionDetails(Long dotModelFirmwareVersionId)
			throws NoDataFoundException;

	public void addDOTModelFirmwareVersionConfiguration(Long dotModelId,
			DOTModelFirmwareVersionConfigurationContent config) throws NoDataFoundException;

	public void updateDOTModelFirmwareVersionConfiguration(DOTModelFirmwareVersionConfigurationContent config)
			throws NoDataFoundException;

	public String getDOTModelFirmwareVersionConfiguration(Long configId) throws NoDataFoundException;

	public void removeDOTModelFirmwareVersionConfiguration(Long configId) throws NoDataFoundException;

	public void addDOTModelSFirmwareVersioncript(Long dotModelId, DOTModelFirmwareVersionScriptContent script)
			throws NoDataFoundException;

	public void updateDOTModelSFirmwareVersioncript(DOTModelFirmwareVersionScriptContent script)
			throws NoDataFoundException;

	public String getDOTModelFirmwareVersionScript(Long scriptId) throws NoDataFoundException;

	public void removeDOTModelFirmwareVersionScript(Long scriptId) throws NoDataFoundException;

	/**
	 * Resets all the DOT connections.
	 * 
	 * @throws DeviceOrThingException
	 */
	public void disconnectDOTConnections() throws DeviceOrThingException;

	/**
	 * Shuts down this application/library
	 */
	public void shutdown();

}
