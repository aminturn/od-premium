package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Association;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;

public class TaxRateItemAssociation extends Association {

	private TaxRate taxRate;
	private Item item;
	
	public TaxRateItemAssociation() {}
	public TaxRateItemAssociation(TaxRate taxRate, Item item) {
		this.taxRate = taxRate;
		this.item = item;
	}
	
	@Override
	@JsonProperty("taxRate")
    public Reference getReference1() {
        return new Reference((taxRate != null) ? taxRate.getId() : null);
    }

	@Override
	@JsonProperty("item")
    public Reference getReference2() {
        return new Reference((item != null) ? item.getId() : null); 
    }
	
}
