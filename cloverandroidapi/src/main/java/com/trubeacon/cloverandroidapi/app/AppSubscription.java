package com.trubeacon.cloverandroidapi.app;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class AppSubscription {

	@JsonProperty
	private String id;
	@JsonProperty
	private Reference app;
	@JsonProperty
	private String label;
	@JsonProperty
	private WrappedList<AppSubscriptionCountry> subscriptionCountries;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Reference getApp() {
		return app;
	}
	public void setApp(Reference app) {
		this.app = app;
	}
	public WrappedList<AppSubscriptionCountry> getSubscriptionCountries() {
		return subscriptionCountries;
	}
	public void setSubscriptionCountries(WrappedList<AppSubscriptionCountry> subscriptionCountries) {
		this.subscriptionCountries = subscriptionCountries;
	}

	public static class AppSubscriptionCountry {
		
		@JsonProperty
		private String id;
		@JsonProperty
		private String name;
		@JsonProperty
		private String description;
		@JsonProperty
		private long amount;
		@JsonProperty
		private long installCountBillable;
		@JsonProperty
		private boolean active;
		@JsonProperty
		private Reference appSubscription;
		@JsonProperty
		private long installCount;
		@JsonProperty
		private String country;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public long getAmount() {
			return amount;
		}
		public void setAmount(long amount) {
			this.amount = amount;
		}
		public long getInstallCountBillable() {
			return installCountBillable;
		}
		public void setInstallCountBillable(long installCountBillable) {
			this.installCountBillable = installCountBillable;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public Reference getAppSubscription() {
			return appSubscription;
		}
		public void setAppSubscription(Reference appSubscription) {
			this.appSubscription = appSubscription;
		}
		public long getInstallCount() {
			return installCount;
		}
		public void setInstallCount(long installCount) {
			this.installCount = installCount;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		
	}
	
}
