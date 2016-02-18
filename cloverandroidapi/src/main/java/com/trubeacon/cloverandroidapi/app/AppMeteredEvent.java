package com.trubeacon.cloverandroidapi.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppMeteredEvent {

	@JsonProperty
	private String id;
	@JsonProperty
	private long count;
	@JsonProperty
	private long createdTime;
	@JsonProperty
	private long modifiedTime;
	@JsonProperty
	private AppMetered appMetered;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public long getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public AppMetered getAppMetered() {
		return appMetered;
	}
	public void setAppMetered(AppMetered appMetered) {
		this.appMetered = appMetered;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (!(obj instanceof AppMeteredEvent))
		    return false;
	    AppMeteredEvent other = (AppMeteredEvent) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
	}
	
}
