package com.trubeacon.cloverandroidapi.inventory;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifiable;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;
import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.*;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class Category implements AlternateIdentifiable<Category> {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {
		"id", "sortOrder", "name", "modifiedTime", "deleted"
	};
	public static final String[] UPDATABLE_FIELDS = new String[] {
		"name", "sortOrder"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[] {
		"items"
	};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private int sortOrder;
	@JsonProperty
	private WrappedList<Item> items;
	
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
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public WrappedList<Item> getItems() {
		return items;
	}
	public void setItems(WrappedList<Item> items) {
		this.items = items;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 193;
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
	    if (!(obj instanceof Category))
		    return false;
	    Category other = (Category) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
	@Override
    public Collection<AlternateIdentifier> getAlternateIds() {
		return ids(id(this.name, "name"));
    }
	
	@Override
    public boolean alternateEquals(Category target) {
		boolean equals = true;
		if (target != null) {
			equals |= (this.name == null) ? target.getName() == null : this.name.equals(target.getName()); 
		}
		else {
			equals = false;
		}
	    return equals;
    }
	
}
