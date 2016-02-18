package com.trubeacon.cloverandroidapi.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reference {

	@JsonProperty("id")
	private String id;

	public Reference() {}
	public Reference(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
