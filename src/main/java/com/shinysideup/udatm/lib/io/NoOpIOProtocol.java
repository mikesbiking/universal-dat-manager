package com.shinysideup.udatm.lib.io;

import java.io.IOException;

/**
 * @author Mike Worley
 */
public class NoOpIOProtocol implements IOProtocol {

	private NoOpIOProtocolDescriptor iopd = null;

	public NoOpIOProtocol(NoOpIOProtocolDescriptor iopd) {
		this.iopd = iopd;
	}

	@Override
	public boolean initialize() throws IOException {
		return false;
	}

	@Override
	public void reset() throws IOException {
	}

	@Override
	public NoOpIOProtocolDescriptor getIOProtocolDescriptor() {
		return this.iopd;
	}

}
