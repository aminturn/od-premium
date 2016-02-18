package com.trubeacon.cloverandroidapi.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;
import com.trubeacon.cloverandroidapi.common.Copyable;

public class TenderSummary implements Accumulateable<TenderSummary>, Copyable<TenderSummary> {

	@JsonProperty
	private String id;
	@JsonProperty
	private SummaryObject summaryObject;
	@JsonProperty
	private PaymentSummary paymentsSummary;
	@JsonProperty
	private CreditSummary creditsSummary;
	@JsonProperty
	private RefundSummary refundsSummary;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getLabel() {
		return (this.summaryObject != null && this.summaryObject.getLabel() != null) ? this.summaryObject.getLabel() : "Unknown";
	}
	@JsonProperty
	public String getLabelKey() {
		return (this.summaryObject != null && this.summaryObject.getLabelKey() != null) ? this.summaryObject.getLabelKey() : "Unknown";
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
	    result = prime * result
	            + ((getLabelKey() == null) ? 0 : getLabelKey().hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (!(obj instanceof TenderSummary))
		    return false;
	    TenderSummary other = (TenderSummary) obj;
	    if (getLabel() == null) {
		    if (other.getLabel() != null)
			    return false;
	    } else if (!getLabel().equals(other.getLabel()))
		    return false;
	    if (getLabelKey() == null) {
		    if (other.getLabelKey() != null)
			    return false;
	    } else if (!getLabelKey().equals(other.getLabelKey()))
		    return false;
	    return true;
    }
	
	public static class SummaryObject implements Copyable<SummaryObject> {
		
		@JsonProperty
		private boolean editable;
		@JsonProperty
		private String labelKey;
		@JsonProperty
		private String label;
		@JsonProperty
		private boolean opensCashDrawer;
		@JsonProperty
		private boolean enabled;
		@JsonProperty
		private boolean visible;
		
		public boolean isEditable() {
			return editable;
		}
		public void setEditable(boolean editable) {
			this.editable = editable;
		}
		public String getLabelKey() {
			return labelKey;
		}
		public void setLabelKey(String labelKey) {
			this.labelKey = labelKey;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public boolean isOpensCashDrawer() {
			return opensCashDrawer;
		}
		public void setOpensCashDrawer(boolean opensCashDrawer) {
			this.opensCashDrawer = opensCashDrawer;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		public boolean isVisible() {
			return visible;
		}
		public void setVisible(boolean visible) {
			this.visible = visible;
		}
		
		@Override
        public SummaryObject copy() {
			SummaryObject summary = new SummaryObject();
			summary.setEditable(this.editable);
			summary.setLabelKey(this.labelKey);
			summary.setLabel(this.label);
			summary.setOpensCashDrawer(this.opensCashDrawer);
			summary.setEnabled(this.enabled);
			summary.setVisible(this.visible);
	        return summary;
        }
	
	}

	@Override
    public void accumulate(TenderSummary toAccumulate) {
	    if (toAccumulate != null) {
	    	this.paymentsSummary.accumulate(toAccumulate.getPaymentsSummary());
	    	this.creditsSummary.accumulate(toAccumulate.getCreditsSummary());
	    	this.refundsSummary.accumulate(toAccumulate.getRefundsSummary());
	    }
    }
	
	@Override
    public TenderSummary copy() {
		TenderSummary t = new TenderSummary();
		t.setId(this.id);
		t.setSummaryObject((this.summaryObject != null) ? this.summaryObject.copy() : null);
		t.setPaymentsSummary((this.paymentsSummary != null) ? this.paymentsSummary.copy() : null);
		t.setCreditsSummary((this.creditsSummary != null) ? this.creditsSummary.copy() : null);
		t.setRefundsSummary((this.refundsSummary != null) ? this.refundsSummary.copy() : null);
		return t;
    }

}
