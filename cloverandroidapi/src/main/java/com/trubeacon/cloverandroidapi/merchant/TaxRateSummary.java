package com.trubeacon.cloverandroidapi.merchant;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;

public class TaxRateSummary {

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
		private List<TaxPaymentAmount> taxPaymentAmounts;
		@JsonProperty
		private List<TaxRefundAmount> taxRefundAmounts;
		@JsonProperty
		private List<TaxCreditAmount> taxCreditAmounts;
		
		public List<TaxPaymentAmount> getTaxPaymentAmounts() {
			return taxPaymentAmounts;
		}
		public void setTaxPaymentAmounts(List<TaxPaymentAmount> taxPaymentAmounts) {
			this.taxPaymentAmounts = taxPaymentAmounts;
		}
		public List<TaxRefundAmount> getTaxRefundAmounts() {
			return taxRefundAmounts;
		}
		public void setTaxRefundAmounts(List<TaxRefundAmount> taxRefundAmounts) {
			this.taxRefundAmounts = taxRefundAmounts;
		}
		public List<TaxCreditAmount> getTaxCreditAmounts() {
			return taxCreditAmounts;
		}
		public void setTaxCreditAmounts(List<TaxCreditAmount> taxCreditAmounts) {
			this.taxCreditAmounts = taxCreditAmounts;
		}

		public static class TaxPaymentAmount implements Accumulateable<TaxPaymentAmount> {
		
			@JsonProperty
			private String id;
			@JsonProperty
			private String name;
			@JsonProperty
			private long taxableAmount;
			@JsonProperty
			private long rate;
			@JsonProperty
			private boolean isVat;
			@JsonProperty
			private long taxPaid;
			
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
			public long getTaxableAmount() {
				return taxableAmount;
			}
			public void setTaxableAmount(long taxableAmount) {
				this.taxableAmount = taxableAmount;
			}
			public long getRate() {
				return rate;
			}
			public void setRate(long rate) {
				this.rate = rate;
			}
			public boolean isVat() {
				return isVat;
			}
			public void setVat(boolean isVat) {
				this.isVat = isVat;
			}
			public long getTaxPaid() {
				return taxPaid;
			}
			public void setTaxPaid(long taxPaid) {
				this.taxPaid = taxPaid;
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
	            if (!(obj instanceof TaxPaymentAmount))
		            return false;
	            TaxPaymentAmount other = (TaxPaymentAmount) obj;
	            if (name == null) {
		            if (other.name != null)
			            return false;
	            } else if (!name.equals(other.name))
		            return false;
	            return true;
            }
			
			@Override
            public void accumulate(TaxPaymentAmount toAccumulate) {
	            if (toAccumulate != null) {
	            	this.taxableAmount += toAccumulate.getTaxableAmount();
	            	this.taxPaid += toAccumulate.getTaxPaid();
	            }
            }
			
		}
		
		public static class TaxRefundAmount implements Accumulateable<TaxRefundAmount> {
			
			private String id;
			private String name;
			private long taxableAmount;
			private long rate;
			private boolean isVat;
			private long taxPaid;
			
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
			public long getTaxableAmount() {
				return taxableAmount;
			}
			public void setTaxableAmount(long taxableAmount) {
				this.taxableAmount = taxableAmount;
			}
			public long getRate() {
				return rate;
			}
			public void setRate(long rate) {
				this.rate = rate;
			}
			public boolean isVat() {
				return isVat;
			}
			public void setVat(boolean isVat) {
				this.isVat = isVat;
			}
			public long getTaxPaid() {
				return taxPaid;
			}
			public void setTaxPaid(long taxPaid) {
				this.taxPaid = taxPaid;
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
	            if (!(obj instanceof TaxRefundAmount))
		            return false;
	            TaxRefundAmount other = (TaxRefundAmount) obj;
	            if (name == null) {
		            if (other.name != null)
			            return false;
	            } else if (!name.equals(other.name))
		            return false;
	            return true;
            }
			
			@Override
            public void accumulate(TaxRefundAmount toAccumulate) {
	            if (toAccumulate != null) {
	            	this.taxableAmount += toAccumulate.getTaxableAmount();
	            	this.taxPaid += toAccumulate.getTaxPaid();
	            }
            }

		}

		public static class TaxCreditAmount implements Accumulateable<TaxCreditAmount> {
			
			// ???: I don't actually know if these are the correct fields or not
			
			@JsonProperty
			private String id;
			@JsonProperty
			private String name;
			@JsonProperty
			private long taxableAmount;
			@JsonProperty
			private long rate;
			@JsonProperty
			private boolean isVat;
			@JsonProperty
			private long taxPaid;
			
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
			public long getTaxableAmount() {
				return taxableAmount;
			}
			public void setTaxableAmount(long taxableAmount) {
				this.taxableAmount = taxableAmount;
			}
			public long getRate() {
				return rate;
			}
			public void setRate(long rate) {
				this.rate = rate;
			}
			public boolean isVat() {
				return isVat;
			}
			public void setVat(boolean isVat) {
				this.isVat = isVat;
			}
			public long getTaxPaid() {
				return taxPaid;
			}
			public void setTaxPaid(long taxPaid) {
				this.taxPaid = taxPaid;
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
	            if (!(obj instanceof TaxCreditAmount))
		            return false;
	            TaxCreditAmount other = (TaxCreditAmount) obj;
	            if (name == null) {
		            if (other.name != null)
			            return false;
	            } else if (!name.equals(other.name))
		            return false;
	            return true;
            }
			
			@Override
            public void accumulate(TaxCreditAmount toAccumulate) {
	            if (toAccumulate != null) {
	            	this.taxableAmount += toAccumulate.getTaxableAmount();
	            	this.taxPaid += toAccumulate.getTaxPaid();
	            }
            }
			
		}
		
	}
	
	
}
