package com.trubeacon.cloverandroidapi.merchant;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Devices {

	@JsonProperty
	private Set<Device> devices;
	@JsonProperty
	private Set<DeviceType> types;
	
	public Set<Device> getDevices() {
		return devices;
	}
	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}
	public Set<DeviceType> getTypes() {
		return types;
	}
	public void setTypes(Set<DeviceType> types) {
		this.types = types;
	}
	
}
