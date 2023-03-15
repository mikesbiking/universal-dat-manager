package com.shinysideup.udatm.lib.util;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shinysideup.udatm.lib.GeneralException;

public class JsonUtil {

	public static <T> T deserialize(String json, Class<T> valueType) throws GeneralException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(json, valueType);
		} catch (Exception e) {
			throw new GeneralException(e.getLocalizedMessage());
		}
	}

	public static <T> ArrayList<T> deserializeArrayList(String json, Class<T> valueType) throws GeneralException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, valueType);
			return objectMapper.readValue(json, listType);
		} catch (Exception e) {
			throw new GeneralException(e.getLocalizedMessage());
		}
	}

	public static String serialize(Object obj) throws GeneralException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			throw new GeneralException(e.getLocalizedMessage());
		}
	}
}
