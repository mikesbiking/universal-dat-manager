package com.shinysideup.udatm.lib.io;

/**
 * @author Mike Worley
 */
public class NoOpIOProtocolDescriptor extends IOProtocolDescriptor {

	@Override
	public String getType() {
		return "NoOp";
	}

	@Override
	public String getIOProtocolClass() {
		return "com.shinysideup.udatm.lib.io.NoOpIOProtocol";
	}

	@Override
	public void setIOProtocolClass(String ioProtocolClass) {
	}
}
