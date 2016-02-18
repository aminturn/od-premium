package com.trubeacon.cloverandroidapi.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class AlternateIdentifier {

	private Object id;
	private String field;
	
	public static AlternateIdentifier id(Object id, String field) {
		return new AlternateIdentifier(id, field);
	}
	
	public static Collection<AlternateIdentifier> ids(AlternateIdentifier... alternateIdentifiers) {
		return new HashSet<AlternateIdentifier>(Arrays.asList(alternateIdentifiers));
	}
	
	public AlternateIdentifier(Object id, String field) {
		this.id = id;
		this.field = field;
	}
	
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((field == null) ? 0 : field.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
	        return true;
        if (obj == null)
	        return false;
        if (!(obj instanceof AlternateIdentifier))
	        return false;
        AlternateIdentifier other = (AlternateIdentifier) obj;
        if (field == null) {
	        if (other.field != null)
		        return false;
        } else if (!field.equals(other.field))
	        return false;
        if (id == null) {
	        if (other.id != null)
		        return false;
        } else if (!id.equals(other.id))
	        return false;
        return true;
    }
	
}
