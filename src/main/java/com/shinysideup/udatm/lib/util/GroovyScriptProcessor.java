package com.shinysideup.udatm.lib.util;

import java.util.Hashtable;
import java.util.Map;

import com.shinysideup.udatm.lib.GeneralException;
import com.shinysideup.udatm.lib.NoDataFoundException;
import com.shinysideup.udatm.lib.Result;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author Mike Worley
 */
public class GroovyScriptProcessor implements ScriptProcessor {

	private final String script;

	private final Map<String, Object> parameters;

	private final Result result;

	private String errorMessage;

	public GroovyScriptProcessor(String script, Map<String, Object> parameters) throws NoDataFoundException {
		if (script == null)
			throw new NoDataFoundException("Script can't be null");
		this.script = script;
		if (parameters == null) {
			this.parameters = new Hashtable<>();
		} else {
			this.parameters = parameters;
		}
		this.result = new Result();
		this.parameters.put(ScriptProcessor.SCRIPT_RESULT_KEY, this.result);
	}

	public void run() {
		try {
			Object scriptResult = runGroovyScript(script, parameters);
			if (scriptResult != null) {
				this.result.setResult(scriptResult.toString());
			}
		} catch (GeneralException e) {
			this.errorMessage = "Script terminated : " + e.getLocalizedMessage();
		} finally {
			this.result.setComplete(true);
		}
	}

	public String getLastErrorMessage() {
		try {
			return this.errorMessage;
		} finally {
			this.errorMessage = null;
		}
	}

	public Result getCurrentResult() {
		Result result = new Result();
		result.setComplete(this.result.isComplete());
		result.setResult(this.result.getResult());
		this.result.setComplete(false);
		this.result.setResult(null);
		return result;
	}

	@Override
	public Map<String, Object> getParameters() {
		return this.getParameters();
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
