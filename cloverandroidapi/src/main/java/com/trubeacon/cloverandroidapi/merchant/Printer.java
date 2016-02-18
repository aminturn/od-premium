package com.trubeacon.cloverandroidapi.merchant;

import java.util.Collection;

import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;
import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifiable;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;

public class Printer implements AlternateIdentifiable<Printer> {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String model;
	@JsonProperty
	private String mac;
	@JsonProperty
	private Type type;
	@JsonProperty
	private String ipAddress;
	
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public enum Type {
		
		NETWORK,
		MY_LOCAL;

		@JsonCreator
		public static Type fromString(String typeString) {
			Type type = null;
			for (Type t : Type.values()) {
				if (t.toString().equalsIgnoreCase(typeString)) {
					type = t;
					break;
				}
			}
			return type;
		}
		
	}

	@Override
    public Collection<AlternateIdentifier> getAlternateIds() {
		return ids(id(this.name, "name"));
    }
	
	@Override
    public boolean alternateEquals(Printer target) {
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
