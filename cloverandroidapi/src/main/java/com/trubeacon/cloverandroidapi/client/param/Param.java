package com.trubeacon.cloverandroidapi.client.param;

public abstract class Param<T> {
	
	protected final String key;
	protected final T value;
		
	public Param(String key, T value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public T getValue() {
		return value;
	}
	
	public static QueryParam query(String key, Object value) {
		return new QueryParam(key, value);
	}
	
	public static PathParam path(String key, String path) {
		return new PathParam(key, path);
	}
	
	public static Header header(String key, String value) {
		return new Header(key, value);
	}
	
}
