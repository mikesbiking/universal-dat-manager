package com.shinysideup.udatm.lib;

import java.io.Serializable;

/**
 * @author Mike Worley
 */
public class DeviceOrThingException extends GeneralException implements Serializable {
	private static final long serialVersionUID = 1L;

	private long bytesTransferred;

	public DeviceOrThingException() {
		super();
	}

	public DeviceOrThingException(String errorMsg) {
		this(1, errorMsg, 0);
	}

	public DeviceOrThingException(int errorCode, String errorMsg, long bytesTransferred) {
		super(errorCode, errorMsg);
		this.bytesTransferred = bytesTransferred;
	}

	public long getBytesTransferred() {
		return bytesTransferred;
	}

	public void setBytesTransferred(long bytesTransferred) {
		this.bytesTransferred = bytesTransferred;
	}

}
