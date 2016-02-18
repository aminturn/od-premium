package com.trubeacon.cloverandroidapi.client.error;

import com.fasterxml.jackson.annotation.JsonProperty;

// FIXME: I really have no idea what other properties this has, it's not documented as far as I can tell
public class Error {
	
	@JsonProperty
	private String message;
	private Throwable cause;
	
	public static Error error(String message) {
		return new Error(message);
	}
	
	public static Error error(String message, Throwable throwable) {
		return new Error(message, throwable);
	}
	
	public Error() {}
	public Error(String message) {
		this.message = message;
	}
	public Error(String message, Throwable cause) {
		this.message = message;
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Throwable getCause() {
		return cause;
	}
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
}
