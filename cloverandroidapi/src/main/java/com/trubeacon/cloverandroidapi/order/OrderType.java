package com.trubeacon.cloverandroidapi.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.HoursSet;
import com.trubeacon.cloverandroidapi.common.Reference;

public class OrderType {
		
	@JsonProperty
	private String id;
	@JsonProperty("isDefault")
	private boolean isDefault;
	@JsonProperty
	private long avgOrderTime;
	@JsonProperty
	private long maxRadius;
	@JsonProperty
	private HoursAvailable hoursAvailable;
	@JsonProperty
	private boolean filterCategories;
	@JsonProperty
	private long minOrderAmount;
	@JsonProperty
	private CustomerIdMethod customerIdMethod;
	@JsonProperty("isHidden")
	private boolean isHidden;
	@JsonProperty
	private long fee;
	@JsonProperty
	private long maxOrderAmount;
	@JsonProperty
	private List<Reference> categories;
	@JsonProperty
	private boolean taxable;
	@JsonProperty
	private HoursSet hours;
	@JsonProperty
	private String systemOrderTypeId;
	@JsonProperty
	private String labelKey;
	@JsonProperty
	private String label;
	@JsonProperty
	private boolean isDeleted;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public boolean isTaxable() {
		return taxable;
	}
	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}
	public HoursSet getHours() {
		return hours;
	}
	public void setHours(HoursSet hours) {
		this.hours = hours;
	}
	public String getSystemOrderTypeId() {
		return systemOrderTypeId;
	}
	public void setSystemOrderTypeId(String systemOrderTypeId) {
		this.systemOrderTypeId = systemOrderTypeId;
	}
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public long getAvgOrderTime() {
		return avgOrderTime;
	}
	public void setAvgOrderTime(long avgOrderTime) {
		this.avgOrderTime = avgOrderTime;
	}
	public long getMaxRadius() {
		return maxRadius;
	}
	public void setMaxRadius(long maxRadius) {
		this.maxRadius = maxRadius;
	}
	public HoursAvailable getHoursAvailable() {
		return hoursAvailable;
	}
	public void setHoursAvailable(HoursAvailable hoursAvailable) {
		this.hoursAvailable = hoursAvailable;
	}
	public boolean isFilterCategories() {
		return filterCategories;
	}
	public void setFilterCategories(boolean filterCategories) {
		this.filterCategories = filterCategories;
	}
	public long getMinOrderAmount() {
		return minOrderAmount;
	}
	public void setMinOrderAmount(long minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}
	public CustomerIdMethod getCustomerIdMethod() {
		return customerIdMethod;
	}
	public void setCustomerIdMethod(CustomerIdMethod customerIdMethod) {
		this.customerIdMethod = customerIdMethod;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public long getMaxOrderAmount() {
		return maxOrderAmount;
	}
	public void setMaxOrderAmount(long maxOrderAmount) {
		this.maxOrderAmount = maxOrderAmount;
	}
	public List<Reference> getCategories() {
		return categories;
	}
	public void setCategories(List<Reference> categories) {
		this.categories = categories;
	}
	
	public enum HoursAvailable {

		ALL,
		BUSINESS,
		CUSTOM;

		@JsonCreator
		public static HoursAvailable fromString(String hoursAvailableString) {
			HoursAvailable hoursAvailable = null;
			for (HoursAvailable h : HoursAvailable.values()) {
				if (h.toString().equalsIgnoreCase(hoursAvailableString)) {
					hoursAvailable = h;
					break;
				}
			}
			return hoursAvailable;
		}
		
	}
	
	public enum CustomerIdMethod {
		
		NAME,
		TABLE,
		NAME_TABLE;
		
		@JsonCreator
		public static CustomerIdMethod fromString(String customerIdMethodString) {
			CustomerIdMethod customerIdMethod = null;
			for (CustomerIdMethod c : CustomerIdMethod.values()) {
				if (c.toString().equalsIgnoreCase(customerIdMethodString)) {
					customerIdMethod = c;
					break;
				}
			}
			return customerIdMethod;
		}
		
	}
	
}
