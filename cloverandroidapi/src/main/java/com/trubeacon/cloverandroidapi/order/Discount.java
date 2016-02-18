package com.trubeacon.cloverandroidapi.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;

public class Discount implements Identifiable {
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private long amount;
	@JsonProperty
	private long percentage;
	@JsonProperty
	private Reference discount;
	
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
	public Reference getDiscount() {
		return discount;
	}
	public void setDiscount(Reference discount) {
		this.discount = discount;
	}
	public long getPercentage() {
		return percentage;
	}
	public void setPercentage(long percentage) {
		this.percentage = percentage;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (!(obj instanceof Discount))
		    return false;
	    Discount other = (Discount) obj;
	    if (name == null) {
		    if (other.name != null)
			    return false;
	    } else if (!name.equals(other.name))
		    return false;
	    return true;
    }
	
}
