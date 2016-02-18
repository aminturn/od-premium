package com.trubeacon.cloverandroidapi.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;
import com.trubeacon.cloverandroidapi.common.Copyable;
import com.trubeacon.cloverandroidapi.common.Role;
import com.trubeacon.cloverandroidapi.common.SystemRole;
import com.trubeacon.cloverandroidapi.payment.CreditSummary;
import com.trubeacon.cloverandroidapi.payment.PaymentSummary;
import com.trubeacon.cloverandroidapi.payment.RefundSummary;

public class EmployeeSummary implements Accumulateable<EmployeeSummary>, Copyable<EmployeeSummary> {

	@JsonProperty
	private String id;
	@JsonProperty
	private SummaryObject summaryObject;
	@JsonProperty
	private PaymentSummary paymentsSummary;
	@JsonProperty
	private RefundSummary refundsSummary;
	@JsonProperty
	private CreditSummary creditsSummary;
		
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
	public RefundSummary getRefundsSummary() {
		return refundsSummary;
	}
	public void setRefundsSummary(RefundSummary refundsSummary) {
		this.refundsSummary = refundsSummary;
	}
	public CreditSummary getCreditsSummary() {
		return creditsSummary;
	}
	public void setCreditsSummary(CreditSummary creditsSummary) {
		this.creditsSummary = creditsSummary;
	}
	
	@JsonProperty
	public String getName() {
		return (this.summaryObject != null && this.summaryObject.getName() != null) ? this.summaryObject.getName() : "Unknown";
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (!(obj instanceof EmployeeSummary))
		    return false;
	    EmployeeSummary other = (EmployeeSummary) obj;
	    if (getName() == null) {
		    if (other.getName() != null)
			    return false;
	    } else if (!getName().equals(other.getName()))
		    return false;
	    return true;
    }
	
	public static class SummaryObject implements Copyable<SummaryObject> {
		
		@JsonProperty
		private String id;
		@JsonProperty
		private String name;
		@JsonProperty
		private String nickname;
		@JsonProperty
		private String email;
		@JsonProperty
		private boolean inviteSent;
		@JsonProperty
		private long claimedTime;
		@JsonProperty
		private SystemRole role;
		
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
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public boolean isInviteSent() {
			return inviteSent;
		}
		public void setInviteSent(boolean inviteSent) {
			this.inviteSent = inviteSent;
		}
		public long getClaimedTime() {
			return claimedTime;
		}
		public void setClaimedTime(long claimedTime) {
			this.claimedTime = claimedTime;
		}
		public SystemRole getRole() {
			return role;
		}
		public void setRole(SystemRole role) {
			this.role = role;
		}
		
		@Override
        public SummaryObject copy() {
			SummaryObject copy = new SummaryObject();
			copy.setId(this.id);
			copy.setName(this.name);
			copy.setNickname(this.nickname);
			copy.setEmail(this.email);
			copy.setInviteSent(this.inviteSent);
			copy.setClaimedTime(this.claimedTime);
			copy.setRole(this.role);
	        return copy;
        }
		
	}

	@Override
    public void accumulate(EmployeeSummary toAccumulate) {
	    if (toAccumulate != null) {
	    	this.paymentsSummary.accumulate(toAccumulate.getPaymentsSummary());
	    	this.creditsSummary.accumulate(toAccumulate.getCreditsSummary());
	    	this.refundsSummary.accumulate(toAccumulate.getRefundsSummary());
	    }
    }
	
	@Override
    public EmployeeSummary copy() {
		EmployeeSummary copy = new EmployeeSummary();
		copy.setId(this.id);
		copy.setCreditsSummary((this.creditsSummary != null) ? this.creditsSummary.copy() : null);
		copy.setPaymentsSummary((this.paymentsSummary != null) ? this.paymentsSummary.copy() : null);
		copy.setRefundsSummary((this.refundsSummary != null) ? this.refundsSummary.copy() : null);
		copy.setSummaryObject((this.summaryObject != null) ? this.summaryObject.copy() : null);
	    return copy;
    }
		
}
