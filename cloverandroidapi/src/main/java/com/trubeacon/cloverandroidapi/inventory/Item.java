package com.trubeacon.cloverandroidapi.inventory;

import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.id;
import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.ids;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifiable;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;
import com.trubeacon.cloverandroidapi.common.Identifiable;
import com.trubeacon.cloverandroidapi.common.Reference;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;

public class Item implements AlternateIdentifiable<Item> {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {
		"modifiedTime", 
		"sku", 
		"deleted", 
		"alternateName", 
		"itemGroup.id",
		"id", 
		"tags.id", 
		"price", 
		"hidden", 
		"name", 
		"tags.name", 
		"option.id", 
		"item.id", 
		"itemCode"
	};
	public static final String[] UPDATABLE_FIELDS = new String[] {
		"name", 
		"tags", 
		"modifierGroups", 
		"taxRates", 
		"defaultTaxRates",
		"stockCount", 
		"code", 
		"sku", 
		"cost", 
		"alternateName",
		"price", 
		"hidden", 
		"unitName",
		"revenue",
		"categories",
		"options",
		"itemGroup",
		"itemStock"
	};
	
	public static final String[] EXPANDABLE_FIELDS = new String[]{"tags", "categories", "taxRates", "modifierGroups", "modifierGroups.modifiers", "itemStock"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private WrappedList<Tag> tags;
	@JsonProperty
	private WrappedList<ModifierGroup> modifierGroups;
	@JsonProperty
	private long modifiedTime;
	@JsonProperty
	private WrappedList<TaxRate> taxRates;
	@JsonProperty
	private PriceType priceType;
	@JsonProperty
	private boolean defaultTaxRates;
	@JsonProperty
	private long stockCount;
	@JsonProperty
	private String code;
	@JsonProperty
	private String sku;
	@JsonProperty
	private long cost;
	@JsonProperty
	private String alternateName;
	@JsonProperty
	private long price;
	@JsonProperty
	private boolean hidden;
	@JsonProperty
	private String unitName;
	@JsonProperty("isRevenue")
	private boolean isRevenue;
	@JsonProperty
	private WrappedList<Category> categories;
	// ???: I don't know if this is serialized or not...
	@JsonProperty
	private WrappedList<Option> options;
	@JsonProperty
	private Reference itemGroup;
	@JsonProperty
	private ItemStock itemStock;
	
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
	public WrappedList<Tag> getTags() {
		return tags;
	}
	public void setTags(WrappedList<Tag> tags) {
		this.tags = tags;
	}
	public WrappedList<ModifierGroup> getModifierGroups() {
		return modifierGroups;
	}
	public void setModifierGroups(WrappedList<ModifierGroup> modifierGroups) {
		this.modifierGroups = modifierGroups;
	}
	public long getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public WrappedList<TaxRate> getTaxRates() {
		return taxRates;
	}
	public void setTaxRates(WrappedList<TaxRate> taxRates) {
		this.taxRates = taxRates;
	}
	public PriceType getPriceType() {
		return priceType;
	}
	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}
	public boolean isDefaultTaxRates() {
		return defaultTaxRates;
	}
	public void setDefaultTaxRates(boolean defaultTaxRates) {
		this.defaultTaxRates = defaultTaxRates;
	}
	public long getStockCount() {
		return stockCount;
	}
	public void setStockCount(long stockCount) {
		this.stockCount = stockCount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public boolean isRevenue() {
		return isRevenue;
	}
	public void setRevenue(boolean isRevenue) {
		this.isRevenue = isRevenue;
	}
	public WrappedList<Category> getCategories() {
		return categories;
	}
	public void setCategories(WrappedList<Category> categories) {
		this.categories = categories;
	}
	public WrappedList<Option> getOptions() {
		return options;
	}
	public void setOptions(WrappedList<Option> options) {
		this.options = options;
	}
	public Reference getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(Reference itemGroup) {
		this.itemGroup = itemGroup;
	}
	public ItemStock getItemStock() {
		return itemStock;
	}
	public void setItemStock(ItemStock itemStock) {
		this.itemStock = itemStock;
	}
	
	public enum PriceType {
		
		FIXED,
		VARIABLE,
		PER_UNIT;
		
		@JsonCreator
		public static PriceType fromString(String priceTypeString) {
			PriceType priceType = null;
			for (PriceType p : PriceType.values()) {
				if (p.toString().equalsIgnoreCase(priceTypeString)) {
					priceType = p;
					break;
				}
			}
			return priceType;
		}
		
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
	    if (!(obj instanceof Item))
		    return false;
	    Item other = (Item) obj;
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
    public boolean alternateEquals(Item target) {
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