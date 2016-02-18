package com.trubeacon.cloverandroidapi.client.param;

public class Header extends Param<String> {

	public Header(String key, String value) {
		super(key, value);
	}
	
	@Override
	public String toString() {
		return this.key + ": " + ((this.value != null) ? this.value.toString() : null);
	}
	
}
