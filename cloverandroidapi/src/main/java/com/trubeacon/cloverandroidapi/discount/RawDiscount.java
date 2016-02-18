package com.trubeacon.cloverandroidapi.discount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawDiscount {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private Long amount;
	@JsonProperty
	private Long percentage;
	
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getPercentage() {
		return percentage;
	}
	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}
	
}
