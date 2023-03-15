package com.shinysideup.udatm.lib;

/**
 * @author Mike Worley
 */
public class Result {

	private boolean complete;

	private String currentResult;

	private byte[] currentBytes;

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getCurrentResult() {
		return currentResult;
	}

	public void setCurrentResult(String currentResult) {
		this.currentResult = currentResult;
	}

	public byte[] getCurrentBytes() {
		return currentBytes;
	}

	public void setCurrentBytes(byte[] currentBytes) {
		this.currentBytes = currentBytes;
	}

}
