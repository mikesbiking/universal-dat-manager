package com.shinysideup.udatm.lib.util;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.shinysideup.udatm.lib.GeneralException;
import com.shinysideup.udatm.lib.NoDataFoundException;

/**
 * @author Mike Worley
 */
public class JavaScriptProcessor extends ScriptProcessorBase {

	public JavaScriptProcessor(String script, Map<String, Object> parameters) throws NoDataFoundException {
		super(script, parameters);
	}

	public Object runScript(String scriptContent, Map<String, Object> parameters) throws GeneralException {
		return runJavaScript(scriptContent, parameters);
	}

	public static Object runJavaScript(String scriptContent, Map<String, Object> parameters) throws GeneralException {
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			if (parameters != null && parameters.size() > 0) {
				for (String key : parameters.keySet()) {
					engine.put(key, parameters.get(key));
				}
			}
			return engine.eval(scriptContent);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
}
