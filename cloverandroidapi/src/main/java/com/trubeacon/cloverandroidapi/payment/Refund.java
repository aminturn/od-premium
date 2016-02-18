package com.trubeacon.cloverandroidapi.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.Employee;

public class Refund implements Identifiable {
	
	public static final String[] EXPANDABLE_FIELDS = new String[]{"payment", "payments.tender", "payment.tender", "payment.cardTransaction", "employee", "overrideMerchantTender", "serviceCharge"};
	
	@JsonProperty("serviceChargeAmount")
	private ServiceCharge serviceCharge;
	@JsonProperty
	private long clientCreatedTime;
	@JsonProperty
	private Payment payment;
	@JsonProperty
	private long createdTime;
	@JsonProperty
	private Tender overrideMerchantTender;
	@JsonProperty
	private String id;
	@JsonProperty
	private long amount;
	@JsonProperty
	private long taxAmount;
	@JsonProperty
	private WrappedList<TaxableAmountRate> taxableAmountRates;
	@JsonProperty
	private Reference device;
	@JsonProperty
	private Reference orderRef;
	@JsonProperty
	private Employee employee;
	@JsonProperty
	private WrappedList<Reference> lineItems;
	
	public ServiceCharge getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(ServiceCharge serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public long getClientCreatedTime() {
		return clientCreatedTime;
	}
	public void setClientCreatedTime(long clientCreatedTime) {
		this.clientCreatedTime = clientCreatedTime;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public Tender getOverrideMerchantTender() {
		return overrideMerchantTender;
	}
	public void setOverrideMerchantTender(Tender overrideMerchantTender) {
		this.overrideMerchantTender = overrideMerchantTender;
	}
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
	public WrappedList<TaxableAmountRate> getTaxableAmountRates() {
		return taxableAmountRates;
	}
	public void setTaxableAmountRates(WrappedList<TaxableAmountRate> taxableAmountRates) {
		this.taxableAmountRates = taxableAmountRates;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public WrappedList<Reference> getLineItems() {
		return lineItems;
	}
	public void setLineItems(WrappedList<Reference> lineItems) {
		this.lineItems = lineItems;
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
	    if (!(obj instanceof Refund))
		    return false;
	    Refund other = (Refund) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
		
}
