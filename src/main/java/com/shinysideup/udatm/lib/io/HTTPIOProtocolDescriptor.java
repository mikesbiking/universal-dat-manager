package com.shinysideup.udatm.lib.io;

/**
 * @author Mike Worley
 */
public class HTTPIOProtocolDescriptor extends IOProtocolDescriptor {

	private String connectionAddress = null;

	private int port = 8080;

	private int connectionTimeout = 500;

	private int readTimeout = 100;

	private int writeTimeout = 100;

	private int retries = 0;

	private boolean secure = false;

	private boolean acceptInvalidCertificate = false;

	private int maxResponseSize = 1024;

	private boolean authEnabled = true;

	private String userId = null;

	@Override
	public String getType() {
		return "HTTP";
	}

	@Override
	public String getIOProtocolClass() {
		return "com.shinysideup.udatm.lib.io.HTTPIOProtocol";
	}

	@Override
	public void setIOProtocolClass(String ioProtocolClass) {
	}

	public boolean isAuthEnabled() {
		return authEnabled;
	}

	public void setAuthEnabled(boolean authEnabled) {
		this.authEnabled = authEnabled;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password = null;

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

	public int getWriteTimeout() {
		return writeTimeout;
	}

	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public boolean isSecure() {
		return secure;
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	public boolean isAcceptInvalidCertificate() {
		return acceptInvalidCertificate;
	}

	public void setAcceptInvalidCertificate(boolean acceptInvalidCertificate) {
		this.acceptInvalidCertificate = acceptInvalidCertificate;
	}

	public int getMaxResponseSize() {
		return maxResponseSize;
	}

	public void setMaxResponseSize(int maxResponseSize) {
		this.maxResponseSize = maxResponseSize;
	}

}
