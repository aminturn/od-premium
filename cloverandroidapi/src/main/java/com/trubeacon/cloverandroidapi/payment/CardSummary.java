package com.trubeacon.cloverandroidapi.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;
import com.trubeacon.cloverandroidapi.common.Copyable;
import com.trubeacon.cloverandroidapi.payment.CardTransaction.CardType;
import com.trubeacon.cloverandroidapi.payment.TenderSummary.SummaryObject;

public class CardSummary implements Accumulateable<CardSummary>, Copyable<CardSummary> {

	@JsonProperty
	private SummaryObject summaryObject;
	@JsonProperty
	private PaymentSummary paymentsSummary;
	@JsonProperty
	private CreditSummary creditsSummary;
	@JsonProperty
	private RefundSummary refundsSummary;	
	
	public SummaryObject getSummaryObject() {
		return summaryObject;
	}
	public void setSummaryObject(SummaryObject summaryObject) {
		this.summaryObject = summaryObject;
	}
	public PaymentSummary getPaymentsSummary() {
		return paymentsSummary;
	}
	public void setPaymentsSummary(PaymentSummary paymentsSummary) {
		this.paymentsSummary = paymentsSummary;
	}
	public CreditSummary getCreditsSummary() {
		return creditsSummary;
	}
	public void setCreditsSummary(CreditSummary creditsSummary) {
		this.creditsSummary = creditsSummary;
	}
	public RefundSummary getRefundsSummary() {
		return refundsSummary;
	}
	public void setRefundsSummary(RefundSummary refundsSummary) {
		this.refundsSummary = refundsSummary;
	}
	
	@JsonProperty
	public String getType() {
		return (this.summaryObject != null && this.summaryObject.getType() != null) ? this.summaryObject.getType().pretty() : CardType.UNKNOWN.pretty();
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
	    return result;
    }
	
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (!(obj instanceof CardSummary))
		    return false;
	    CardSummary other = (CardSummary) obj;
	    if (getType() != other.getType())
		    return false;
	    return true;
    }
	
	public static class SummaryObject implements Copyable<SummaryObject> {
		
		@JsonProperty
		private CardType type;

		public CardType getType() {
			return type;
		}
		public void setType(CardType type) {
			this.type = type;
		}
		
		@Override
        public SummaryObject copy() {
			SummaryObject s = new SummaryObject();
			s.setType(this.type);
	        return s;
        }
		
	}

	@Override
    public void accumulate(CardSummary toAccumulate) {
	    if (toAccumulate != null) {
	    	this.paymentsSummary.accumulate(toAccumulate.getPaymentsSummary());
	    	this.creditsSummary.accumulate(toAccumulate.getCreditsSummary());
	    	this.refundsSummary.accumulate(toAccumulate.getRefundsSummary());
	    }
    }
	
	@Override
    public CardSummary copy() {
		CardSummary c = new CardSummary();
		c.setCreditsSummary((this.creditsSummary != null) ? this.creditsSummary.copy() : null);
		c.setPaymentsSummary((this.paymentsSummary != null) ? this.paymentsSummary.copy() : null);
		c.setRefundsSummary((this.refundsSummary != null) ? this.refundsSummary.copy() : null);
		c.setSummaryObject((this.summaryObject != null) ? this.summaryObject.copy() : null);
	    return c;
    }
	
}
