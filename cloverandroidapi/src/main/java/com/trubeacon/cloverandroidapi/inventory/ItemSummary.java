package com.trubeacon.cloverandroidapi.inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Accumulateable;

public class ItemSummary {

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
		private List<Category> categories;
		
		public List<Category> getCategories() {
			return categories;
		}
		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}

		public static class Category {
			
			@JsonProperty
			private String id;
			@JsonProperty
			private String name;
			@JsonProperty
			private List<Item> items;
			
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
			public List<Item> getItems() {
				return items;
			}
			public void setItems(List<Item> items) {
				this.items = items;
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
	            if (!(obj instanceof Category))
		            return false;
	            Category other = (Category) obj;
	            if (name == null) {
		            if (other.name != null)
			            return false;
	            } else if (!name.equals(other.name))
		            return false;
	            return true;
			}

			public static class Item implements Accumulateable<Item> {
				
				@JsonProperty
				private String id;
				@JsonProperty
				private String name;
				@JsonProperty
				private long numberSold;
				@JsonProperty
				private long revenueSold;
				@JsonProperty
				private long numRefunds;
				@JsonProperty
				private long refundAmount;
				@JsonProperty
				private long numExchanges;
				@JsonProperty
				private long exchangeAmount;
				@JsonProperty
				private List<DiscountSale> discountSales;
				@JsonProperty
				private List<ModifierSale> modifierSales;
				
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
				public long getNumRefunds() {
					return numRefunds;
				}
				public void setNumRefunds(long numRefunds) {
					this.numRefunds = numRefunds;
				}
				public long getRefundAmount() {
					return refundAmount;
				}
				public void setRefundAmount(long refundAmount) {
					this.refundAmount = refundAmount;
				}
				public long getNumExchanges() {
					return numExchanges;
				}
				public void setNumExchanges(long numExchanges) {
					this.numExchanges = numExchanges;
				}
				public long getExchangeAmount() {
					return exchangeAmount;
				}
				public void setExchangeAmount(long exchangeAmount) {
					this.exchangeAmount = exchangeAmount;
				}
				public List<DiscountSale> getDiscountSales() {
					return discountSales;
				}
				public void setDiscountSales(List<DiscountSale> discountSales) {
					this.discountSales = discountSales;
				}
				public List<ModifierSale> getModifierSales() {
					return modifierSales;
				}
				public void setModifierSales(List<ModifierSale> modifierSales) {
					this.modifierSales = modifierSales;
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
	                if (!(obj instanceof Item))
		                return false;
	                Item other = (Item) obj;
	                if (name == null) {
		                if (other.name != null)
			                return false;
	                } else if (!name.equals(other.name))
		                return false;
	                return true;
                }

				public static class DiscountSale implements Accumulateable<DiscountSale> {
					
					@JsonProperty
					private String id;
					@JsonProperty
					private String name;
					@JsonProperty
					private long numberSold;
					@JsonProperty
					private long revenueSold;
					
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
                    public void accumulate(DiscountSale toAccumulate) {
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
	                    if (!(obj instanceof DiscountSale))
		                    return false;
	                    DiscountSale other = (DiscountSale) obj;
	                    if (name == null) {
		                    if (other.name != null)
			                    return false;
	                    } else if (!name.equals(other.name))
		                    return false;
	                    return true;
                    }
										
				}
				
				public static class ModifierSale implements Accumulateable<ModifierSale> {
					
					@JsonProperty
					private String id;
					@JsonProperty
					private String name;
					@JsonProperty
					private long numberSold;
					@JsonProperty
					private long revenueSold;
					
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
	                    if (!(obj instanceof ModifierSale))
		                    return false;
	                    ModifierSale other = (ModifierSale) obj;
	                    if (name == null) {
		                    if (other.name != null)
			                    return false;
	                    } else if (!name.equals(other.name))
		                    return false;
	                    return true;
                    }
					
					@Override
                    public void accumulate(ModifierSale toAccumulate) {
	                    if (toAccumulate != null) {
	                    	this.numberSold += toAccumulate.getNumberSold();
	                    	this.revenueSold += toAccumulate.getRevenueSold();
	                    }
                    }
					
				}

				@Override
                public void accumulate(Item toAccumulate) {
	                if (toAccumulate != null) {
	                	this.exchangeAmount += toAccumulate.getExchangeAmount();
	                	this.numberSold += toAccumulate.getNumberSold();
	                	this.numExchanges += toAccumulate.getNumExchanges();
	                	this.numRefunds += toAccumulate.getNumRefunds();
	                	this.refundAmount += toAccumulate.getRefundAmount();
	                	this.revenueSold += toAccumulate.getRevenueSold();
	                	if (this.discountSales == null) this.discountSales = new ArrayList<DiscountSale>();
	                	if (toAccumulate.getDiscountSales() != null) {
		                	for (DiscountSale sale : toAccumulate.getDiscountSales()) {
		                		if (this.discountSales.contains(sale)) {
		                			Iterator<DiscountSale> iter = this.discountSales.iterator();
		                			while (iter.hasNext()) {
		                				DiscountSale next = iter.next();
		                				if (next.equals(sale)) {
		                					next.accumulate(sale);
		                				}
		                			}
		                		}
		                		else {
		                			this.discountSales.add(sale);
		                		}
		                	}	                		
	                	}
	                	if (this.modifierSales == null) this.modifierSales = new ArrayList<ModifierSale>();
	                	if (toAccumulate.getModifierSales() != null) {
		                	for (ModifierSale sale : toAccumulate.getModifierSales()) {
		                		if (this.discountSales.contains(sale)) {
		                			Iterator<ModifierSale> iter = this.modifierSales.iterator();
		                			while (iter.hasNext()) {
		                				ModifierSale next = iter.next();
		                				if (next.equals(sale)) {
		                					next.accumulate(sale);
		                				}
		                			}
		                		}
		                		else {
		                			this.modifierSales.add(sale);
		                		}
		                	}	                		
	                	}
	                }
                }
				
			}
			
		}
		
	}
	
}
