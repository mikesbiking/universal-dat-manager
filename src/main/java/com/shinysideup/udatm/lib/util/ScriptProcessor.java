package com.shinysideup.udatm.lib.util;

import java.util.Map;

import com.shinysideup.udatm.lib.Result;

/**
 * @author Mike Worley
 */
public interface ScriptProcessor extends Runnable {

	public static final String SCRIPT_RESULT_KEY = "scriptResult";

	public String getLastErrorMessage();

	public Result getCurrentResult();

	public Map<String, Object> getParameters();

}
