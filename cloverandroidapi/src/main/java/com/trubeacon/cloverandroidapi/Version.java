package com.trubeacon.cloverandroidapi;

public enum Version {

	V1("v1"),
	V2("v2"),
	V3("v3");

	public static final Version CURRENT = Version.V3;
	
	private String label;
	private Version(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label;
	}
		
}
