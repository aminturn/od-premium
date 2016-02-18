package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.common.Address;
import com.trubeacon.cloverandroidapi.common.HoursSet;
import com.trubeacon.cloverandroidapi.common.Role;
import com.trubeacon.cloverandroidapi.common.WrappedList;
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

public class MerchantServiceImpl implements MerchantService,
											GetMerchantCallback,
											UpdateMerchantCallback,
											GetPermissionsCallback,
											GetMerchantAddressCallback,
											GetMerchantPropertiesCallback,
											UpdateMerchantPropertiesCallback,
											GetTipSuggestionsCallback,
											GetTipSuggestionCallback,
											UpdateTipSuggestionCallback,
											GetOrderTypesCallback,
											CreateOrderTypeCallback,
											GetOrderTypeCallback,
											UpdateOrderTypeCallback,
											DeleteOrderTypeCallback,
											GetRolesCallback,
											CreateRoleCallback,
											GetRoleCallback,
											UpdateRoleCallback,
											DeleteRoleCallback,
											GetTendersCallback,
											GetTenderCallback,
											GetOpeningHoursCallback,
											CreateOpeningHourCallback,
											GetOpeningHourCallback,
											UpdateOpeningHourCallback,
											DeleteOpeningHourCallback,
											GetTaxRatesCallback,
											CreateTaxRateCallback,
											GetTaxRateCallback,
											UpdateTaxRateCallback,
											DeleteTaxRateCallback,
											GetTaxRateSummaryCallback,
											GetDevicesCallback {
	
	private GetMerchantCallback getMerchantCallback;
	private UpdateMerchantCallback updateMerchantCallback;
	private GetPermissionsCallback getPermissionsCallback;
	private GetMerchantAddressCallback getMerchantAddressCallback;
	private GetMerchantPropertiesCallback getMerchantPropertiesCallback;
	private UpdateMerchantPropertiesCallback updateMerchantPropertiesCallback;
	private GetTipSuggestionsCallback getTipSuggestionsCallback;
	private GetTipSuggestionCallback getTipSuggestionCallback;
	private UpdateTipSuggestionCallback updateTipSuggestionCallback;
	private GetOrderTypesCallback getOrderTypesCallback;
	private CreateOrderTypeCallback createOrderTypeCallback;
	private GetOrderTypeCallback getOrderTypeCallback;
	private UpdateOrderTypeCallback updateOrderTypeCallback;
	private DeleteOrderTypeCallback deleteOrderTypeCallback;
	private GetRolesCallback getRolesCallback;
	private CreateRoleCallback createRoleCallback;
	private GetRoleCallback getRoleCallback;
	private UpdateRoleCallback updateRoleCallback;
	private DeleteRoleCallback deleteRoleCallback;
	private GetTendersCallback getTendersCallback;
	private GetTenderCallback getTenderCallback;
	private GetOpeningHoursCallback getOpeningHoursCallback;
	private CreateOpeningHourCallback createOpeningHourCallback;
	private GetOpeningHourCallback getOpeningHourCallback;
	private UpdateOpeningHourCallback updateOpeningHourCallback;
	private DeleteOpeningHourCallback deleteOpeningHourCallback;
	private GetTaxRatesCallback getTaxRatesCallback;
	private CreateTaxRateCallback createTaxRateCallback;
	private GetTaxRateCallback getTaxRateCallback;
	private UpdateTaxRateCallback updateTaxRateCallback;
	private DeleteTaxRateCallback deleteTaxRateCallback;
	private GetTaxRateSummaryCallback getTaxRateSummaryCallback;
	private GetDevicesCallback getDevicesCallback;
	
	@Override
    public Merchant getMerchant(String mId, String token) throws RESTException {
	    return new GetMerchant(mId, token).execute();
    }

	@Override
    public Merchant updateMerchant(String mId, String token, Merchant update)
            throws RESTException {
		return new UpdateMerchant(mId, token, update).execute();
	}

	@Override
    public Address getMerchantAddress(String mId, String token)
            throws RESTException {
		return new GetMerchantAddress(mId, token).execute();
	}

	@Override
	public List<Permission> getPermissions(String mId, String token) throws RESTException {
		return new GetPermissions(mId, token).execute();
	}
	
	@Override
	public void getPermissions(String mId, String token, GetPermissionsCallback callback) {
		this.getPermissionsCallback = callback;
		new GetPermissions(mId, token).execute(this);
	}
	
	@Override
    public Properties getMerchantProperties(String mId, String token)
            throws RESTException {
		return new GetMerchantProperties(mId, token).execute();
    }

	@Override
    public Properties updateMerchantProperties(String mId, String token,
            Properties update) throws RESTException {
		return new UpdateMerchantProperties(mId, token, update).execute();
    }

	@Override
    public List<TipSuggestion> getTipSuggestions(String mId, String token, Object... params)
            throws RESTException {
		return new GetTipSuggestions(mId, token, params).execute();
    }

	@Override
    public TipSuggestion getTipSuggestion(String mId, String token, String tId)
            throws RESTException {
		return new GetTipSuggestion(mId, token, tId).execute();
    }

	@Override
    public TipSuggestion updateTipSuggestion(String mId, String token,
            String id, TipSuggestion update) throws RESTException {
		return new UpdateTipSuggestion(mId, token, id, update).execute();
    }

	@Override
    public List<OrderType> getOrderTypes(String mId, String token, Object... params)
            throws RESTException {
		return new GetOrderTypes(mId, token, params).execute();
    }

	@Override
    public OrderType createOrderType(String mId, String token,
            OrderType orderType) throws RESTException {
		return new CreateOrderType(mId, token, orderType).execute();
    }

	@Override
    public OrderType getOrderType(String mId, String token, String orderTypeId)
            throws RESTException {
		return new GetOrderType(mId, token, orderTypeId).execute();
    }

	@Override
    public OrderType updateOrderType(String mId, String token,
            String orderTypeId, OrderType update) throws RESTException {
	    return new UpdateOrderType(mId, token, orderTypeId, update).execute();
    }

	@Override
    public OrderType deleteOrderType(String mId, String token, String orderTypeId) throws RESTException {
	    return new DeleteOrderType(mId, token, orderTypeId).execute();
    }

	@Override
    public List<Role> getRoles(String mId, String token, Object... params) throws RESTException {
	    return new GetRoles(mId, token, params).execute();
    }

	@Override
    public Role createRole(String mId, String token, Role role) throws RESTException {
	    return new CreateRole(mId, token, role).execute();
    }

	@Override
    public Role getRole(String mId, String token, String rId) throws RESTException {
	    return new GetRole(mId, token, rId).execute();
    }

	@Override
    public Role updateRole(String mId, String token, String rId, Role role) throws RESTException {
	    return new UpdateRole(mId, token, rId, role).execute();
    }

	@Override
    public Role deleteRole(String mId, String token, String rId) throws RESTException {
	    return new DeleteRole(mId, token, rId).execute();
    }

	@Override
    public List<Tender> getTenders(String mId, String token, Object... params) throws RESTException {
	    return new GetTenders(mId, token, params).execute();
    }

	@Override
    public Tender getTender(String mId, String token, String tenderId) throws RESTException {
	    return new GetTender(mId, token, tenderId).execute();
    }

	@Override
    public List<HoursSet> getOpeningHours(String mId, String token, Object... params) throws RESTException {
	    return new GetOpeningHours(mId, token, params).execute();
    }

	@Override
    public HoursSet createOpeningHour(String mId, String token,
            HoursSet openingHours) throws RESTException {
	    return new CreateOpeningHour(mId, token, openingHours).execute();
    }

	@Override
    public HoursSet getOpeningHour(String mId, String token, String hId) throws RESTException {
	    return new GetOpeningHour(mId, token, hId).execute();
    }

	@Override
    public HoursSet updateOpeningHour(String mId, String token, String hId,
            HoursSet hoursSet) throws RESTException {
	    return new UpdateOpeningHour(mId, token, hId, hoursSet).execute();
    }

	@Override
    public HoursSet deleteOpeningHour(String mId, String token, String hId)
            throws RESTException {
	    return new DeleteOpeningHour(mId, token, hId).execute();
    }
	
	@Override
    public TaxRate createTaxRate(String mId, String token, TaxRate taxRate) throws RESTException {
	    return new CreateTaxRate(mId, token, taxRate).execute();
    }

	@Override
    public List<TaxRate> getTaxRates(String mId, String token, Object... params) throws RESTException {
	    return new GetTaxRates(mId, token, params).execute();
    }

	@Override
    public TaxRate getTaxRate(String mId, String token, String taxId) throws RESTException {
	    return new GetTaxRate(mId, token, taxId).execute();
    }

	@Override
    public TaxRate updateTaxRate(String mId, String token, String taxId, TaxRate update) throws RESTException {
	    return new UpdateTaxRate(mId, token, taxId, update).execute();
    }

	@Override
    public TaxRate deleteTaxRate(String mId, String token, String taxId) throws RESTException {
	    return new DeleteTaxRate(mId, token, taxId).execute();
    }

	@Override
    public TaxRateSummary getTaxRateSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetTaxRateSummary(mId, token, params).execute();
    }


	@Override
    public void getMerchant(String mId, String token,
            GetMerchantCallback callback) {
	    this.getMerchantCallback = callback;
	    new GetMerchant(mId, token).execute(this);
    }

	@Override
    public void updateMerchant(String mId, String token, Merchant update,
            UpdateMerchantCallback callback) {
	    this.updateMerchantCallback = callback;
	    new UpdateMerchant(mId, token, update).execute(this);
    }

	@Override
    public void getMerchantAddress(String mId, String token,
            GetMerchantAddressCallback callback) {
	    this.getMerchantAddressCallback = callback;
	    new GetMerchantAddress(mId, token).execute(this);
    }

	@Override
    public void getMerchantProperties(String mId, String token,
            GetMerchantPropertiesCallback callback) {
	    this.getMerchantPropertiesCallback = callback;
	    new GetMerchantProperties(mId, token).execute(this);
    }

	@Override
    public void updateMerchantProperties(String mId, String token,
            Properties update, UpdateMerchantPropertiesCallback callback) {
	    this.updateMerchantPropertiesCallback = callback;
	    new UpdateMerchantProperties(mId, token, update).execute(this);
    }

	@Override
    public void getTipSuggestions(String mId, String token,
            GetTipSuggestionsCallback callback, Object... params) {
	    this.getTipSuggestionsCallback = callback;
	    new GetTipSuggestions(mId, token, params).execute(this);
    }

	@Override
    public void getTipSuggestion(String mId, String token, String tId,
            GetTipSuggestionCallback callback) {
	    this.getTipSuggestionCallback = callback;
	    new GetTipSuggestion(mId, token, tId).execute(this);
    }

	@Override
    public void updateTipSuggestion(String mId, String token, String id,
            TipSuggestion update, UpdateTipSuggestionCallback callback) {
	    this.updateTipSuggestionCallback = callback;
	    new UpdateTipSuggestion(mId, token, id, update).execute(this);
    }

	@Override
    public void getOrderTypes(String mId, String token,
            GetOrderTypesCallback callback, Object... params) {
	    this.getOrderTypesCallback = callback;
	    new GetOrderTypes(mId, token, params).execute(this);
    }
	
	@Override
    public void createOrderType(String mId, String token, OrderType orderType,
            CreateOrderTypeCallback callback) {
	    this.createOrderTypeCallback = callback;
	    new CreateOrderType(mId, token, orderType).execute(this);
    }

	@Override
    public void getOrderType(String mId, String token, String orderTypeId,
            GetOrderTypeCallback callback) {
	    this.getOrderTypeCallback = callback;
	    new GetOrderType(mId, token, orderTypeId).execute(this);
    }

	@Override
    public void updateOrderType(String mId, String token, String orderTypeId,
            OrderType update, UpdateOrderTypeCallback callback) {
	    this.updateOrderTypeCallback = callback;
	    new UpdateOrderType(mId, token, orderTypeId, update).execute(this);
    }

	@Override
    public void deleteOrderType(String mId, String token, String orderTypeId,
            DeleteOrderTypeCallback callback) {
	    this.deleteOrderTypeCallback = callback;
	    new DeleteOrderType(mId, token, orderTypeId).execute(this);
    }

	@Override
    public void getRoles(String mId, String token, GetRolesCallback callback,
            Object... params) {
	    this.getRolesCallback = callback;
	    new GetRoles(mId, token, params).execute(this);
    }

	@Override
    public void createRole(String mId, String token, Role role,
            CreateRoleCallback callback) {
	    this.createRoleCallback = callback;
	    new CreateRole(mId, token, role).execute(this);
    }

	@Override
    public void getRole(String mId, String token, String rId,
            GetRoleCallback callback) {
	    this.getRoleCallback = callback;
	    new GetRole(mId, token, rId).execute(this);
    }

	@Override
    public void updateRole(String mId, String token, String rId, Role role,
            UpdateRoleCallback callback) {
	    this.updateRoleCallback = callback;
	    new UpdateRole(mId, token, rId, role).execute(this);
    }

	@Override
    public void deleteRole(String mId, String token, String rId,
            DeleteRoleCallback callback) {
	    this.deleteRoleCallback = callback;
	    new DeleteRole(mId, token, rId).execute(this);
    }

	@Override
    public void getTenders(String mId, String token,
            GetTendersCallback callback, Object... params) {
	    this.getTendersCallback = callback;
	    new GetTenders(mId, token, params).execute(this);
    }

	@Override
    public void getTender(String mId, String token, String tenderId,
            GetTenderCallback callback) {
	    this.getTenderCallback = callback;
	    new GetTender(mId, token, tenderId).execute(this);
    }

	@Override
    public void getOpeningHours(String mId, String token,
            GetOpeningHoursCallback callback, Object... params) {
	    this.getOpeningHoursCallback = callback;
	    new GetOpeningHours(mId, token, params).execute(this);
    }

	@Override
    public void createOpeningHour(String mId, String token,
            HoursSet openingHours, CreateOpeningHourCallback callback) {
	    this.createOpeningHourCallback = callback;
	    new CreateOpeningHour(mId, token, openingHours).execute(this);
    }

	@Override
    public void getOpeningHour(String mId, String token, String hId,
            GetOpeningHourCallback callback) {
	    this.getOpeningHourCallback = callback;
	    new GetOpeningHour(mId, token, hId).execute(this);
    }

	@Override
    public void updateOpeningHour(String mId, String token, String hId,
            HoursSet hoursSet, UpdateOpeningHourCallback callback) {
	    this.updateOpeningHourCallback = callback;
	    new UpdateOpeningHour(mId, token, hId, hoursSet).execute(this);
    }

	@Override
    public void deleteOpeningHour(String mId, String token, String hId,
            DeleteOpeningHourCallback callback) {
	    this.deleteOpeningHourCallback = callback;
	    new DeleteOpeningHour(mId, token, hId).execute(this);
    }

	@Override
    public void getTaxRates(String mId, String token,
            GetTaxRatesCallback callback, Object... params) {
	    this.getTaxRatesCallback = callback;
	    new GetTaxRates(mId, token, params).execute(this);
    }

	@Override
    public void createTaxRate(String mId, String token, TaxRate taxRate,
            CreateTaxRateCallback callback) {
	    this.createTaxRateCallback = callback;
	    new CreateTaxRate(mId, token, taxRate).execute(this);
    }

	@Override
    public void getTaxRate(String mId, String token, String taxId,
            GetTaxRateCallback callback) {
	    this.getTaxRateCallback = callback;
	    new GetTaxRate(mId, token, taxId).execute(this);
    }

	@Override
    public void updateTaxRate(String mId, String token, String taxId,
            TaxRate update, UpdateTaxRateCallback callback) {
	    this.updateTaxRateCallback = callback;
	    new UpdateTaxRate(mId, token, taxId, update).execute(this);
    }

	@Override
    public void deleteTaxRate(String mId, String token, String taxId,
            DeleteTaxRateCallback callback) {
	    this.deleteTaxRateCallback = callback;
	    new DeleteTaxRate(mId, token, taxId).execute(this);
    }
	
	@Override
    public void getTaxRateSummary(String mId, String token,
            GetTaxRateSummaryCallback callback, Object... params) {
	    this.getTaxRateSummaryCallback = callback;
	    new GetTaxRateSummary(mId, token, params).execute(this);
    }

	@Override
    public Devices getDevices(String mId, String token) throws RESTException {
	    return new GetDevices(mId, token).execute();
    }

	@Override
    public void getDevices(String mId, String token, GetDevicesCallback callback) {
	    this.getDevicesCallback = callback;
	    new GetDevices(mId, token).execute(this);
    }
	
	@Override
	public void onGetMerchant(Merchant result) {
		if (getMerchantCallback != null) {
			getMerchantCallback.onGetMerchant(result);
		}
    }

	@Override
	public void onFailGetMerchant(Error error) {
		if (getMerchantCallback != null) {
			getMerchantCallback.onFailGetMerchant(error);
		}
    }

	@Override
	public void onUpdateMerchant(Merchant result) {
		if (updateMerchantCallback != null) {
			updateMerchantCallback.onUpdateMerchant(result);
		}
    }

	@Override
	public void onFailUpdateMerchant(Error error) {
		if (updateMerchantCallback != null) {
			updateMerchantCallback.onFailUpdateMerchant(error);
		}
    }

	@Override
	public void onGetPermissions(List<Permission> permissions) {
		if (getPermissionsCallback != null) {
			getPermissionsCallback.onGetPermissions(permissions);
		}
	}
	
	@Override
	public void onFailGetPermissions(Error error) {
		if (getPermissionsCallback != null) {
			getPermissionsCallback.onFailGetPermissions(error);
		}
	}
	
	@Override
	public void onGetMerchantAddress(Address result) {
		if (getMerchantAddressCallback != null) {
			getMerchantAddressCallback.onGetMerchantAddress(result);
		}
    }

	@Override
	public void onFailGetMerchantAddress(Error error) {
		if (getMerchantAddressCallback != null) {
			getMerchantAddressCallback.onFailGetMerchantAddress(error);
		}
    }

	@Override
	public void onGetMerchantProperties(Properties result) {
		if (getMerchantPropertiesCallback != null) {
			getMerchantPropertiesCallback.onGetMerchantProperties(result);
		}
    }
	
	@Override
	public void onFailGetMerchantProperties(Error error) {
		if (getMerchantPropertiesCallback != null) {
			getMerchantPropertiesCallback.onFailGetMerchantProperties(error);
		}
    }

	@Override
	public void onUpdateMerchantProperties(Properties result) {
		if (updateMerchantPropertiesCallback != null) {
			updateMerchantPropertiesCallback.onUpdateMerchantProperties(result);
		}
    }

	@Override
	public void onFailUpdateMerchantProperties(Error error) {
		if (updateMerchantPropertiesCallback != null) {
			updateMerchantPropertiesCallback.onFailUpdateMerchantProperties(error);
		}
    }

	@Override
	public void onGetTipSuggestions(WrappedList<TipSuggestion> result) {
		if (getTipSuggestionsCallback != null) {
			getTipSuggestionsCallback.onGetTipSuggestions(result);
		}
    }

	@Override
	public void onFailGetTipSuggestions(Error error) {
		if (getTipSuggestionsCallback != null) {
			getTipSuggestionsCallback.onFailGetTipSuggestions(error);
		}
    }

	@Override
	public void onGetTipSuggestion(TipSuggestion result) {
		if (getTipSuggestionCallback != null) {
			getTipSuggestionCallback.onGetTipSuggestion(result);
		}
    }

	@Override
	public void onFailGetTipSuggestion(Error error) {
		if (getTipSuggestionCallback != null) {
			getTipSuggestionCallback.onFailGetTipSuggestion(error);
		}
    }

	@Override
	public void onUpdateTipSuggestion(TipSuggestion result) {
		if (updateTipSuggestionCallback != null) {
			updateTipSuggestionCallback.onUpdateTipSuggestion(result);
		}
    }

	@Override
	public void onFailUpdateTipSuggestion(Error error) {
		if (updateTipSuggestionCallback != null) {
			updateTipSuggestionCallback.onFailUpdateTipSuggestion(error);
		}
    }

	@Override
	public void onGetOrderTypes(WrappedList<OrderType> result) {
		if (getOrderTypesCallback != null) {
			getOrderTypesCallback.onGetOrderTypes(result);
		}
    }

	@Override
	public void onFailGetOrderTypes(Error error) {
		if (getOrderTypesCallback != null) {
			getOrderTypesCallback.onFailGetOrderTypes(error);
		}
    }

	@Override
	public void onCreateOrderType(OrderType result) {
		if (createOrderTypeCallback != null) {
			createOrderTypeCallback.onCreateOrderType(result);
		}
    }

	@Override
	public void onFailCreateOrderType(Error error) {
		if (createOrderTypeCallback != null) {
			createOrderTypeCallback.onFailCreateOrderType(error);
		}
    }

	@Override
	public void onGetOrderType(OrderType result) {
		if (getOrderTypeCallback != null) {
			getOrderTypeCallback.onGetOrderType(result);
		}
    }

	@Override
	public void onFailGetOrderType(Error error) {
		if (getOrderTypeCallback != null) {
			getOrderTypeCallback.onFailGetOrderType(error);
		}
    }

	@Override
	public void onUpdateOrderType(OrderType result) {
		if (updateOrderTypeCallback != null) {
			updateOrderTypeCallback.onUpdateOrderType(result);
		}
    }

	@Override
	public void onFailUpdateOrderType(Error error) {
		if (updateOrderTypeCallback != null) {
			updateOrderTypeCallback.onFailUpdateOrderType(error);
		}
    }

	@Override
	public void onDeleteOrderType(OrderType result) {
		if (deleteOrderTypeCallback != null) {
			deleteOrderTypeCallback.onDeleteOrderType(result);
		}
    }

	@Override
	public void onFailDeleteOrderType(Error error) {
		if (deleteOrderTypeCallback != null) {
			deleteOrderTypeCallback.onFailDeleteOrderType(error);
		}
    }

	@Override
	public void onGetRoles(WrappedList<Role> result) {
		if (getRolesCallback != null) {
			getRolesCallback.onGetRoles(result);
		}
    }

	@Override
	public void onFailGetRoles(Error error) {
		if (getRolesCallback != null) {
			getRolesCallback.onFailGetRoles(error);
		}
    }

	@Override
	public void onCreateRole(Role result) {
		if (createRoleCallback != null) {
			createRoleCallback.onCreateRole(result);
		}
    }

	@Override
	public void onFailCreateRole(Error error) {
		if (createRoleCallback != null) {
			createRoleCallback.onFailCreateRole(error);
		}
    }

	@Override
	public void onGetRole(Role result) {
		if (getRoleCallback != null) {
			getRoleCallback.onGetRole(result);
		}
    }

	@Override
	public void onFailGetRole(Error error) {
		if (getRoleCallback != null) {
			getRoleCallback.onFailGetRole(error);
		}
    }

	@Override
	public void onUpdateRole(Role result) {
		if (updateRoleCallback != null) {
			updateRoleCallback.onUpdateRole(result);
		}
    }

	@Override
	public void onFailUpdateRole(Error error) {
		if (updateRoleCallback != null) {
			updateRoleCallback.onFailUpdateRole(error);
		}
    }

	@Override
	public void onDeleteRole(Role result) {
		if (deleteRoleCallback != null) {
			deleteRoleCallback.onDeleteRole(result);
		}
    }

	@Override
	public void onFailDeleteRole(Error error) {
		if (deleteRoleCallback != null) {
			deleteRoleCallback.onFailDeleteRole(error);
		}
    }

	@Override
	public void onGetTenders(WrappedList<Tender> result) {
		if (getTendersCallback != null) {
			getTendersCallback.onGetTenders(result);
		}
    }

	@Override
	public void onFailGetTenders(Error error) {
		if (getTendersCallback != null) {
			getTendersCallback.onFailGetTenders(error);
		}
    }

	@Override
	public void onGetTender(Tender result) {
		if (getTenderCallback != null) {
			getTenderCallback.onGetTender(result);
		}
    }

	@Override
	public void onFailGetTender(Error error) {
		if (getTenderCallback != null) {
			getTenderCallback.onFailGetTender(error);
		}
    }

	@Override
	public void onGetOpeningHours(WrappedList<HoursSet> result) {
		if (getOpeningHoursCallback != null) {
			getOpeningHoursCallback.onGetOpeningHours(result);
		}
    }

	@Override
	public void onFailGetOpeningHours(Error error) {
		if (getOpeningHoursCallback != null) {
			getOpeningHoursCallback.onFailGetOpeningHours(error);
		}
    }

	@Override
	public void onCreateOpeningHour(HoursSet result) {
		if (createOpeningHourCallback != null) {
			createOpeningHourCallback.onCreateOpeningHour(result);
		}
    }

	@Override
	public void onFailCreateOpeningHour(Error error) {
		if (createOpeningHourCallback != null) {
			createOpeningHourCallback.onFailCreateOpeningHour(error);
		}
    }

	@Override
	public void onGetOpeningHour(HoursSet result) {
		if (getOpeningHourCallback != null) {
			getOpeningHourCallback.onGetOpeningHour(result);
		}
    }

	@Override
	public void onFailGetOpeningHour(Error error) {
		if (getOpeningHourCallback != null) {
			getOpeningHourCallback.onFailGetOpeningHour(error);
		}
    }

	@Override
	public void onUpdateOpeningHour(HoursSet result) {
		if (updateOpeningHourCallback != null) {
			updateOpeningHourCallback.onUpdateOpeningHour(result);
		}
    }

	@Override
	public void onFailUpdateOpeningHour(Error error) {
		if (updateOpeningHourCallback != null) {
			updateOpeningHourCallback.onFailUpdateOpeningHour(error);
		}
    }

	@Override
	public void onDeleteOpeningHour(HoursSet result) {
		if (deleteOpeningHourCallback != null) {
			deleteOpeningHourCallback.onDeleteOpeningHour(result);
		}
    }

	@Override
	public void onFailDeleteOpeningHour(Error error) {
		if (deleteOpeningHourCallback != null) {
			deleteOpeningHourCallback.onFailDeleteOpeningHour(error);
		}
    }

	@Override
	public void onGetTaxRates(WrappedList<TaxRate> result) {
		if (getTaxRatesCallback != null) {
			getTaxRatesCallback.onGetTaxRates(result);
		}
    }

	@Override
	public void onFailGetTaxRates(Error error) {
		if (getTaxRatesCallback != null) {
			getTaxRatesCallback.onFailGetTaxRates(error);
		}
    }

	@Override
	public void onCreateTaxRate(TaxRate result) {
		if (createTaxRateCallback != null) {
			createTaxRateCallback.onCreateTaxRate(result);
		}
    }

	@Override
	public void onFailCreateTaxRate(Error error) {
		if (createTaxRateCallback != null) {
			createTaxRateCallback.onFailCreateTaxRate(error);
		}
    }

	@Override
	public void onGetTaxRate(TaxRate result) {
		if (getTaxRateCallback != null) {
			getTaxRateCallback.onGetTaxRate(result);
		}
    }

	@Override
	public void onFailGetTaxRate(Error error) {
		if (getTaxRateCallback != null) {
			getTaxRateCallback.onFailGetTaxRate(error);
		}
    }

	@Override
	public void onUpdateTaxRate(TaxRate result) {
		if (updateTaxRateCallback != null) {
			updateTaxRateCallback.onUpdateTaxRate(result);
		}
    }

	@Override
	public void onFailUpdateTaxRate(Error error) {
		if (updateTaxRateCallback != null) {
			updateTaxRateCallback.onFailUpdateTaxRate(error);
		}
    }

	@Override
	public void onDeleteTaxRate(TaxRate result) {
		if (deleteTaxRateCallback != null) {
			deleteTaxRateCallback.onDeleteTaxRate(result);
		}
    }

	@Override
	public void onFailDeleteTaxRate(Error error) {
		if (deleteTaxRateCallback != null) {
			deleteTaxRateCallback.onFailDeleteTaxRate(error);
		}
    }

	@Override
	public void onGetTaxRateSummary(TaxRateSummary result) {
		if (getTaxRateSummaryCallback != null) {
			getTaxRateSummaryCallback.onGetTaxRateSummary(result);
		}
    }

	@Override
	public void onFailGetTaxRateSummary(Error error) {
		if (getTaxRateSummaryCallback != null) {
			getTaxRateSummaryCallback.onFailGetTaxRateSummary(error);
		}
    }

	@Override
	public void onGetDevices(Devices result) {
		if (this.getDevicesCallback != null) {
			this.getDevicesCallback.onGetDevices(result);
		}
	}
	
	@Override
	public void onFailGetDevices(Error error) {
		if (this.getDevicesCallback != null) {
			this.getDevicesCallback.onFailGetDevices(error);
		}
	}

}
