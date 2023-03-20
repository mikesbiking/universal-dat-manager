package com.shinysideup.udatm.lib.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author Mike Worley
 */
public class DATUtil {

	public static Long getRandomLongValue() {
		Random random = new Random();
		return random.nextLong(1000000000);
	}

	public static synchronized String generateUUID() {
		return UUID.randomUUID().toString();
	}
}
