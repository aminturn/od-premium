package com.trubeacon.cloverandroidapi.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Reference;

public class AppNotification {

	@JsonProperty
	private Reference app;
	@JsonProperty
	private String event;
	@JsonProperty
	private String data;
	@JsonProperty
	private long timeToLive;
	
	public Reference getApp() {
		return app;
	}
	public void setApp(Reference app) {
		this.app = app;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public long getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}
	
}
