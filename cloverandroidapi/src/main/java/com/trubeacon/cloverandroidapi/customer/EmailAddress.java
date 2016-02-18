package com.trubeacon.cloverandroidapi.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailAddress {
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String emailAddress;
	@JsonProperty
	private long verifiedTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public long getVerifiedTime() {
		return verifiedTime;
	}
	public void setVerifiedTime(long verifiedTime) {
		this.verifiedTime = verifiedTime;
	}
	
}
