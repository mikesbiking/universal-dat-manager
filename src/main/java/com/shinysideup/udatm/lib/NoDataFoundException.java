package com.shinysideup.udatm.lib;

import java.io.Serializable;

/**
 * @author Mike Worley
 */
public class NoDataFoundException extends GeneralException implements Serializable {
	private static final long serialVersionUID = 1260692884910L;

	public NoDataFoundException() {
		super();
	}

	public NoDataFoundException(String errorMsg) {
		this(1, errorMsg);
	}

	public NoDataFoundException(int errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}
}
