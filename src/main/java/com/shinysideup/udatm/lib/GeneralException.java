package com.shinysideup.udatm.lib;

import java.io.Serializable;

/**
 * @author Mike Worley
 */
public class GeneralException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	private int errorCode;

	private String errorMsg;

	public GeneralException() {
		this(1, "unknown");
	}

	public GeneralException(String errorMsg) {
		this(1, errorMsg);
	}

	public GeneralException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String toString() {
		return this.getClass().getName() + " : " + this.errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
