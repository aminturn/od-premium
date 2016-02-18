package com.trubeacon.cloverandroidapi.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Address;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class Customer {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {
		"id", "deletedTime", "lastName", "order.id", "customerSince", "fullName", "marketingAllowed", "firstName"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[]{"addresses", "emailAddresses", "phoneNumbers"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String lastName;
	@JsonProperty
	private long customerSince;
	@JsonProperty
	private WrappedList<PhoneNumber> phoneNumbers;
	@JsonProperty
	private WrappedList<Address> addresses;
	@JsonProperty
	private boolean marketingAllowed;
	@JsonProperty
	private String firstName;
	@JsonProperty
	private WrappedList<EmailAddress> emailAddresses; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getCustomerSince() {
		return customerSince;
	}
	public void setCustomerSince(long customerSince) {
		this.customerSince = customerSince;
	}
	public WrappedList<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(WrappedList<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public WrappedList<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(WrappedList<Address> addresses) {
		this.addresses = addresses;
	}
	public boolean isMarketingAllowed() {
		return marketingAllowed;
	}
	public void setMarketingAllowed(boolean marketingAllowed) {
		this.marketingAllowed = marketingAllowed;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public WrappedList<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}
	public void setEmailAddresses(WrappedList<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
			
}
