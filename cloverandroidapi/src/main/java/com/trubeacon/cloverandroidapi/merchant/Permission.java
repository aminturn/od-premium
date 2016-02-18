package com.trubeacon.cloverandroidapi.merchant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class Permission {

	public static final String[] EXPANDABLE_FIELDS = new String[] {
		"roles"
	};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String label;
	@JsonProperty
	private Reference app;
	@JsonProperty
	private WrappedList<Role> roles;
	
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Reference getApp() {
		return app;
	}
	public void setApp(Reference app) {
		this.app = app;
	}
	public WrappedList<Role> getRoles() {
		return roles;
	}
	public void setRoles(WrappedList<Role> roles) {
		this.roles = roles;
	}

	public static class Role {
		
		@JsonProperty
		private String id;
		@JsonProperty
		private String name;
		@JsonProperty
		private String systemRole;
		
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
		public String getSystemRole() {
			return systemRole;
		}
		public void setSystemRole(String systemRole) {
			this.systemRole = systemRole;
		}
		
	}
	
}
