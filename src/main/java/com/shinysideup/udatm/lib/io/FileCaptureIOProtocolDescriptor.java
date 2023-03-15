package com.shinysideup.udatm.lib.io;

/**
 * @author Mike Worley
 */
public class FileCaptureIOProtocolDescriptor extends IOProtocolDescriptor {

	private String filePath = null;

	private boolean append = true;

	@Override
	public String getType() {
		return "FileCapture";
	}

	@Override
	public String getIOProtocolClass() {
		return "com.shinysideup.udatm.lib.io.FileCaptureIOProtocol";
	}

	@Override
	public void setIOProtocolClass(String ioProtocolClass) {
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

}
