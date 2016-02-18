package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.common.Address;
import com.trubeacon.cloverandroidapi.common.HoursSet;
import com.trubeacon.cloverandroidapi.common.Role;
import com.trubeacon.cloverandroidapi.merchant.Devices;
import com.trubeacon.cloverandroidapi.merchant.Merchant;
import com.trubeacon.cloverandroidapi.merchant.Permission;
import com.trubeacon.cloverandroidapi.merchant.Properties;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;
import com.trubeacon.cloverandroidapi.merchant.TaxRateSummary;
import com.trubeacon.cloverandroidapi.merchant.service.CreateOpeningHour.CreateOpeningHourCallback;
import com.trubeacon.cloverandroidapi.merchant.service.CreateOrderType.CreateOrderTypeCallback;
import com.trubeacon.cloverandroidapi.merchant.service.CreateRole.CreateRoleCallback;
import com.trubeacon.cloverandroidapi.merchant.service.CreateTaxRate.CreateTaxRateCallback;
import com.trubeacon.cloverandroidapi.merchant.service.DeleteOpeningHour.DeleteOpeningHourCallback;
import com.trubeacon.cloverandroidapi.merchant.service.DeleteOrderType.DeleteOrderTypeCallback;
import com.trubeacon.cloverandroidapi.merchant.service.DeleteRole.DeleteRoleCallback;
import com.trubeacon.cloverandroidapi.merchant.service.DeleteTaxRate.DeleteTaxRateCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetDevices.GetDevicesCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetMerchant.GetMerchantCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetMerchantAddress.GetMerchantAddressCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetMerchantProperties.GetMerchantPropertiesCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetOpeningHour.GetOpeningHourCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetOpeningHours.GetOpeningHoursCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetOrderType.GetOrderTypeCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetOrderTypes.GetOrderTypesCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetPermissions.GetPermissionsCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetRole.GetRoleCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetRoles.GetRolesCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetTaxRate.GetTaxRateCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetTaxRateSummary.GetTaxRateSummaryCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetTaxRates.GetTaxRatesCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetTender.GetTenderCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetTenders.GetTendersCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetTipSuggestion.GetTipSuggestionCallback;
import com.trubeacon.cloverandroidapi.merchant.service.GetTipSuggestions.GetTipSuggestionsCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateMerchant.UpdateMerchantCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateMerchantProperties.UpdateMerchantPropertiesCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateOpeningHour.UpdateOpeningHourCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateOrderType.UpdateOrderTypeCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateRole.UpdateRoleCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateTaxRate.UpdateTaxRateCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateTipSuggestion.UpdateTipSuggestionCallback;
import com.trubeacon.cloverandroidapi.order.OrderType;
import com.trubeacon.cloverandroidapi.payment.Tender;
import com.trubeacon.cloverandroidapi.payment.TipSuggestion;

public interface MerchantService {
	
	public Merchant getMerchant(String mid, String token) throws RESTException;
	public void getMerchant(String mId, String token, GetMerchantCallback callback);
	public Merchant updateMerchant(String mid, String token, Merchant update) throws RESTException;
	public void updateMerchant(String mId, String token, Merchant update, UpdateMerchantCallback callback);
	public Address getMerchantAddress(String mid, String token) throws RESTException;
	public void getMerchantAddress(String mId, String token, GetMerchantAddressCallback callback);
	public Properties getMerchantProperties(String mid, String token) throws RESTException;
	public void getMerchantProperties(String mId, String token, GetMerchantPropertiesCallback callback);
	public Properties updateMerchantProperties(String mid, String token, Properties update) throws RESTException;
	public void updateMerchantProperties(String mId, String token, Properties update, UpdateMerchantPropertiesCallback callback);
	
	public List<Permission> getPermissions(String mId, String token) throws RESTException;
	public void getPermissions(String mId, String token, GetPermissionsCallback callback);
	
	public Devices getDevices(String mId, String token) throws RESTException;
	public void getDevices(String mId, String token, GetDevicesCallback callback);
	
	public List<TipSuggestion> getTipSuggestions(String mid, String token, Object... params) throws RESTException;
	public void getTipSuggestions(String mId, String token, GetTipSuggestionsCallback callback, Object... params);
	public TipSuggestion getTipSuggestion(String mid, String token, String tid) throws RESTException;
	public void getTipSuggestion(String mId, String token, String tId, GetTipSuggestionCallback callback);
	public TipSuggestion updateTipSuggestion(String mid, String token, String id, TipSuggestion update) throws RESTException;
	public void updateTipSuggestion(String mId, String token, String id, TipSuggestion update, UpdateTipSuggestionCallback callback);
	
