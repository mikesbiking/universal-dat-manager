package com.shinysideup.udatm.lib.pojo;

/**
 * @author Mike Worley
 */
public class DOTType {

	private String type;

	private int[] positions = { 1 };

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int[] getPositions() {
		return positions;
	}

	public void setPositions(int[] positions) {
		this.positions = positions;
	}
}
