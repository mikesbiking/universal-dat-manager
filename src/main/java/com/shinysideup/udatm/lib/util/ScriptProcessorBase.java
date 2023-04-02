package com.shinysideup.udatm.lib.util;

import java.util.Hashtable;
import java.util.Map;

import com.shinysideup.udatm.lib.GeneralException;
import com.shinysideup.udatm.lib.NoDataFoundException;
import com.shinysideup.udatm.lib.Result;

/**
 * @author Mike Worley
 */
public abstract class ScriptProcessorBase implements ScriptProcessor {

	private final String script;

	private final Map<String, Object> parameters;

	private final Result result;

	private String errorMessage;

	public ScriptProcessorBase(String script, Map<String, Object> parameters) throws NoDataFoundException {
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
			Object scriptResult = runScript(script, parameters);
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

	public abstract Object runScript(String scriptContent, Map<String, Object> parameters) throws GeneralException;

}
