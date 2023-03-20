package com.shinysideup.udatm.lib;

/**
 * @author Mike Worley
 */
public class Result {

	private boolean complete;

	private String result;

	private String lastErrorMessage;

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		if (result == null) {
			this.result = null;
		} else {
			if (this.result == null) {
				this.result = result;
			} else {
				this.result = this.result.concat(result);
			}
		}
	}

	public String getLastErrorMessage() {
		return lastErrorMessage;
	}

	public void setLastErrorMessage(String lastErrorMessage) {
		this.lastErrorMessage = lastErrorMessage;
	}

}
