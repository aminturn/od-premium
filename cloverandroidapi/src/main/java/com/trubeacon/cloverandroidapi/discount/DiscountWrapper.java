package com.trubeacon.cloverandroidapi.discount;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;

public class DiscountWrapper extends Discount {

	@JsonProperty("discount")
	private RawDiscount rawDiscount;
			
	public DiscountWrapper() {
		this.rawDiscount = new RawDiscount();
	}
	public DiscountWrapper(Discount discount) {
		if (discount != null) {
			this.rawDiscount = new RawDiscount();
			this.rawDiscount.setName(discount.getName());
			if (discount.getType() != null) {
				switch(discount.getType()) {
				case AMOUNT:
					this.rawDiscount.setAmount(discount.getAmount());
					break;
				case PERCENTAGE:
					this.rawDiscount.setPercentage(discount.getAmount());
					break;
				}				
			}
		}
	}
	
	public RawDiscount getRawDiscount() {
		return rawDiscount;
	}
	public void setRawDiscount(RawDiscount rawDiscount) {
		this.rawDiscount = rawDiscount;
	}
	
	@Override
	public String getId() {
		return rawDiscount.getId();
	}
	public void setId(String id) {
		super.setId(id);
		if (rawDiscount != null) {
			rawDiscount.setId(id);
		}
	}
	@Override
	public String getName() {
		return rawDiscount.getName();
	}
	public void setName(String name) {
		super.setName(name);
		if (rawDiscount != null) {
			rawDiscount.setName(name);
		}
	}
	@Override
	public long getAmount() {
		return (rawDiscount.getAmount() != null) ? rawDiscount.getAmount() : rawDiscount.getPercentage(); 
	}
	public void setAmount(long amount) {
		super.setAmount(amount);
		if (rawDiscount != null) {
			rawDiscount.setAmount(amount);
		}
	}
	@Override	
	public Type getType() {
		return Type.fromRawDiscount(rawDiscount);
	}
	// nothing to override here
		
}
