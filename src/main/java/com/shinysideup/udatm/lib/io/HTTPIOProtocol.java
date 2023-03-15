package com.shinysideup.udatm.lib.io;

import java.io.IOException;

/**
 * @author Mike Worley
 */
public class HTTPIOProtocol implements IOProtocol {

	HTTPIOProtocolDescriptor httpiopd;

	public HTTPIOProtocol(HTTPIOProtocolDescriptor httpiopd) throws IOException {
		this.httpiopd = httpiopd;
	}

	@Override
	public boolean initialize() throws IOException {
		synchronized (this.ioLock) {
			return false;
		}
	}

	@Override
	public synchronized void reset() throws IOException {
		synchronized (this.ioLock) {
		}
	}

	/**
	 * IO lock to synchronize access
	 */
	private Object ioLock = new Object();

	@Override
	public IOProtocolDescriptor getIOProtocolDescriptor() {
		return this.httpiopd;
	}

}
