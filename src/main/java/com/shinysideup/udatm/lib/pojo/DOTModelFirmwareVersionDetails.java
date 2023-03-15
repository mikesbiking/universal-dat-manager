package com.shinysideup.udatm.lib.pojo;

import java.util.List;

public class DOTModelFirmwareVersionDetails {

	private String firmwareVersion;

	private List<DOTModelFirmwareVersionCommandDetails> allCommandDetails;

	private List<DOTModelFirmwareVersionScriptDetails> allScriptDetails;

	private List<DOTModelFirmwareVersionConfigurationDetails> allConfigurationDetails;

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

	public List<DOTModelFirmwareVersionScriptDetails> getAllScriptDetails() {
		return allScriptDetails;
	}

	public void setAllScriptDetails(List<DOTModelFirmwareVersionScriptDetails> allScriptDetails) {
		this.allScriptDetails = allScriptDetails;
	}

	public List<DOTModelFirmwareVersionConfigurationDetails> getAllConfigurationDetails() {
		return allConfigurationDetails;
	}

	public void setAllConfigurationDetails(List<DOTModelFirmwareVersionConfigurationDetails> allConfigurationDetails) {
		this.allConfigurationDetails = allConfigurationDetails;
	}

}
