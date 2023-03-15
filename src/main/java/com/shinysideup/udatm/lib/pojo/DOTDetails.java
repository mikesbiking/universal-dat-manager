package com.shinysideup.udatm.lib.pojo;

import java.util.List;

/**
 * @author Mike Worley
 */
public class DOTDetails {

	private Long id;

	private String model;

	private String serialNumber;

	private List<DOTType> types;

	private List<DOTConfigurationDetails> allConfigurationDetails;

	private DOTModelFirmwareVersionDetails firmwareVersionDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<DOTType> getTypes() {
		return types;
	}

	public void setTypes(List<DOTType> types) {
		this.types = types;
	}

	public List<DOTConfigurationDetails> getAllConfigurationDetails() {
		return allConfigurationDetails;
	}

	public void setAllConfigurationDetails(List<DOTConfigurationDetails> allConfigurationDetails) {
		this.allConfigurationDetails = allConfigurationDetails;
	}

	public DOTModelFirmwareVersionDetails getFirmwareVersionDetails() {
		return firmwareVersionDetails;
	}

	public void setFirmwareVersionDetails(DOTModelFirmwareVersionDetails firmwareVersionDetails) {
		this.firmwareVersionDetails = firmwareVersionDetails;
	}

}
