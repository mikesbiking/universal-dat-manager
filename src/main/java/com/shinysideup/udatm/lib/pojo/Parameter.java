package com.shinysideup.udatm.lib.pojo;

/**
 * @author Mike Worley
 */
public class Parameter {

	private String name;

	private Object value;

	private boolean optional;

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.name + ":\n" + this.value;
	}

}
