package com.shinysideup.udatm.lib.pojo;

import java.util.List;

/**
 * @author Mike Worley
 */
public class DOTModelFirmwareVersionContent {

	private String firmwareVersion;

	private List<DOTModelFirmwareVersionCommandDetails> allCommandDetails;

	private List<DOTModelFirmwareVersionScriptContent> allScriptContent;

	private List<DOTModelFirmwareVersionConfigurationContent> allConfigurationContent;

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public List<DOTModelFirmwareVersionCommandDetails> getAllCommandDetails() {
		return allCommandDetails;
	}

	public void setAllCommandDetails(List<DOTModelFirmwareVersionCommandDetails> allCommandDetails) {
		this.allCommandDetails = allCommandDetails;
	}

	public List<DOTModelFirmwareVersionScriptContent> getAllScriptContent() {
		return allScriptContent;
	}

	public void setAllScriptContent(List<DOTModelFirmwareVersionScriptContent> allScriptContent) {
		this.allScriptContent = allScriptContent;
	}

	public List<DOTModelFirmwareVersionConfigurationContent> getAllConfigurationContent() {
		return allConfigurationContent;
	}

	public void setAllConfigurationContent(List<DOTModelFirmwareVersionConfigurationContent> allConfigurationContent) {
		this.allConfigurationContent = allConfigurationContent;
	}

}
