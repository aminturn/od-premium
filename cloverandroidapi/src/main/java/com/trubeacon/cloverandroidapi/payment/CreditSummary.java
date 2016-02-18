package com.trubeacon.cloverandroidapi.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;
import com.trubeacon.cloverandroidapi.common.Copyable;

public class CreditSummary implements Accumulateable<CreditSummary>, Copyable<CreditSummary> {

	@JsonProperty
	private long num;
	@JsonProperty
	private long amount;
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
	public long getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(long taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	@Override
    public void accumulate(CreditSummary toAccumulate) {
	    if (toAccumulate != null) {
	    	this.num += toAccumulate.getNum();
	    	this.amount += toAccumulate.getAmount();
	    	this.taxAmount += toAccumulate.getTaxAmount();
	    }
    }
	
	@Override
    public CreditSummary copy() {
		CreditSummary c = new CreditSummary();
		c.setNum(this.num);
		c.setAmount(this.amount);
		c.setTaxAmount(this.taxAmount);
	    return c;
    }
	
}
