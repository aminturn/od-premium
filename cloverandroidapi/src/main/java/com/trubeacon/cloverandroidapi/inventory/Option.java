package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;

public class Option implements Identifiable {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {"id", "deletedTime", "name", "modifiedTime", "attribute.id"};
	public static final String[] UPDATABLE_FIELDS = new String[] {"name"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private Reference attribute;
	
	@Override
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
	public Reference getAttribute() {
		return attribute;
	}
	public void setAttribute(Reference attribute) {
		this.attribute = attribute;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 131;
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
	    if (!(obj instanceof Option))
		    return false;
	    Option other = (Option) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }

}
