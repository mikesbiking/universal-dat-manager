package com.shinysideup.udatm.lib.io;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Mike Worley
 */
public class TCPIOProtocol extends IOProtocolBasics {

	private TCPIOProtocolDescriptor iopd = null;

	public TCPIOProtocol(TCPIOProtocolDescriptor iopd) throws IOException {
		this.iopd = iopd;
		this.socketChannel = SocketChannel.open();
		this.socketChannel.configureBlocking(this.iopd.isBlocking());
	}

	@Override
	public boolean initialize() throws IOException {
		if (this.socketChannel.isConnected()) {
			throw new IOException("Socket is already connected");
		}
		// Send a connection request to the server; this method is non-blocking
		this.socketChannel.connect(new InetSocketAddress(this.iopd.getConnectionAddress(), this.iopd.getPort()));
		long startTime = System.currentTimeMillis();
		while (!this.socketChannel.finishConnect()) {
			if ((System.currentTimeMillis() - startTime) >= this.iopd.getConnectionTimeout()) {
				this.socketChannel.close();
				throw new IOException("Connect timed out");
			}
			try {
				Thread.sleep(100);
			} catch (Exception sleepe) {
				this.socketChannel.close();
				throw new IOException(sleepe.toString());
			}
		}
		return true;
	}

	@Override
	public byte[] read() throws IOException {
		try {
			ByteBuffer myreadbytebuffer = ByteBuffer.allocate(1024);
			int numBytesRead = this.socketChannel.read(myreadbytebuffer);
			if (numBytesRead == -1) {
				// No more bytes can be read from the channel
				throw new SocketException("socket is closed");
			} else if (numBytesRead > 0) {
				byte[] myBytes = new byte[myreadbytebuffer.position()];
				myreadbytebuffer.flip();
				myreadbytebuffer.get(myBytes);
				myreadbytebuffer.clear();
				return myBytes;
			} else {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					throw new IOException(e.toString());
				}
				return new byte[0];
			}
		} catch (IOException ioe) {
			throw ioe;
		}
	}

	@Override
	public void reset() throws IOException {
		this.socketChannel.close();
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		try {
			ByteBuffer writeByteBuffer = ByteBuffer.allocate(bytes.length);
			writeByteBuffer = writeByteBuffer.put(bytes);
			// Prepare the buffer for reading by the socket
			writeByteBuffer.flip();
			// Write bytes
			int byteswritten = this.socketChannel.write(writeByteBuffer);
			if (byteswritten < bytes.length) {
				InterruptedIOException iioe = new InterruptedIOException("Failed to write all the bytes");
				iioe.bytesTransferred = byteswritten;
				throw iioe;
			}
		} catch (IOException ioe) {
			throw ioe;
		}
	}

	private final SocketChannel socketChannel;

	@Override
	public TCPIOProtocolDescriptor getIOProtocolDescriptor() {
		return this.iopd;
	}

}
