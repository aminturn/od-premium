package com.trubeacon.cloverandroidapi.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Item;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;

public class LineItem implements Identifiable {

	public static final String[] FILTERABLE_FIELDS = new String[] {
		"name",
		"item.id",
		"createdTime",
		"employee.id",
		"exchanged",
		"modification.id",
		"discount.id",
		"tag.id",
		"refunded",
		"printed",
		"taxRate.id",
		"price"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[]{
		"order.employee", 
		"discounts", 
		"modifications", 
		"taxRates", 
		"payments"
	};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private int unitQty;
	@JsonProperty
	private long createdTime;
	@JsonProperty
	private WrappedList<Modification> modifications;
	@JsonProperty
	private WrappedList<TaxRate> taxRates;
	@JsonProperty
	private String userData;
	@JsonProperty
	private long discountAmount;
	@JsonProperty
	private String alternateName;
	@JsonProperty
	private boolean exchanged;
	@JsonProperty
	private boolean refunded;
	@JsonProperty
	private long price;
	@JsonProperty
	private String binName;
	@JsonProperty
	private boolean isRevenue;
	@JsonProperty
	private String unitName;
	@JsonProperty
	private WrappedList<Discount> discounts;
	@JsonProperty
	private Item item;
	@JsonProperty
	private String name;
	@JsonProperty
	private WrappedList<LineItemPayment> payments;
	@JsonProperty
	private boolean printed;
	@JsonProperty
	private Reference exchangedLineItem;
	@JsonProperty
	private Order orderRef;
	@JsonProperty
	private String note;
	@JsonProperty
	private String itemCode;
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUnitQty() {
		return unitQty;
	}
	public void setUnitQty(int unitQty) {
		this.unitQty = unitQty;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public WrappedList<Modification> getModifications() {
		return modifications;
	}
	public void setModifications(WrappedList<Modification> modifications) {
		this.modifications = modifications;
	}
	public WrappedList<TaxRate> getTaxRates() {
		return taxRates;
	}
	public void setTaxRates(WrappedList<TaxRate> taxRates) {
		this.taxRates = taxRates;
	}
	public String getUserData() {
		return userData;
	}
	public void setUserData(String userData) {
		this.userData = userData;
	}
	public long getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(long discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	public boolean isExchanged() {
		return exchanged;
	}
	public void setExchanged(boolean exchanged) {
		this.exchanged = exchanged;
	}
	public boolean isRefunded() {
		return refunded;
	}
	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public boolean isRevenue() {
		return isRevenue;
	}
	public void setRevenue(boolean isRevenue) {
		this.isRevenue = isRevenue;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public WrappedList<Discount> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(WrappedList<Discount> discounts) {
		this.discounts = discounts;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WrappedList<LineItemPayment> getPayments() {
		return payments;
	}
	public void setPayments(WrappedList<LineItemPayment> payments) {
		this.payments = payments;
	}
	public boolean isPrinted() {
		return printed;
	}
	public void setPrinted(boolean printed) {
		this.printed = printed;
	}
	public Reference getExchangedLineItem() {
		return exchangedLineItem;
	}
	public void setExchangedLineItem(Reference exchangedLineItem) {
		this.exchangedLineItem = exchangedLineItem;
	}
	public Order getOrderRef() {
		return orderRef;
	}
	public void setOrderRef(Order orderRef) {
		this.orderRef = orderRef;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
	    if (!(obj instanceof LineItem))
		    return false;
	    LineItem other = (LineItem) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
}
