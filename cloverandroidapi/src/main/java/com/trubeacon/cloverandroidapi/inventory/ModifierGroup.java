package com.trubeacon.cloverandroidapi.inventory;

import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.id;
import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.ids;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifiable;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class ModifierGroup implements AlternateIdentifiable<ModifierGroup> {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {
		"id", "category.id", "name", "item.id", "showByDefault"
	};
	public static final String[] UPDATABLE_FIELDS = new String[] {
		"name", 
		"modifierIds", 
		"maxAllowed", 
		"minRequired", 
		"showByDefault", 
		"alternateName"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[]{"modifiers", "items"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private int maxAllowed;
	@JsonProperty
	private int minRequired;
	@JsonProperty
	private WrappedList<Modifier> modifiers;
	@JsonProperty
	private boolean showByDefault;
	@JsonProperty
	private String alternateName;
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
	@JsonProperty
	public String getModifierIds() {
		String modifierIds = "";
		if (this.modifiers != null) {
			String delimiter = "";
			for (Modifier m : this.modifiers) {
				if (m != null && m.getId() != null) {
					modifierIds += delimiter + m.getId();
					delimiter = ",";
				}
			}
		}
		return modifierIds;
	}
	public int getMaxAllowed() {
		return maxAllowed;
	}
	public void setMaxAllowed(int maxAllowed) {
		this.maxAllowed = maxAllowed;
	}
	public int getMinRequired() {
		return minRequired;
	}
	public void setMinRequired(int minRequired) {
		this.minRequired = minRequired;
	}
	public WrappedList<Modifier> getModifiers() {
		return modifiers;
	}
	public void setModifiers(WrappedList<Modifier> modifiers) {
		this.modifiers = modifiers;
	}
	public boolean isShowByDefault() {
		return showByDefault;
	}
	public void setShowByDefault(boolean showByDefault) {
		this.showByDefault = showByDefault;
	}
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	public WrappedList<Item> getItems() {
		return items;
	}
	public void setItems(WrappedList<Item> items) {
		this.items = items;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 47;
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
	    if (!(obj instanceof ModifierGroup))
		    return false;
	    ModifierGroup other = (ModifierGroup) obj;
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
    public boolean alternateEquals(ModifierGroup target) {
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
