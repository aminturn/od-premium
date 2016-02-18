package com.trubeacon.cloverandroidapi.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LineItemPayment {

	@JsonProperty
	private String id;
	@JsonProperty
	private long percentage;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getPercentage() {
		return percentage;
	}
	public void setPercentage(long percentage) {
		this.percentage = percentage;
	}
	
}
