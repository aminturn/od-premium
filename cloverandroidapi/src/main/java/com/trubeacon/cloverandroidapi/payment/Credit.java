package com.trubeacon.cloverandroidapi.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.customer.Customer;
import com.trubeacon.cloverandroidapi.employee.Employee;

public class Credit implements Identifiable {

	public static final String[] FILTERABLE_FIELDS = new String[] {
		"order.id", 
		"deletedTime", 
		"clientCreatedTime", 
		"cardType", 
		"id", 
		"amount", 
		"order.modifiedTime", 
		"taxAmount", 
		"device.id", 
		"employee.id", 
		"merchantId", 
		"tender.id"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[]{"tender", "employee", "cardTransaction", "order", "taxRates"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private long amount;
	@JsonProperty
	private long taxAmount;
	@JsonProperty
	private long clientCreatedTime;
	@JsonProperty
	private long createdTime;
	@JsonProperty
	private WrappedList<TaxableAmountRate> taxRates;
	@JsonProperty
	private Tender tender;
	@JsonProperty
	private Reference device;
	@JsonProperty
	private Reference orderRef;
	@JsonProperty
	private Customer customers;
	@JsonProperty
	private Employee employee;
	@JsonProperty
	private CardTransaction cardTransaction;
	
	@Override
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
	public long getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(long taxAmount) {
		this.taxAmount = taxAmount;
	}
	public long getClientCreatedTime() {
		return clientCreatedTime;
	}
	public void setClientCreatedTime(long clientCreatedTime) {
		this.clientCreatedTime = clientCreatedTime;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public WrappedList<TaxableAmountRate> getTaxRates() {
		return taxRates;
	}
	public void setTaxRates(WrappedList<TaxableAmountRate> taxRates) {
		this.taxRates = taxRates;
	}
	public Tender getTender() {
		return tender;
	}
	public void setTender(Tender tender) {
		this.tender = tender;
	}
	public Reference getDevice() {
		return device;
	}
	public void setDevice(Reference device) {
		this.device = device;
	}
	public Reference getOrderRef() {
		return orderRef;
	}
	public void setOrderRef(Reference orderRef) {
		this.orderRef = orderRef;
	}
	public Customer getCustomers() {
		return customers;
	}
	public void setCustomers(Customer customers) {
		this.customers = customers;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public CardTransaction getCardTransaction() {
		return cardTransaction;
	}
	public void setCardTransaction(CardTransaction cardTransaction) {
		this.cardTransaction = cardTransaction;
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
	    if (!(obj instanceof Credit))
		    return false;
	    Credit other = (Credit) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
		
}
