package com.trubeacon.cloverandroidapi.client.param;

public class QueryParam extends Param<Object> {

	public QueryParam(String key, Object value) {
		super(key, value);
	}
	
	@Override
	public String toString() {
		return this.key + "=" + ((this.value != null) ? this.value.toString() : null);
	}
	
}
