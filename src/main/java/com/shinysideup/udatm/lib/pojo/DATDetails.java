package com.shinysideup.udatm.lib.pojo;

/**
 * @author Mike Worley
 */
public class DATDetails {

	private Long id;

	private String hardwareType;

	private String softwareVersion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHardwareType() {
		return hardwareType;
	}

	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

}
