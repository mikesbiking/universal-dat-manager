package com.shinysideup.udatm.lib.io;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shinysideup.udatm.lib.util.JsonUtil;

/**
 * @author Mike Worley
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = NoOpIOProtocolDescriptor.class)
@JsonSubTypes({ @JsonSubTypes.Type(value = FileCaptureIOProtocolDescriptor.class, name = "FileCapture"),
		@JsonSubTypes.Type(value = HTTPIOProtocolDescriptor.class, name = "HTTP"),
		@JsonSubTypes.Type(value = NoOpIOProtocolDescriptor.class, name = "NoOp"),
		@JsonSubTypes.Type(value = TCPIOProtocolDescriptor.class, name = "TCP"),
		@JsonSubTypes.Type(value = TelnetIOProtocolDescriptor.class, name = "Telnet") })
public abstract class IOProtocolDescriptor {

	/**
	 * Each subclass must implement this method and return a unique identifier.<br>
	 * Subclasses also must add themselves to above. This is needed for
	 * deserializing.
	 */
	public abstract String getType();

	/**
	 * Each subclass needs to provide the Implementing IOProtocol class name.<br>
	 * i.e., "com.shinysideup.udatm.lib.io.[NAME]IOProtocol"<br>
	 * This is used to inject the Impl class using reflection.
	 */
	public abstract String getIOProtocolClass();

	public abstract void setIOProtocolClass(String ioProtocolClass);

	@Override
	public String toString() {
		try {
			return JsonUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

}
