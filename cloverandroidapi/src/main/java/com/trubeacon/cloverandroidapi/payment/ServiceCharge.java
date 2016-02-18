package com.trubeacon.cloverandroidapi.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceCharge {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private long amount;
	
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
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}	
	
}
