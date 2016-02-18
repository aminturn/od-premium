package com.trubeacon.cloverandroidapi.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.Employee;

public class Order implements Identifiable {

	public static final String[] FILTERABLE_FIELDS = new String[] {
			"total",
			"deletedTime",
			"clientCreatedTime",
			"orderType",
			"createdTime",
			"employee.name",
			"modifiedTime",
			"manualTransaction",
			"state",
			"id",
			"title",
			"device.id",
			"testMode",
			"employee.id",
			"payType",
			"customer.id",
			"note"
	};

	public static final String[] EXPANDABLE_FIELDS = new String[]{
			"lineItems",
			"lineItems.item",
			"lineItems.item.tags",
			"lineItems.modifications",
			"orderType"
	};

	@JsonProperty
	private String id;
	@JsonProperty
	private String title;
	@JsonProperty
	private long total;
	@JsonProperty
	private String state;
	@JsonProperty
	private String currency; // TODO: enum this
	@JsonProperty
	private boolean testMode;
	@JsonProperty
	private Employee employee;
	@JsonProperty
	private WrappedList<LineItem> lineItems;
	@JsonProperty
	private String note;
	@JsonProperty
	private long clientCreatedTime;
	@JsonProperty
	private OrderType orderType;
	@JsonProperty
	private long createdTime;
	@JsonProperty
	private long modifiedTime;
	@JsonProperty
	private boolean manualTransaction;
	@JsonProperty
	private Reference device;
	@JsonProperty
	private boolean groupLineItems;
	@JsonProperty
	private PayType payType;
	@JsonProperty("isVat")
	private boolean isVat;
	@JsonProperty
	private boolean taxRemoved;

	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public boolean isTestMode() {
		return testMode;
	}
	public void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}


	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public WrappedList<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(WrappedList<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public long getClientCreatedTime() {
		return clientCreatedTime;
	}
	public void setClientCreatedTime(long clientCreatedTime) {
		this.clientCreatedTime = clientCreatedTime;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
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
	public boolean isManualTransaction() {
		return manualTransaction;
	}
	public void setManualTransaction(boolean manualTransaction) {
		this.manualTransaction = manualTransaction;
	}

	public Reference getDevice() {
		return device;
	}
	public void setDevice(Reference device) {
		this.device = device;
	}
	public boolean isGroupLineItems() {
		return groupLineItems;
	}
	public void setGroupLineItems(boolean groupLineItems) {
		this.groupLineItems = groupLineItems;
	}
	public PayType getPayType() {
		return payType;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public boolean isVat() {
		return isVat;
	}
	public void setVat(boolean isVat) {
		this.isVat = isVat;
	}
	public boolean isTaxRemoved() {
		return taxRemoved;
	}
	public void setTaxRemoved(boolean taxRemoved) {
		this.taxRemoved = taxRemoved;
	}

	public enum PayType {

		SPLIT_GUEST,
		SPLIT_ITEM,
		SPLIT_CUSTOM,
		FULL;

		@JsonCreator
		public static PayType fromString(String payTypeString) {
			PayType payType = null;
			for (PayType p : PayType.values()) {
				if (p.toString().equalsIgnoreCase(payTypeString)) {
					payType = p;
					break;
				}
			}
			return payType;
		}

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
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
