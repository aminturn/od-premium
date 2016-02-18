package com.trubeacon.cloverandroidapi.merchant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceType {

	@JsonProperty
	private long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String displayName;
	@JsonProperty
	private boolean goldleaf;
	@JsonProperty
	private boolean mapleCutter;
	@JsonProperty
	private String models;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isGoldleaf() {
		return goldleaf;
	}
	public void setGoldleaf(boolean goldleaf) {
		this.goldleaf = goldleaf;
	}
	public boolean isMapleCutter() {
		return mapleCutter;
	}
	public void setMapleCutter(boolean mapleCutter) {
		this.mapleCutter = mapleCutter;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	
}
