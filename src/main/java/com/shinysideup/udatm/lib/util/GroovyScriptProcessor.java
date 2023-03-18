package com.shinysideup.udatm.lib.util;

import java.util.Map;

import com.shinysideup.udatm.lib.GeneralException;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyScriptProcessor {

	public static Object runGroovyScript(String scriptContent, Map<String, Object> objects) throws GeneralException {
		try {
			Binding binding;
			GroovyShell shell;
			if (objects != null && objects.size() > 0) {
				binding = new Binding();
				shell = new GroovyShell(binding);
				for (String key : objects.keySet()) {
					binding.setVariable(key, objects.get(key));
				}
			} else {
				shell = new GroovyShell();
			}
			return shell.evaluate(scriptContent);
		} catch (Exception e) {
			GeneralException ge = new GeneralException(e.getLocalizedMessage());
			ge.setStackTrace(e.getStackTrace());
			throw ge;
		}
	}

}
