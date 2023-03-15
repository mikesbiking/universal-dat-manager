package com.shinysideup.udatm.lib.io;

import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.commons.net.telnet.TelnetClient;

import com.shinysideup.udatm.lib.util.UDATMHelper;

/**
 * @author Mike Worley
 */
public class TelnetIOProtocol extends IOProtocolBasics {

	/**
	 * This is the pattern to wait for after login to confirm success. This can be
	 * overridden.
	 */
	public String loginResponsePattern = "/.+/";

	public TelnetIOProtocol(TelnetIOProtocolDescriptor iopd) throws IOException {
		this.iopd = iopd;
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		synchronized (ioLock) {
			initialize();
			try {
				this.telnet.getOutputStream().write(bytes);
				this.telnet.getOutputStream().flush();
			} catch (IOException ioe) {
				this.reset();
				throw ioe;
			}
		}
	}

	@Override
	public byte[] read() throws IOException {
		synchronized (ioLock) {
			// TelnetClient does not appear to respect read timeouts.
			// It seems to always return an empty byte array immediately
			long startTime = System.currentTimeMillis();
			while ((System.currentTimeMillis() - startTime) < this.iopd.getReadTimeout()) {
				byte[] bytes = this.read2();
				if (bytes.length > 0) {
					return bytes;
				}
			}
			throw new InterruptedIOException("Read timed out");
		}
	}

	private byte[] read2() throws IOException {
		synchronized (ioLock) {
			initialize();
			int bytesAvailable = this.telnet.getInputStream().available();
			byte[] bytebuffer = new byte[bytesAvailable];
			int bytesRead = this.telnet.getInputStream().read(bytebuffer);
			if (bytesRead < 0) {
				throw new IOException("End of the stream has been reached");
			} else if (bytesRead == 0) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					throw new IOException("read interupted: " + e.toString());
				}
				return new byte[0];
			}
			byte[] bytes = new byte[bytesRead];
			System.arraycopy(bytebuffer, 0, bytes, 0, bytebuffer.length);
			return bytes;
		}
	}

	@Override
	public boolean initialize() throws IOException {
		synchronized (ioLock) {
			if (this.telnet.isConnected()) {
				return false;
			}
			String debugResult = "";
			try {
				this.telnet.setConnectTimeout(this.iopd.getConnectionTimeout());
				this.telnet.connect(iopd.getConnectionAddress(), 23);
				this.telnet.setSoTimeout(this.iopd.getReadTimeout());
				this.telnet.setTcpNoDelay(true);
				if (this.iopd.getUserPrompt() != null) {
					debugResult += this.readUntil(this.iopd.getUserPrompt());
					this.write(this.iopd.getUserName() + "\r\n");
				}
				if (this.iopd.getPassPrompt() != null) {
					debugResult += this.readUntil(this.iopd.getPassPrompt());
					this.write(this.iopd.getUserPassword() + "\r\n");
				}
				debugResult += this.readUntil(this.loginResponsePattern);
				UDATMHelper.logger.debug("Telnet IOP login result:\n" + debugResult);
				return true;
			} catch (Exception e) {
				this.reset();
				throw new IOException(
						"Failed to login\nThis is what I recieved:\n" + debugResult + "\n" + e.toString());
			}
		}
	}

	@Override
	public void reset() throws IOException {
		synchronized (ioLock) {
			if (this.telnet.isConnected()) {
				this.telnet.disconnect();
			}
		}
	}

	private final TelnetIOProtocolDescriptor iopd;
	private final TelnetClient telnet = new TelnetClient();
	private Object ioLock = new Object();

	@Override
	public TelnetIOProtocolDescriptor getIOProtocolDescriptor() {
		return iopd;
	}

}
