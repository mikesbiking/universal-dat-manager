package com.shinysideup.udatm.lib.io;

import java.io.IOException;

/**
 * @author Mike Worley
 */
public interface IOProtocol {

	public IOProtocolDescriptor getIOProtocolDescriptor();

	public boolean initialize() throws IOException;

	public void reset() throws IOException;

}
