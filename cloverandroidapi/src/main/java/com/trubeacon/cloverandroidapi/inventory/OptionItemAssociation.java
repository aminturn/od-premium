package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Association;
import com.trubeacon.cloverandroidapi.common.Reference;

public class OptionItemAssociation extends Association {

	private Option option;
	private Item item;
	
	public OptionItemAssociation() {}
	public OptionItemAssociation(Option option, Item item) {
		this.option = option;
		this.item = item;
	}
	
	@Override
	@JsonProperty("option")
    public Reference getReference1() {
        return new Reference((option != null) ? option.getId() : null);
    }

	@Override
	@JsonProperty("item")
    public Reference getReference2() {
        return new Reference((item != null) ? item.getId() : null); 
    }

}
