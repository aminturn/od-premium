package com.trubeacon.cloverandroidapi.inventory;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Reference;

public class Attribute {
		
	public static final String[] UPDATABLE_FIELDS = new String[] {"name"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private Reference itemGroup;
	@JsonProperty
	private List<Option> options;
	
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
	public Reference getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(Reference itemGroup) {
		this.itemGroup = itemGroup;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
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
	    if (!(obj instanceof Attribute))
		    return false;
	    Attribute other = (Attribute) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
}
