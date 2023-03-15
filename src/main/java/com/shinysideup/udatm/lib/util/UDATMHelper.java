package com.shinysideup.udatm.lib.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shinysideup.udatm.lib.NoDataFoundException;

/**
 * This class is a utility class used primarily by UTC to get important
 * configuration information.
 *
 * @author Mike Worley
 *
 */
public class UDATMHelper {

	private static final Properties udatmProperties = new Properties();

	public static final String UDATM_CONFIG_NAME = "udatm.properties";
	public static final String UDATM_HOME_DIR_KEY = "UDATM_HOME_DIR";
	public static final String LOG_FILE_LOCATION_KEY = "LOG_FILE_LOCATION";
	public static final String REFRESH_CACHE_KEY = "REFRESH_CACHE";
	public static final String TRUE_PROPERTY_VALUE = "true";
	public static final String FALSE_PROPERTY_VALUE = "false";

	/**
	 * The one and only logger instance
	 */
	public static final Logger logger;

	/**
	 * Create the shared ThreadPool which will run all Callables dispatched
	 */
	public static final ThreadPoolExecutor UDATM_Thread_Pool_Executor = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 60L,
			TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	/**
	 * Create the shared Scheduled ThreadPool for running threads on a schedule
	 */
	public static final ScheduledThreadPoolExecutor UDATM_Scheduled_ThreadPool_Executor = new ScheduledThreadPoolExecutor(
			10);

	/**
	 * Merge all properties into my props.<br>
	 * Properties are loaded in the following order:
	 * <ol>
	 * <li>First load all System properties (i.e., "-D[property name]")
	 * <li>Copy all properties from <b>"udatm.properties"</b>. This does not override
	 * any property that is already set with the same name.<br>
	 * Allows overriding "udatm.properties" settings.
	 * </ol>
	 * <b>"udatm.properties"</b> is searched for in the following order:
	 * <ol>
	 * <li>Looks under the "UDATM_HOME_DIR" environment location
	 * <li>Users Home directory
	 * <li>The default configuration packaged with UDATM
	 * </ol>
	 */
	static {
		String logMsg = "";
		try {
			// Copy all the System properties into my Props and override existing values
			mergeProperties(System.getProperties(), true);
			// Merge udatm.properties, but don't replace any values that are
			// already set.
			// This allows overriding udatm.properties with command line options
			try {
				File dspropsfile = getUDATMConfig();
				logMsg = "UDATM is being configured using: " + dspropsfile.getAbsolutePath();
				mergeProperties(PropertiesUtil.getProperties(dspropsfile.getAbsolutePath()), false);
			} catch (Exception e) {
				logMsg = "UDATM is being configured using the default configuration file that is packaged with it: "
						+ UDATM_CONFIG_NAME;
				mergeProperties(PropertiesUtil.getProperties(UDATM_CONFIG_NAME), false);
			}
			// Check to see if we have a Log locations specified and set it.
			String logLoc = getProperty(LOG_FILE_LOCATION_KEY);
			if (logLoc != null) {
				// Log4j picks this up in the Sys props, so set it there.
				System.setProperty(LOG_FILE_LOCATION_KEY, logLoc);
			} else {
				// Default to the tempdir if not set
				System.setProperty(LOG_FILE_LOCATION_KEY, System.getProperty("java.io.tmpdir"));
			}
		} catch (Exception e) {
			// Don't really want to abort here, because the rest of my code will try to
			// recover.
			// I don't have a logger at this time, so just dump the stack trace.
			e.printStackTrace();
		} finally {
			logger = LogManager.getLogger(UDATMHelper.class.getName());
			logger.info(logMsg);
		}
	}

	/**
	 * Returns the UDATM bootstrap configuration file
	 *
	 * @throws NoDataFoundException
	 */
	public static File getUDATMConfig() throws NoDataFoundException {
		try {
			File dsProps = new File(System.getenv(UDATM_HOME_DIR_KEY), UDATM_CONFIG_NAME);
			if (dsProps.exists() && dsProps.isFile()) {
				return dsProps;
			}
		} catch (Exception e) {
		}
		try {
			File dsProps = new File(System.getProperty("user.dir"), UDATM_CONFIG_NAME);
			if (dsProps.exists() && dsProps.isFile()) {
				return dsProps;
			}
		} catch (Exception e) {
		}
		throw new NoDataFoundException("Failed to find UDATM configuration file");
	}

