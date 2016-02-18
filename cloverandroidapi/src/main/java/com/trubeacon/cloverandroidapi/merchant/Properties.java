package com.trubeacon.cloverandroidapi.merchant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {

	@JsonProperty
	private String supportEmail;
	@JsonProperty
	private boolean autoPrint;
	@JsonProperty
	private boolean logInClockInPrompt;
	@JsonProperty
	private boolean removeTaxEnabled;
	@JsonProperty
	private boolean trackStock;
	@JsonProperty
	private String appBillingSystem;
	@JsonProperty
	private String defaultCurrency; // TODO: this can probably be an enum but I don't know the standard
	@JsonProperty
	private String locale;
	@JsonProperty
	private boolean autoLogout;
	@JsonProperty
	private boolean tipsEnabled;
	@JsonProperty
	private boolean vat;
	@JsonProperty
	private int summaryHour;
	@JsonProperty
	private boolean showCloseoutOrders;
	@JsonProperty
	private boolean deleteOrders;
	@JsonProperty
	private String timezone; // TODO: this can probably be an enum but I don't know the format
	@JsonProperty
	private String marketingPreferenceText;
	@JsonProperty
	private String hardwareProfile;
	@JsonProperty
	private String shippingAddress;
	@JsonProperty
	private boolean stayInCategory;
	@JsonProperty
	private boolean updateStock;
	@JsonProperty
	private int tipRateDefault;
	@JsonProperty
	private boolean marketingEnabled;
	@JsonProperty
	private String supportHome;
	@JsonProperty
	private String vatTaxName;
	@JsonProperty
	private long signatureThreshold;
	@JsonProperty
	private int bankMarker;
	@JsonProperty
	private OrderTitle orderTitle;
	@JsonProperty
	private boolean alternateInventoryNames;
	@JsonProperty
	private boolean manualCloseout;
	@JsonProperty
	private boolean onPaperTipSignatures;
	@JsonProperty
	private String accountType;
	@JsonProperty
	private boolean notesOnOrder;
	@JsonProperty
	private boolean groupLineItems;
	@JsonProperty
	private boolean allowClockOutWithOpenOrders;
	@JsonProperty
	private String receiptProperties;

	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	public boolean isAutoPrint() {
		return autoPrint;
	}
	public void setAutoPrint(boolean autoPrint) {
		this.autoPrint = autoPrint;
	}
	public boolean isLogInClockInPrompt() {
		return logInClockInPrompt;
	}
	public void setLogInClockInPrompt(boolean logInClockInPrompt) {
		this.logInClockInPrompt = logInClockInPrompt;
	}
	public boolean isRemoveTaxEnabled() {
		return removeTaxEnabled;
	}
	public void setRemoveTaxEnabled(boolean removeTaxEnabled) {
		this.removeTaxEnabled = removeTaxEnabled;
	}
	public boolean isTrackStock() {
		return trackStock;
	}
	public void setTrackStock(boolean trackStock) {
		this.trackStock = trackStock;
	}
	public String getAppBillingSystem() {
		return appBillingSystem;
	}
	public void setAppBillingSystem(String appBillingSystem) {
		this.appBillingSystem = appBillingSystem;
	}
	public String getDefaultCurrency() {
		return defaultCurrency;
	}
	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public boolean isAutoLogout() {
		return autoLogout;
	}
	public void setAutoLogout(boolean autoLogout) {
		this.autoLogout = autoLogout;
	}
	public boolean isTipsEnabled() {
		return tipsEnabled;
	}
	public void setTipsEnabled(boolean tipsEnabled) {
		this.tipsEnabled = tipsEnabled;
	}
	public boolean isVat() {
		return vat;
	}
	public void setVat(boolean vat) {
		this.vat = vat;
	}
	public int getSummaryHour() {
		return summaryHour;
	}
	public void setSummaryHour(int summaryHour) {
		this.summaryHour = summaryHour;
	}
	public boolean isShowCloseoutOrders() {
		return showCloseoutOrders;
	}
	public void setShowCloseoutOrders(boolean showCloseoutOrders) {
		this.showCloseoutOrders = showCloseoutOrders;
	}
	public boolean isDeleteOrders() {
		return deleteOrders;
	}
	public void setDeleteOrders(boolean deleteOrders) {
		this.deleteOrders = deleteOrders;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getMarketingPreferenceText() {
		return marketingPreferenceText;
	}
	public void setMarketingPreferenceText(String marketingPreferenceText) {
		this.marketingPreferenceText = marketingPreferenceText;
	}
	public String getHardwareProfile() {
		return hardwareProfile;
	}
	public void setHardwareProfile(String hardwareProfile) {
		this.hardwareProfile = hardwareProfile;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public boolean isStayInCategory() {
		return stayInCategory;
	}
	public void setStayInCategory(boolean stayInCategory) {
		this.stayInCategory = stayInCategory;
	}
	public boolean isUpdateStock() {
		return updateStock;
	}
	public void setUpdateStock(boolean updateStock) {
		this.updateStock = updateStock;
	}
	public int getTipRateDefault() {
		return tipRateDefault;
	}
	public void setTipRateDefault(int tipRateDefault) {
		this.tipRateDefault = tipRateDefault;
	}
	public boolean isMarketingEnabled() {
		return marketingEnabled;
	}
	public void setMarketingEnabled(boolean marketingEnabled) {
		this.marketingEnabled = marketingEnabled;
	}
	public String getSupportHome() {
		return supportHome;
	}
	public void setSupportHome(String supportHome) {
		this.supportHome = supportHome;
	}
	public String getVatTaxName() {
		return vatTaxName;
	}
	public void setVatTaxName(String vatTaxName) {
		this.vatTaxName = vatTaxName;
	}
	public long getSignatureThreshold() {
		return signatureThreshold;
	}
	public void setSignatureThreshold(long signatureThreshold) {
		this.signatureThreshold = signatureThreshold;
	}
	public int getBankMarker() {
		return bankMarker;
	}
	public void setBankMarker(int bankMarker) {
		this.bankMarker = bankMarker;
	}
	public OrderTitle getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(OrderTitle orderTitle) {
		this.orderTitle = orderTitle;
	}
	public boolean isAlternateInventoryNames() {
		return alternateInventoryNames;
	}
	public void setAlternateInventoryNames(boolean alternateInventoryNames) {
		this.alternateInventoryNames = alternateInventoryNames;
	}
	public boolean isManualCloseout() {
		return manualCloseout;
	}
	public void setManualCloseout(boolean manualCloseout) {
		this.manualCloseout = manualCloseout;
	}
	public boolean isOnPaperTipSignatures() {
		return onPaperTipSignatures;
	}
	public void setOnPaperTipSignatures(boolean onPaperTipSignatures) {
		this.onPaperTipSignatures = onPaperTipSignatures;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public boolean isNotesOnOrder() {
		return notesOnOrder;
	}
	public void setNotesOnOrder(boolean notesOnOrder) {
		this.notesOnOrder = notesOnOrder;
	}
	public boolean isGroupLineItems() {
		return groupLineItems;
	}
	public void setGroupLineItems(boolean groupLineItems) {
		this.groupLineItems = groupLineItems;
	}
	public boolean isAllowClockOutWithOpenOrders() {
		return allowClockOutWithOpenOrders;
	}
	public void setAllowClockOutWithOpenOrders(boolean allowClockOutWithOpenOrders) {
		this.allowClockOutWithOpenOrders = allowClockOutWithOpenOrders;
	}
	public String getReceiptProperties() {
		return receiptProperties;
	}
	public void setReceiptProperties(String receiptProperties) {
		this.receiptProperties = receiptProperties;
	}
	
	public enum OrderTitle {
		
		NONE,
		MANUAL,
		AUTOMATIC;
		
		public static OrderTitle fromString(String orderTitleString) {
			OrderTitle orderTitle = null;
			for (OrderTitle o : OrderTitle.values()) {
				if (o.toString().equalsIgnoreCase(orderTitleString)) {
					orderTitle = o;
					break;
				}
			}
			return orderTitle;
		}
		
	}
		
}
