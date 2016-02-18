package com.trubeacon.cloverandroidapi.merchant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gateway {

	@JsonProperty
	private boolean supportsTipping;
	@JsonProperty
	private String accountName;
	@JsonProperty
	private boolean supportsNakedCredit;
	@JsonProperty
	private boolean supportsTipAdjust;
	@JsonProperty
	private String paymentGatewayApi;
	@JsonProperty
	private String backendMid;
	@JsonProperty
	private String tid;
	@JsonProperty
	private String mid;
	@JsonProperty
	private String paymentProcessorName;
	@JsonProperty
	private String storeId;
	
	public boolean isSupportsTipping() {
		return supportsTipping;
	}
	public void setSupportsTipping(boolean supportsTipping) {
		this.supportsTipping = supportsTipping;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public boolean isSupportsNakedCredit() {
		return supportsNakedCredit;
	}
	public void setSupportsNakedCredit(boolean supportsNakedCredit) {
		this.supportsNakedCredit = supportsNakedCredit;
	}
	public boolean isSupportsTipAdjust() {
		return supportsTipAdjust;
	}
	public void setSupportsTipAdjust(boolean supportsTipAdjust) {
		this.supportsTipAdjust = supportsTipAdjust;
	}
	public String getPaymentGatewayApi() {
		return paymentGatewayApi;
	}
	public void setPaymentGatewayApi(String paymentGatewayApi) {
		this.paymentGatewayApi = paymentGatewayApi;
	}
	public String getBackendMid() {
		return backendMid;
	}
	public void setBackendMid(String backendMid) {
		this.backendMid = backendMid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPaymentProcessorName() {
		return paymentProcessorName;
	}
	public void setPaymentProcessorName(String paymentProcessorName) {
		this.paymentProcessorName = paymentProcessorName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
		
}
