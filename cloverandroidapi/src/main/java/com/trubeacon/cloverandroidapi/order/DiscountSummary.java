package com.trubeacon.cloverandroidapi.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;

public class DiscountSummary {

	@JsonProperty
	private Report report;
	
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}

	public static class Report {
	
		@JsonProperty
		private List<OrderDiscount> orderDiscounts;
		
		public List<OrderDiscount> getOrderDiscounts() {
			return orderDiscounts;
		}
		public void setOrderDiscounts(List<OrderDiscount> orderDiscounts) {
			this.orderDiscounts = orderDiscounts;
		}

		public static class OrderDiscount implements Accumulateable<OrderDiscount> {
			
			@JsonProperty
			private String name;
			@JsonProperty
			private long numberSold;
			@JsonProperty
			private long revenueSold;
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public long getNumberSold() {
				return numberSold;
			}
			public void setNumberSold(long numberSold) {
				this.numberSold = numberSold;
			}
			public long getRevenueSold() {
				return revenueSold;
			}
			public void setRevenueSold(long revenueSold) {
				this.revenueSold = revenueSold;
			}
			
			@Override
            public void accumulate(OrderDiscount toAccumulate) {
	            if (toAccumulate != null) {
	            	this.numberSold += toAccumulate.getNumberSold();
	            	this.revenueSold += toAccumulate.getRevenueSold();
	            }
            }
			
			@Override
            public int hashCode() {
	            final int prime = 31;
	            int result = 1;
	            result = prime * result
	                    + ((name == null) ? 0 : name.hashCode());
	            return result;
            }
			@Override
            public boolean equals(Object obj) {
	            if (this == obj)
		            return true;
	            if (obj == null)
		            return false;
	            if (!(obj instanceof OrderDiscount))
		            return false;
	            OrderDiscount other = (OrderDiscount) obj;
	            if (name == null) {
		            if (other.name != null)
			            return false;
	            } else if (!name.equals(other.name))
		            return false;
	            return true;
            }
			
		}
		
	}
	
}
