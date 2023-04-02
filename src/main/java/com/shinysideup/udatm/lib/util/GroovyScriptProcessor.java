package com.shinysideup.udatm.lib.util;

import java.util.Map;

import com.shinysideup.udatm.lib.GeneralException;
import com.shinysideup.udatm.lib.NoDataFoundException;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author Mike Worley
 */
public class GroovyScriptProcessor extends ScriptProcessorBase {

	public GroovyScriptProcessor(String script, Map<String, Object> parameters) throws NoDataFoundException {
		super(script, parameters);
	}

	public Object runScript(String scriptContent, Map<String, Object> parameters) throws GeneralException {
		return runGroovyScript(scriptContent, parameters);
	}

	public static Object runGroovyScript(String scriptContent, Map<String, Object> parameters) throws GeneralException {
		try {
			GroovyShell shell;
			if (parameters != null && parameters.size() > 0) {
				Binding binding = new Binding();
				for (String key : parameters.keySet()) {
					binding.setVariable(key, parameters.get(key));
				}
				shell = new GroovyShell(binding);
			} else {
				shell = new GroovyShell();
			}
			return shell.evaluate(scriptContent);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
}
