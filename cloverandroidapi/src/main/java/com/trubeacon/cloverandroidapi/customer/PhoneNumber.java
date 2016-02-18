package com.trubeacon.cloverandroidapi.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneNumber {

	@JsonProperty
	private String id;
	@JsonProperty
	private String phoneNumber;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
