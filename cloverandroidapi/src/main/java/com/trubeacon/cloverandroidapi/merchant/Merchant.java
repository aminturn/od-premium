package com.trubeacon.cloverandroidapi.merchant;

import java.util.Currency;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Address;
import com.trubeacon.cloverandroidapi.common.HoursSet;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.Employee;
import com.trubeacon.cloverandroidapi.employee.Shift;
import com.trubeacon.cloverandroidapi.inventory.Item;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;
import com.trubeacon.cloverandroidapi.inventory.Tag;
import com.trubeacon.cloverandroidapi.order.Order;
import com.trubeacon.cloverandroidapi.order.OrderType;
import com.trubeacon.cloverandroidapi.payment.Payment;
import com.trubeacon.cloverandroidapi.payment.Tender;
import com.trubeacon.cloverandroidapi.payment.TipSuggestion;

public class Merchant {

	public static final String[] EXPANDABLE_FIELDS = new String[]{"address", "owner", "tenders", "gateway", "printers", "properties", "tipSuggestions", "openingHours"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String phoneNumber;
	@JsonProperty
	private String website;
	@JsonProperty
	private long createdTime; // TODO: json mapping from long to datetime
	@JsonProperty
	private Address address;
	@JsonProperty
	private Properties properties;
	@JsonProperty
	private Gateway gateway;
	@JsonProperty
	private Reference reseller;
	@JsonProperty
	private Currency defaultCurrency;
	@JsonProperty
	private WrappedList<TipSuggestion> tipSuggestions;
	@JsonProperty("opening_hours")
	private WrappedList<HoursSet> openingHours;
	@JsonProperty
	private WrappedList<Printer> printers;
	@JsonProperty
	private WrappedList<Tender> tenders;
	@JsonProperty
	private WrappedList<OrderType> orderTypes;
	@JsonProperty
	private Employee owner;
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Gateway getGateway() {
		return gateway;
	}
	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}
	public Reference getReseller() {
		return reseller;
	}
	public void setReseller(Reference reseller) {
		this.reseller = reseller;
	}
	public Currency getDefaultCurrency() {
		return defaultCurrency;
	}
	public void setDefaultCurrency(Currency defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}
	public WrappedList<TipSuggestion> getTipSuggestions() {
		return tipSuggestions;
	}
	public void setTipSuggestions(WrappedList<TipSuggestion> tipSuggestions) {
		this.tipSuggestions = tipSuggestions;
	}
	public WrappedList<HoursSet> getOpeningHours() {
		return openingHours;
	}
	public void setOpeningHours(WrappedList<HoursSet> openingHours) {
		this.openingHours = openingHours;
	}
	public WrappedList<Printer> getPrinters() {
		return printers;
	}
	public void setPrinters(WrappedList<Printer> printers) {
		this.printers = printers;
	}
	public WrappedList<Tender> getTenders() {
		return tenders;
	}
	public void setTenders(WrappedList<Tender> tenders) {
		this.tenders = tenders;
	}
	public WrappedList<OrderType> getOrderTypes() {
		return orderTypes;
	}
	public void setOrderTypes(WrappedList<OrderType> orderTypes) {
		this.orderTypes = orderTypes;
	}
	public Employee getOwner() {
		return owner;
	}
	public void setOwner(Employee owner) {
		this.owner = owner;
	}
		
}
