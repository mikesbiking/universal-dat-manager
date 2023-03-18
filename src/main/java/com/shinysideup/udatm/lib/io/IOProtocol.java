package com.shinysideup.udatm.lib.io;

import java.io.IOException;

/**
 * @author Mike Worley
 */
public interface IOProtocol {

	/**
	 * A reversible string character encoding for bytes. Use as needed to encode
	 * bytes with, e.g., <tt>new String(bytesArray, BYTE_ENCODING)</tt> or to decode
	 * bytes with, e.g., <tt>bytesString.getBytes(BYTE_ENCODING)</tt>.
	 */
	public static final String BYTE_ENCODING = "ISO-8859-1";

	public IOProtocolDescriptor getIOProtocolDescriptor();

	public boolean initialize() throws IOException;

	public void reset() throws IOException;

}