	public static boolean refreshCache() {
		String refreshCache = getProperty(REFRESH_CACHE_KEY);
		if (refreshCache == null) {
			return false;
		}
		return Boolean.parseBoolean(refreshCache);
	}

	public static Properties getProperties() {
		synchronized (udatmProperties) {
			return udatmProperties;
		}
	}

	public static ArrayList<String> getPropertyKeysWithPrefix(String prefix) {
		ArrayList<String> keys = new ArrayList<>();
		for (String key : getProperties().stringPropertyNames()) {
			if (key.startsWith(prefix))
				keys.add(key);
		}
		return keys;
	}

	public static String getProperty(String key) {
		synchronized (udatmProperties) {
			return udatmProperties.getProperty(key);
		}
	}

	public static String setProperty(String key, String value) {
		synchronized (udatmProperties) {
			return (String) udatmProperties.setProperty(key, value);
		}
	}

	public static String removeProperty(String key) {
		synchronized (udatmProperties) {
			return (String) udatmProperties.remove(key);
		}
	}

	public static void mergeProperties(Properties props, boolean replace) {
		synchronized (udatmProperties) {
			Enumeration<Object> keys = props.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = (String) props.get(key);
				if (replace) {
					setProperty(key, value);
				} else if (getProperty(key) == null) {
					setProperty(key, value);
				}
			}
		}
	}

	/**
	 * Return a file object representing software install directory
	 *
	 * @throws FileNotFoundException
	 */
	public static File getHomeDirectory() throws FileNotFoundException {
		try {
			// First try and get home from ENV variable
			String udmHome = System.getenv(UDATM_HOME_DIR_KEY);
			// Last try and get home from property
			if (udmHome == null) {
				udmHome = System.getProperty(UDATM_HOME_DIR_KEY);
			}
			if (udmHome == null) {
				throw new FileNotFoundException(UDATM_HOME_DIR_KEY + " : is not set");
			}
			File udmHomeFile = new File(udmHome);
			if (!udmHomeFile.exists())
				throw new FileNotFoundException(udmHomeFile.getAbsolutePath() + " : does not exist");
			if (!udmHomeFile.isDirectory())
				throw new FileNotFoundException(udmHomeFile.getAbsolutePath() + " : is not a directory");
			return udmHomeFile;
		} catch (FileNotFoundException fnfe) {
			throw fnfe;
		} catch (Exception e) {
			throw new FileNotFoundException(e.toString());
		}
	}

	/**
	 * Translates a byte array into a string of Hex characters.<br>
	 * Each byte is represented as a two character String<br>
	 * e.g., 0x00 = 00 and 0xff = ff (OOff...)
	 *
	 * @param bytes The byte array
	 * @return A Hex string
	 */
	public static String getHexStringFromByteArrayxxx(byte[] bytes) {
		StringBuilder buf = new StringBuilder();
		for (byte element : bytes) {
			/*
			 * output a binary byte as hex. Only operations are available in the Integer
			 * class, which take an integer, so we want to mask off the sign extension when
			 * a byte is promoted to an int (hence the bit-wise AND with 0xFF). But we also
			 * want two hex characters, so we add 0x100 to insure a leading 1, (which will
			 * result in the insertion of a 0 if the byte is <=15). This leading 1 is not
			 * output because of the substring(1) command.
			 */
			buf.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
		}
		return buf.toString();
	}

	/**
	 * Creates a byte array from the Hex string.<br>
	 * Each Byte is made from two Hex characters.
	 *
	 * @param hexString Hex string
	 * @return byte array
	 * @throws NumberFormatException
	 */
	public static byte[] getByteArrayFromHexString(String hexString) throws NumberFormatException {

		byte bArray[] = new byte[hexString.length() / 2];
		for (int i = 0; i < (hexString.length() / 2); i++) {
			byte firstNibble = Byte.parseByte(hexString.substring(2 * i, 2 * i + 1), 16); // [x,y)
			byte secondNibble = Byte.parseByte(hexString.substring(2 * i + 1, 2 * i + 2), 16);
			// bit-operations only with numbers, not bytes.
			int finalByte = (secondNibble) | (firstNibble << 4);
			bArray[i] = (byte) finalByte;
		}
		return bArray;
	}

	/**
	 * A reversible string character encoding for bytes. Use as needed to encode
	 * bytes with, e.g., <tt>new String(bytesArray, BYTE_ENCODING)</tt> or to decode
	 * bytes with, e.g., <tt>bytesString.getBytes(BYTE_ENCODING)</tt>.
	 */
	public static final String BYTE_ENCODING = "ISO-8859-1";

}
