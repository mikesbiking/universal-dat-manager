package com.shinysideup.udatm.lib.pojo;

import java.util.List;

/**
 * @author Mike Worley
 */
public class DOTModelFirmwareVersionCommandDetails {

	private Long id;

	private String name;

	private List<Parameter> parameters;

	private Long maxCompletionTime = 10000l;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public Long getMaxCompletionTime() {
		return maxCompletionTime;
	}

	public void setMaxCompletionTime(Long maxCompletionTime) {
		this.maxCompletionTime = maxCompletionTime;
	}

}
