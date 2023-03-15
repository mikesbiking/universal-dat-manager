package com.shinysideup.udatm.lib.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.shinysideup.udatm.lib.GeneralException;

/**
 * @author Mike Worley
 */
public class XmlUtil {

	public static <T> T deserialize(String xml, Class<T> valueType) throws GeneralException {
		try {
			ObjectMapper xmlMapper = new XmlMapper();
			return xmlMapper.readValue(xml, valueType);
		} catch (Exception e) {
			throw new GeneralException(e.getLocalizedMessage());
		}
	}

	public static String serialize(Object obj) throws GeneralException {
		try {
			ObjectMapper xmlMapper = new XmlMapper();
			return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			throw new GeneralException(e.getLocalizedMessage());
		}
	}
}
