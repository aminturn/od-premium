package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Association;
import com.trubeacon.cloverandroidapi.common.Reference;

public class CategoryItemAssociation extends Association {

	private Category category;
	private Item item;
	
	public CategoryItemAssociation() {}
	public CategoryItemAssociation(Category category, Item item) {
		this.category = category;
		this.item = item;
	}
	
	@Override
	@JsonProperty("item")
    public Reference getReference1() {
        return new Reference((item != null) ? item.getId() : null);
    }

	@Override
	@JsonProperty("category")
    public Reference getReference2() {
        return new Reference((category != null) ? category.getId() : null); 
    }
	
}
