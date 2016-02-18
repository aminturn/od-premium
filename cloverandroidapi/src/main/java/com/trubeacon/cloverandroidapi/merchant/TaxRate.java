package com.trubeacon.cloverandroidapi.merchant;

import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.id;
import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.ids;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifiable;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;
import com.trubeacon.cloverandroidapi.common.Identifiable;

public class TaxRate implements AlternateIdentifiable<TaxRate> {

	public static final double TAX_DENOMINATOR = 10000000.0;
	
	public static final String[] FILTERABLE_FIELDS = new String[] {
		"items.id", "id", "rate", "isDefault", "name"
	};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private long rate;
	@JsonProperty("isDefault") // just to make sure the name doesn't screw with the default mappingm
	private boolean isDefault;
	
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
	public long getRate() {
		return rate;
	}
	public void setRate(long rate) {
		this.rate = rate;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
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
	    if (!(obj instanceof TaxRate))
		    return false;
	    TaxRate other = (TaxRate) obj;
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
    public boolean alternateEquals(TaxRate target) {
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
