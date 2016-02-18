package com.trubeacon.cloverandroidapi.merchant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {

	@JsonProperty
	private String name;
	@JsonProperty
	private String buildType;
	@JsonProperty
	private long createdTime;
	@JsonProperty
	private long deviceTypeId;
	@JsonProperty
	private String deviceTypeName;
	@JsonProperty
	private long merchantId;
	@JsonProperty
	private String merchantUuid;
	@JsonProperty
	private String model;
	@JsonProperty
	private String secureId;
	@JsonProperty
	private String serial;
	@JsonProperty
	private String uuid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public long getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantUuid() {
		return merchantUuid;
	}
	public void setMerchantUuid(String merchantUuid) {
		this.merchantUuid = merchantUuid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSecureId() {
		return secureId;
	}
	public void setSecureId(String secureId) {
		this.secureId = secureId;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
