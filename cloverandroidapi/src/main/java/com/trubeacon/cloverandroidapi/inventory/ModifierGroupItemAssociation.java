package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Association;
import com.trubeacon.cloverandroidapi.common.Reference;

public class ModifierGroupItemAssociation extends Association {

	private Item item;
	private ModifierGroup modifierGroup;
	
	public ModifierGroupItemAssociation() {}
	public ModifierGroupItemAssociation(ModifierGroup modifierGroup, Item item) {
		this.modifierGroup = modifierGroup;
		this.item = item;
	}
	
	@Override
	@JsonProperty("item")
    public Reference getReference1() {
        return new Reference((item != null) ? item.getId() : null);
    }

	@Override
	@JsonProperty("modifierGroup")
    public Reference getReference2() {
        return new Reference((modifierGroup != null) ? modifierGroup.getId() : null); 
	}
	
}
