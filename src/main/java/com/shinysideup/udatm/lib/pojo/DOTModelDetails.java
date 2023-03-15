package com.shinysideup.udatm.lib.pojo;

import java.util.List;

/**
 * @author Mike Worley
 */
public class DOTModelDetails {

	private Long id;

	private String description;

	private String model;

	private String manufacturer;

	private List<DOTModelFirmwareVersionDetails> dotModelFirmwareVersions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<DOTModelFirmwareVersionDetails> getDotModelFirmwareVersions() {
		return dotModelFirmwareVersions;
	}

	public void setDotModelFirmwareVersions(List<DOTModelFirmwareVersionDetails> dotModelFirmwareVersions) {
		this.dotModelFirmwareVersions = dotModelFirmwareVersions;
	}

}
