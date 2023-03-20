package com.shinysideup.udatm.lib;

/**
 * @author Mike Worley
 */
public class DeviceOrThingException extends GeneralException {
	private static final long serialVersionUID = 1L;

	public DeviceOrThingException() {
		super();
	}

	public DeviceOrThingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeviceOrThingException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeviceOrThingException(String message) {
		super(message);
	}

	public DeviceOrThingException(Throwable cause) {
		super(cause);
	}

}
