package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;

public class ItemStock implements Identifiable {

	public static final String[] UPDATABLE_FIELDS = new String[] {"quantity", "stockCount"};
	
	@Override
    public String getId() {
	    return (item != null) ? item.getId() : null;
    }
	public void setId(String id) {
		if (item == null) {
			this.item = new Reference();
		}
		this.item.setId(id);
	}
	
	@JsonProperty
	private Reference item;
	@JsonProperty
	private double quantity;
	@Deprecated
	@JsonProperty
	private long stockCount;
	
	public Reference getItem() {
		return item;
	}
	public void setItem(Reference item) {
		this.item = item;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@Deprecated
	public long getStockCount() {
		return stockCount;
	}
	@Deprecated
	public void setStockCount(long stockCount) {
		this.stockCount = stockCount;
	}
		
}
