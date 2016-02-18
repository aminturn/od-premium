package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Association;
import com.trubeacon.cloverandroidapi.common.Reference;

public class TagItemAssociation extends Association {

	private Tag tag;
	private Item item;
	
	public TagItemAssociation() {}
	public TagItemAssociation(Tag tag, Item item) {
		this.tag = tag;
		this.item = item;
	}
	
	@Override
	@JsonProperty("tag")
    public Reference getReference1() {
        return new Reference((tag != null) ? tag.getId() : null);
    }

	@Override
	@JsonProperty("item")
    public Reference getReference2() {
        return new Reference((item != null) ? item.getId() : null); 
    }
	
}
