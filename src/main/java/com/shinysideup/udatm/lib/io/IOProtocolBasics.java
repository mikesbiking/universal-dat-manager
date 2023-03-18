package com.shinysideup.udatm.lib.io;

import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * @author Mike Worley
 */
public abstract class IOProtocolBasics implements IOProtocol {

	public abstract void write(byte[] bytes) throws IOException;

	public abstract byte[] read() throws IOException;

	public void write(String value) throws IOException {
		this.write(value.getBytes(IOProtocol.BYTE_ENCODING));
	}

	public String readUntil(String regx) throws IOException {
		int MAX_EMPTY_READS = 10;
		int tryCounter = 0;
		String result = "";
		try {
			result = new String(this.read(), IOProtocol.BYTE_ENCODING);
		} catch (InterruptedIOException iioe) {
			// This is OK
			// Indicates the read call did not get any bytes
			// before timing out.
		}

		while (!result.matches(regx)) {
			if (tryCounter >= MAX_EMPTY_READS) {
				throw new IOException("Timed out waiting for expected response");
			}
			try {
				byte[] bytesRead = this.read();
				if (bytesRead.length == 0) {
					tryCounter++;
					try {
						Thread.sleep(100);
					} catch (InterruptedException sleepe) {
						// If thread is interrupted, throw IOException to
						// terminate loop
						throw new IOException(sleepe.toString());
					}
				} else {
					tryCounter = 0;
				}
				result += new String(bytesRead, IOProtocol.BYTE_ENCODING);
			} catch (InterruptedIOException iioe) {
				tryCounter++;
				try {
					Thread.sleep(100);
				} catch (InterruptedException sleepe) {
					// If thread is interrupted, throw IOException to
					// terminate loop
					throw new IOException(sleepe.toString());
				}
			}
		}
		return result;
	}

}
