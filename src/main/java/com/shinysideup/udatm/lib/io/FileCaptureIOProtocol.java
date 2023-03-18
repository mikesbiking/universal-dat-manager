package com.shinysideup.udatm.lib.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * @author Mike Worley
 */
public class FileCaptureIOProtocol extends IOProtocolBasics {

	private FileCaptureIOProtocolDescriptor iopd;

	public FileCaptureIOProtocol(FileCaptureIOProtocolDescriptor fciopd) {
		this.iopd = fciopd;
	}

	public void write(byte[] bytes) throws IOException {
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(this.iopd.getFilePath(), this.iopd.isAppend()));
			out.write(bytes);
			out.flush();
		} catch (InterruptedIOException iioe) {
			throw iioe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public byte[] read() throws IOException {
		this.initialize();
		return "".getBytes(IOProtocol.BYTE_ENCODING);
	}

	public String readUntil(String pattern) throws IOException {
		return "";
	}

	public String readUntilExpression(String expression) throws IOException {
		return "";
	}

	public boolean initialize() throws IOException {
		return true;
	}

	public void reset() throws IOException {

	}

	@Override
	public FileCaptureIOProtocolDescriptor getIOProtocolDescriptor() {
		return this.iopd;
	}

}
