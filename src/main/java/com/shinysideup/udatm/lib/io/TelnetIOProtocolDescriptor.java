package com.shinysideup.udatm.lib.io;

/**
 * @author Mike Worley
 */
public class TelnetIOProtocolDescriptor extends IOProtocolDescriptor {

	private String connectionAddress = null;

	private int port = 0;

	private int connectionTimeout = 500;

	private int readTimeout = 100;

	private String userName = null;

	private String userPassword = null;

	private String userPrompt = null;

	private String passPrompt = null;

	@Override
	public String getType() {
		return "Telnet";
	}

	@Override
	public String getIOProtocolClass() {
		return "com.shinysideup.udatm.lib.io.TelnetIOProtocol";
	}

	@Override
	public void setIOProtocolClass(String ioProtocolClass) {
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

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPrompt() {
		return userPrompt;
	}

	public void setUserPrompt(String userPrompt) {
		this.userPrompt = userPrompt;
	}

	public String getPassPrompt() {
		return passPrompt;
	}

	public void setPassPrompt(String passPrompt) {
		this.passPrompt = passPrompt;
	}

}
