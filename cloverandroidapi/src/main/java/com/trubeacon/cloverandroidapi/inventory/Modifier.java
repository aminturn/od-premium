package com.trubeacon.cloverandroidapi.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;

public class Modifier implements Identifiable {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {"id", "price", "name", "modifierGroup.id", "alternateName"};
	public static final String[] UPDATABLE_FIELDS = new String[] {"name", "price", "alternateName"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private long price;
	@JsonProperty
	private Reference modifierGroup;
	@JsonProperty
	private String alternateName;
	
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Reference getModifierGroup() {
		return modifierGroup;
	}
	public void setModifierGroup(Reference modifierGroup) {
		this.modifierGroup = modifierGroup;
	}
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 61;
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
	    if (!(obj instanceof Modifier))
		    return false;
	    Modifier other = (Modifier) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }

}
