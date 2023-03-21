package com.shinysideup.udatm.lib.util;

import java.util.Map;

import com.shinysideup.udatm.lib.Result;

/**
 * @author Mike Worley
 */
public interface ScriptProcessor extends Runnable {

	public String getLastErrorMessage();

	public Result getCurrentResult();

	public Map<String, Object> getParameters();

}
