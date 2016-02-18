package com.trubeacon.cloverandroidapi.app;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class AppMetered {

	@JsonProperty
	private String id;
	@JsonProperty
	private Reference app;
	@JsonProperty
	private WrappedList<AppMeteredCountry> meteredCountries;
	@JsonProperty
	private String label;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Reference getApp() {
		return app;
	}
	public void setApp(Reference app) {
		this.app = app;
	}
	public WrappedList<AppMeteredCountry> getMeteredCountries() {
		return meteredCountries;
	}
	public void setMeteredCountries(WrappedList<AppMeteredCountry> meteredCountries) {
		this.meteredCountries = meteredCountries;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public static class AppMeteredCountry {
		
		@JsonProperty
		private String id;
		@JsonProperty
		private long amount;
		@JsonProperty
		private long installCountBillable;
		@JsonProperty
		private String description;
		@JsonProperty
		private String name;
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
