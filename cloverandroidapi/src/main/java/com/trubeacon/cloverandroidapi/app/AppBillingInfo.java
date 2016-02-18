package com.trubeacon.cloverandroidapi.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppBillingInfo {

	@JsonProperty
	private long billingStartTime;
	@JsonProperty
	private Status status;
	@JsonProperty
	private boolean isInTrial;
	@JsonProperty
	private long daysLapsed;
	@JsonProperty
	private AppSubscription appSubscription;
	
	public long getBillingStartTime() {
		return billingStartTime;
	}
	public void setBillingStartTime(long billingStartTime) {
		this.billingStartTime = billingStartTime;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public boolean isInTrial() {
		return isInTrial;
	}
	public void setInTrial(boolean isInTrial) {
		this.isInTrial = isInTrial;
	}
	public long getDaysLapsed() {
		return daysLapsed;
	}
	public void setDaysLapsed(long daysLapsed) {
		this.daysLapsed = daysLapsed;
	}
	public AppSubscription getAppSubscription() {
		return appSubscription;
	}
	public void setAppSubscription(AppSubscription appSubscription) {
		this.appSubscription = appSubscription;
	}

	public enum Status {
		
		ACTIVE,
		LAPSED;

		@JsonCreator
		public static Status fromString(String statusString) {
			Status status = null;
			for (Status s : Status.values()) {
				if (s.toString().equalsIgnoreCase(statusString)) {
					status = s;
					break;
				}
			}
			return status;
		}
		
	}
	
}