	public List<OrderType> getOrderTypes(String mid, String token, Object... params) throws RESTException;
	public void getOrderTypes(String mId, String token, GetOrderTypesCallback callback, Object... params);
	public OrderType createOrderType(String mid, String token, OrderType orderType) throws RESTException;
	public void createOrderType(String mId, String token, OrderType orderType, CreateOrderTypeCallback callback);
	public OrderType getOrderType(String mid, String token, String orderTypeId) throws RESTException;
	public void getOrderType(String mId, String token, String orderTypeId, GetOrderTypeCallback callback);
	public OrderType updateOrderType(String mid, String token, String orderTypeId, OrderType update) throws RESTException;
	public void updateOrderType(String mId, String token, String orderTypeId, OrderType update, UpdateOrderTypeCallback callback);
	public OrderType deleteOrderType(String mid, String token, String orderTypeId) throws RESTException;
	public void deleteOrderType(String mId, String token, String orderTypeId, DeleteOrderTypeCallback callback);
	
	public List<Role> getRoles(String mid, String token, Object... params) throws RESTException;
	public void getRoles(String mId, String token, GetRolesCallback callback, Object... params);
	public Role createRole(String mid, String token, Role role) throws RESTException;
	public void createRole(String mId, String token, Role role, CreateRoleCallback callback);
	public Role getRole(String mid, String token, String rId) throws RESTException;
	public void getRole(String mId, String token, String rId, GetRoleCallback callback);
	public Role updateRole(String mid, String token, String rId, Role role) throws RESTException;
	public void updateRole(String mId, String token, String rId, Role role, UpdateRoleCallback callback);
	public Role deleteRole(String mid, String token, String rId) throws RESTException;
	public void deleteRole(String mId, String token, String rId, DeleteRoleCallback callback);
	
	public List<Tender> getTenders(String mid, String token, Object... params) throws RESTException;
	public void getTenders(String mId, String token, GetTendersCallback callback, Object... params);
	public Tender getTender(String mid, String token, String tenderId) throws RESTException;
	public void getTender(String mId, String token, String tenderId, GetTenderCallback callback);
	
	public List<HoursSet> getOpeningHours(String mid, String token, Object... params) throws RESTException;
	public void getOpeningHours(String mId, String token, GetOpeningHoursCallback callback, Object... params);
	public HoursSet createOpeningHour(String mid, String token, HoursSet openingHours) throws RESTException;
	public void createOpeningHour(String mId, String token, HoursSet openingHours, CreateOpeningHourCallback callback);
	public HoursSet getOpeningHour(String mid, String token, String hId) throws RESTException;
	public void getOpeningHour(String mId, String token, String hId, GetOpeningHourCallback callback);
	public HoursSet updateOpeningHour(String mid, String token, String hId, HoursSet hoursSet) throws RESTException;
	public void updateOpeningHour(String mId, String token, String hid, HoursSet hoursSet, UpdateOpeningHourCallback callback);
	public HoursSet deleteOpeningHour(String mid, String token, String hId) throws RESTException;
	public void deleteOpeningHour(String mId, String token, String hId, DeleteOpeningHourCallback callback);
	
	public List<TaxRate> getTaxRates(String mId, String token, Object... params) throws RESTException;
	public void getTaxRates(String mId, String token, GetTaxRatesCallback callback, Object... params);
	public TaxRate createTaxRate(String mId, String token, TaxRate taxRate) throws RESTException;
	public void createTaxRate(String mId, String token, TaxRate taxRate, CreateTaxRateCallback callback);
	public TaxRate getTaxRate(String mId, String token, String taxId) throws RESTException;
	public void getTaxRate(String mId, String token, String taxId, GetTaxRateCallback callback);
	public TaxRate updateTaxRate(String mId, String token, String taxId, TaxRate update) throws RESTException;
	public void updateTaxRate(String mId, String token, String taxId, TaxRate update, UpdateTaxRateCallback callback);
	public TaxRate deleteTaxRate(String mId, String token, String taxId) throws RESTException;
	public void deleteTaxRate(String mId, String token, String taxId, DeleteTaxRateCallback callback);
	
	public TaxRateSummary getTaxRateSummary(String mId, String token, Object... params) throws RESTException;
	public void getTaxRateSummary(String mId, String token, GetTaxRateSummaryCallback callback, Object... params);
	
}
