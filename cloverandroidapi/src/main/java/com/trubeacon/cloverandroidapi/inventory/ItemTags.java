package com.trubeacon.cloverandroidapi.inventory;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;

public class ItemTags implements Identifiable {

	public static final String[] FILTERABLE_FIELDS = new String[] {"id", "modifiedTime", "name", "deleted"};
	public static final String[] UPDATABLE_FIELDS = new String[] {"name"};
	public static final String[] EXPANDABLE_FIELDS = new String[]{"items", "printers"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private List<Reference> items;
	@JsonProperty
	private List<Reference> printers;
	
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
	public List<Reference> getItems() {
		return items;
	}
	public void setItems(List<Reference> items) {
		this.items = items;
	}
	public List<Reference> getPrinters() {
		return printers;
	}
	public void setPrinters(List<Reference> printers) {
		this.printers = printers;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 107;
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
	    if (!(obj instanceof ItemTags))
		    return false;
	    ItemTags other = (ItemTags) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
}
