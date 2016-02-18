package com.trubeacon.cloverandroidapi.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxableAmountRate {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private long taxableAmount;
	@JsonProperty
	private long rate;
	@JsonProperty
	private boolean isVat;
	
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
	public long getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(long taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public long getRate() {
		return rate;
	}
	public void setRate(long rate) {
		this.rate = rate;
	}
	public boolean isVat() {
		return isVat;
	}
	public void setVat(boolean isVat) {
		this.isVat = isVat;
	}
	
}
