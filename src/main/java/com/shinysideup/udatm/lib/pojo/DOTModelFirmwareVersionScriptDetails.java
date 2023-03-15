package com.shinysideup.udatm.lib.pojo;

import java.util.List;

/**
 * @author Mike Worley
 */
public class DOTModelFirmwareVersionScriptDetails {

	private Long id;

	private String name;

	private List<Parameter> paramaters;

	private boolean resultAsString;

	private Long maxCompletionTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaxCompletionTime() {
		return maxCompletionTime;
	}

	public void setMaxCompletionTime(Long maxCompletionTime) {
		this.maxCompletionTime = maxCompletionTime;
	}

	public List<Parameter> getParamaters() {
		return paramaters;
	}

	public void setParamaters(List<Parameter> paramaters) {
		this.paramaters = paramaters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isResultAsString() {
		return resultAsString;
	}

	public void setResultAsString(boolean resultAsString) {
		this.resultAsString = resultAsString;
	}

}
