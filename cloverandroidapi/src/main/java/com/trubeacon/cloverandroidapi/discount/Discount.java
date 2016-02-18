package com.trubeacon.cloverandroidapi.discount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifiable;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;

import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.*;

public class Discount implements AlternateIdentifiable<Discount> {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private Type type;
	@JsonProperty
	private long amount;
	
	public static List<Discount> fromRawDiscounts(Collection<RawDiscount> raw) {
		List<Discount> discounts = new ArrayList<Discount>();
		if (raw != null) {
			for (RawDiscount r : raw) {
				discounts.add(new Discount(r));
			}
		}
		return discounts;
	}
	
	public Discount() {}
	public Discount(RawDiscount raw) {
		if (raw != null) {
			this.id = raw.getId();
			this.name = raw.getName();
			this.type = Type.fromRawDiscount(raw);
			if (this.type != null) {
				switch(this.type) {
				case AMOUNT:
					this.amount = (raw.getAmount() != null) ? raw.getAmount() : 0;
					break;
				case PERCENTAGE:
					this.amount = (raw.getPercentage() != null) ? raw.getPercentage() : 0;
					break;
				}
			}
		}
	}
	
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
    public Collection<AlternateIdentifier> getAlternateIds() {
	    return ids(id(this.name, "name"));
    }
	
	@Override
    public boolean alternateEquals(Discount target) {
		boolean equals = true;
		if (target != null) {
			equals |= (this.name == null) ? target.getName() == null : this.name.equals(target.getName()); 
		}
		else {
			equals = false;
		}
	    return equals;
    }
	
	public enum Type {
		
		AMOUNT,
		PERCENTAGE;
		
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
		
		public static Type fromRawDiscount(RawDiscount raw) {
			Type type = null;
			if (raw != null) {
				if (raw.getAmount() != null) {
					type = Type.AMOUNT;
				}
				if (raw.getPercentage() != null) {
					type = Type.PERCENTAGE;
				}
			}
			return type;
		}
		
	}

}
