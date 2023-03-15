package com.shinysideup.udatm.lib.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

/**
 * @author Mike Worley
 */
public class PropertiesUtil {

	private final String propertiesFileName;

	public PropertiesUtil(String propertiesFileName) {
		this.propertiesFileName = propertiesFileName;
	}

	public Properties getProperties() {
		return getProperties(this.propertiesFileName);
	}

	public String getPropertyValue(String propertyName) {
		return this.getProperties().getProperty(propertyName);
	}

	public boolean saveProperty(String propertyName, String propertyValue) {
		Properties properties = this.getProperties();
		properties.setProperty(propertyName, propertyValue);
		return saveProperties(properties, this.propertiesFileName);
	}

	public static Properties getProperties(String propertiesFileName) {
		if (propertiesFileName == null) {
			return new Properties();
		}
		InputStream inputStream = null;
		try {
			// Try to get properties from a file
			inputStream = new FileInputStream(propertiesFileName);
		} catch (Exception e) {
			// Try to get properties from a resource jar,war,classpath,etc...
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFileName);
		}
		try {
			Properties properties = new Properties();
			properties.load(inputStream);
			inputStream.close();
			inputStream = null;
			return properties;
		} catch (Exception e) {
			inputStream = null;
			return new Properties();
		}

	}

	public static Properties getPropertiesFromString(String props) throws Exception {
		Properties properties = new Properties();
		StringReader stringReader = new StringReader(props);
		properties.load(stringReader);
		return properties;
	}

	public static boolean saveProperties(Properties properties, String propsName) {
		try {
			properties.store(new FileOutputStream(propsName), propsName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
