package com.trubeacon.cloverandroidapi.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.Employee;

public class Payment implements Identifiable {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {
		"deletedTime", 
		"result", 
		"order.id", 
		"clientCreatedTime", 
		"createdTime", 
		"modifiedTime", 
		"cardType", 
		"paymentRefundId", 
		"offline", 
		"id", 
		"amount", 
		"cashTendered", 
		"tipAmount", 
		"order.modifiedTime", 
		"taxAmount", 
		"device.id", 
		"employee.id", 
		"merchantId", 
		"cardTransaction.state", 
		"tender.id", 
		"externalPaymentId"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[]{"cardTransaction", "taxRates", "lineItemPayments", "refunds", "order", "tender", "employee"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private Result result;
	@JsonProperty
	private long clientCreatedTime;
	@JsonProperty
	private long createdTime;
	@JsonProperty
	private long modifiedTime;
	@JsonProperty
	private WrappedList<PaymentTaxRate> taxRates;
	@JsonProperty
	private CardTransaction cardTransaction;
	@JsonProperty
	private boolean offline;
	@JsonProperty
	private long amount;
	@JsonProperty
	private long cashTendered;
	@JsonProperty
	private long tipAmount;
	@JsonProperty
	private WrappedList<LineItemPayment> lineItemPayments;
	@JsonProperty
	private long taxAmount;
	@JsonProperty
	private Reference order;
	@JsonProperty
	private Reference device;
	@JsonProperty
	private Tender tender;
	@JsonProperty
	private WrappedList<Reference> refunds;
	@JsonProperty
	private Employee employee;
	@JsonProperty
	private ServiceCharge serviceCharge;
	@JsonProperty
	private String externalPaymentId;
	@JsonProperty
	private String note;
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
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
	public long getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public WrappedList<PaymentTaxRate> getTaxRates() {
		return taxRates;
	}
	public void setTaxRates(WrappedList<PaymentTaxRate> taxRates) {
		this.taxRates = taxRates;
	}
	public CardTransaction getCardTransaction() {
		return cardTransaction;
	}
	public void setCardTransaction(CardTransaction cardTransaction) {
		this.cardTransaction = cardTransaction;
	}
	public boolean isOffline() {
		return offline;
	}
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getCashTendered() {
		return cashTendered;
	}
	public void setCashTendered(long cashTendered) {
		this.cashTendered = cashTendered;
	}
	public long getTipAmount() {
		return tipAmount;
	}
	public void setTipAmount(long tipAmount) {
		this.tipAmount = tipAmount;
	}
	public WrappedList<LineItemPayment> getLineItemPayments() {
		return lineItemPayments;
	}
	public void setLineItemPayments(WrappedList<LineItemPayment> lineItemPayments) {
		this.lineItemPayments = lineItemPayments;
	}
	public long getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(long taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Reference getOrder() {
		return order;
	}
	public void setOrder(Reference order) {
		this.order = order;
	}
	public Reference getDevice() {
		return device;
	}
	public void setDevice(Reference device) {
		this.device = device;
	}
	public Tender getTender() {
		return tender;
	}
	public void setTender(Tender tender) {
		this.tender = tender;
	}
	public WrappedList<Reference> getRefunds() {
		return refunds;
	}
	public void setRefunds(WrappedList<Reference> refunds) {
		this.refunds = refunds;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public ServiceCharge getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(ServiceCharge serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getExternalPaymentId() {
		return externalPaymentId;
	}
	public void setExternalPaymentId(String externalPaymentId) {
		this.externalPaymentId = externalPaymentId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}	
	
	public enum Result {
		
		SUCCESS,
		FAIL,
		INITIATED,
		VOIDED,
		VOIDING;
		
		@JsonCreator
		public static Result fromString(String resultString) {
			Result result = null;
			for (Result r : Result.values()) {
				if (r.toString().equalsIgnoreCase(resultString)) {
					result = r;
					break;
				}
			}
			return result;
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
	    if (!(obj instanceof Payment))
		    return false;
	    Payment other = (Payment) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
}
