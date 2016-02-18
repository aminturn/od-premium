package com.trubeacon.cloverandroidapi.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;
import com.trubeacon.cloverandroidapi.common.Copyable;

public class PaymentSummary implements Accumulateable<PaymentSummary>, Copyable<PaymentSummary> {

	@JsonProperty
	private long num;
	@JsonProperty
	private long amount;
	@JsonProperty
	private long tipAmount;
	@JsonProperty
	private long taxAmount;
	
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getTipAmount() {
		return tipAmount;
	}
	public void setTipAmount(long tipAmount) {
		this.tipAmount = tipAmount;
	}
	public long getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(long taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	@Override
    public void accumulate(PaymentSummary toAccumulate) {
	    if (toAccumulate != null) {
	    	this.num += toAccumulate.getNum();
	    	this.amount += toAccumulate.getAmount();
	    	this.tipAmount += toAccumulate.getTipAmount();
	    	this.taxAmount += toAccumulate.getTaxAmount();
	    }
    }
	
	@Override
    public PaymentSummary copy() {
		PaymentSummary p = new PaymentSummary();
		p.setNum(this.num);
		p.setAmount(this.amount);
		p.setTaxAmount(this.taxAmount);
		p.setTipAmount(this.tipAmount);
	    return p;
    }

}
