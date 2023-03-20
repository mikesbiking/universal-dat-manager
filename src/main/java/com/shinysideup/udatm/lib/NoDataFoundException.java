package com.shinysideup.udatm.lib;

/**
 * @author Mike Worley
 */
public class NoDataFoundException extends GeneralException {

	private static final long serialVersionUID = 1260692884910L;

	public NoDataFoundException() {
		super();
	}

	public NoDataFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDataFoundException(String message) {
		super(message);
	}

	public NoDataFoundException(Throwable cause) {
		super(cause);
	}

}
