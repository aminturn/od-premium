package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class ItemGroup {

	public static final String[] UPDATABLE_FIELDS = new String[] {"name"};
	public static final String[] EXPANDABLE_FIELDS = new String[]{"items", "attributes"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private WrappedList<Item> items;
	@JsonProperty
	private WrappedList<Attribute> attributes;
	
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
	public WrappedList<Item> getItems() {
		return items;
	}
	public void setItems(WrappedList<Item> items) {
		this.items = items;
	}
	public WrappedList<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(WrappedList<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (!(obj instanceof ItemGroup))
		    return false;
	    ItemGroup other = (ItemGroup) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
}
