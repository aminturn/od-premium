package com.trubeacon.cloverandroidapi.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role implements Identifiable {

	public static final String[] FILTERABLE_FIELDS = new String[] {
		"id", 
		"deletedTime", 
		"modifiedTime", 
		"name", 
		"systemRole"
	};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private com.trubeacon.cloverandroidapi.common.SystemRole systemRole;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public com.trubeacon.cloverandroidapi.common.SystemRole getSystemRole() {
		return systemRole;
	}
	public void setSystemRole(com.trubeacon.cloverandroidapi.common.SystemRole systemRole) {
		this.systemRole = systemRole;
	}
	
}
