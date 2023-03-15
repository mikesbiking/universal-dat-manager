package com.shinysideup.udatm.lib.io;

/**
 * @author Mike Worley
 */
public class TCPIOProtocolDescriptor extends IOProtocolDescriptor {

	public final String type = "TCP";

	private String connectionAddress = null;

	private int port = 0;

	private int connectionTimeout = 500;

	private boolean blocking = false;

	@Override
	public String getType() {
		return "TCP";
	}

	@Override
	public String getIOProtocolClass() {
		return "com.shinysideup.udatm.lib.io.TCPIOProtocol";
	}

	@Override
	public void setIOProtocolClass(String ioProtocolClass) {
	}

	public boolean isBlocking() {
		return blocking;
	}

	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	public String getConnectionAddress() {
		return connectionAddress;
	}

	public void setConnectionAddress(String connectionAddress) {
		this.connectionAddress = connectionAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

}
