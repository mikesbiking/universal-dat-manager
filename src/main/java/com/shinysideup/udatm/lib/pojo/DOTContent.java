package com.shinysideup.udatm.lib.pojo;

import java.util.List;

import com.shinysideup.udatm.lib.io.IOProtocolDescriptor;

/**
 * @author Mike Worley
 */
public class DOTContent {

	private String model;

	private String firmwareVersion;

	private String serialNumber;

	private List<DOTType> types;

	private List<IOProtocolDescriptor> ioProtocolDescriptors;

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

	public List<IOProtocolDescriptor> getIoProtocolDescriptors() {
		return ioProtocolDescriptors;
	}

	public void setIoProtocolDescriptors(List<IOProtocolDescriptor> ioProtocolDescriptors) {
		this.ioProtocolDescriptors = ioProtocolDescriptors;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

}
