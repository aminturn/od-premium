package com.trubeacon.cloverandroidapi;

import com.trubeacon.cloverandroidapi.account.service.AccountService;
import com.trubeacon.cloverandroidapi.account.service.AccountServiceImpl;
import com.trubeacon.cloverandroidapi.account.service.GetMerchants.GetMerchantsCallback;
import com.trubeacon.cloverandroidapi.app.AppBillingInfo;
import com.trubeacon.cloverandroidapi.app.AppMeteredEvent;
import com.trubeacon.cloverandroidapi.app.service.AppService;
import com.trubeacon.cloverandroidapi.app.service.AppServiceImpl;
import com.trubeacon.cloverandroidapi.app.service.CreateMeteredEvent.CreateMeteredEventCallback;
import com.trubeacon.cloverandroidapi.app.service.GetBillingInfo.GetBillingInfoCallback;
import com.trubeacon.cloverandroidapi.auth.AccessToken;
import com.trubeacon.cloverandroidapi.auth.service.AuthenticationService;
import com.trubeacon.cloverandroidapi.auth.service.AuthenticationServiceImpl;
import com.trubeacon.cloverandroidapi.auth.service.GetAccessToken.GetAccessTokenCallback;
import com.trubeacon.cloverandroidapi.client.CloverUrlResolver;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTServiceResolver;
import com.trubeacon.cloverandroidapi.client.RESTServiceResolverHolder;
import com.trubeacon.cloverandroidapi.client.UrlResolver;
import com.trubeacon.cloverandroidapi.client.UrlResolverHolder;
import com.trubeacon.cloverandroidapi.common.Address;
import com.trubeacon.cloverandroidapi.common.HoursSet;
import com.trubeacon.cloverandroidapi.common.Role;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.customer.Customer;
import com.trubeacon.cloverandroidapi.customer.EmailAddress;
import com.trubeacon.cloverandroidapi.customer.PhoneNumber;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomer.CreateCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomerAddress.CreateCustomerAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomerEmailAddress.CreateCustomerEmailAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomerPhoneNumber.CreateCustomerPhoneNumberCallback;
import com.trubeacon.cloverandroidapi.customer.service.CustomerService;
import com.trubeacon.cloverandroidapi.customer.service.CustomerServiceImpl;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomer.DeleteCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomerAddress.DeleteCustomerAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomerEmailAddress.DeleteCustomerEmailAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomerPhoneNumber.DeleteCustomerPhoneNumberCallback;
import com.trubeacon.cloverandroidapi.customer.service.GetCustomer.GetCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.GetCustomers.GetCustomersCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomer.UpdateCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomerAddress.UpdateCustomerAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomerEmailAddress.UpdateCustomerEmailAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomerPhoneNumber.UpdateCustomerPhoneNumberCallback;
import com.trubeacon.cloverandroidapi.discount.service.CreateDiscount.CreateDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.DeleteDiscount.DeleteDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.DiscountService;
import com.trubeacon.cloverandroidapi.discount.service.DiscountServiceImpl;
import com.trubeacon.cloverandroidapi.discount.service.GetDiscount.GetDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.GetDiscounts.GetDiscountsCallback;
import com.trubeacon.cloverandroidapi.discount.service.UpdateDiscount.UpdateDiscountCallback;
import com.trubeacon.cloverandroidapi.employee.Employee;
import com.trubeacon.cloverandroidapi.employee.EmployeeSummary;
import com.trubeacon.cloverandroidapi.employee.Shift;
import com.trubeacon.cloverandroidapi.employee.service.CreateEmployee.CreateEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.CreateEmployeeShift.CreateEmployeeShiftCallback;
import com.trubeacon.cloverandroidapi.employee.service.DeleteEmployee.DeleteEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.EmployeeService;
import com.trubeacon.cloverandroidapi.employee.service.EmployeeServiceImpl;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployee.GetEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeOrders.GetEmployeeOrdersCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeShift.GetEmployeeShiftCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeShifts.GetEmployeeShiftsCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeSummary.GetEmployeeSummaryCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployees.GetEmployeesCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetShift.GetShiftCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetShifts.GetShiftsCallback;
import com.trubeacon.cloverandroidapi.employee.service.UpdateEmployee.UpdateEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.UpdateEmployeeShift.UpdateEmployeeShiftCallback;
import com.trubeacon.cloverandroidapi.inventory.Attribute;
import com.trubeacon.cloverandroidapi.inventory.Category;
import com.trubeacon.cloverandroidapi.inventory.CategoryItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.Item;
import com.trubeacon.cloverandroidapi.inventory.ItemGroup;
import com.trubeacon.cloverandroidapi.inventory.ItemStock;
import com.trubeacon.cloverandroidapi.inventory.ItemSummary;
import com.trubeacon.cloverandroidapi.inventory.Modifier;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroupItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.Option;
import com.trubeacon.cloverandroidapi.inventory.OptionItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.Tag;
import com.trubeacon.cloverandroidapi.inventory.TagItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.TaxRateItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.service.CreateAttribute.CreateAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateAttributeOption.CreateAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateCategory.CreateCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateCategoryItemAssociations.CreateCategoryItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateItem.CreateItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateItemGroup.CreateItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateModifierGroup.CreateModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateModifierGroupItemAssociations.CreateModifierGroupItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateModifierGroupModifier.CreateModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateOptionItemAssociations.CreateOptionItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateTag.CreateTagCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateTagItemAssociations.CreateTagItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateTaxRateItemAssociations.CreateTaxRateItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteAttribute.DeleteAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteAttributeOption.DeleteAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteCategory.DeleteCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteCategoryItemAssociations.DeleteCategoryItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteItem.DeleteItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteItemGroup.DeleteItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteItemStock.DeleteItemStockCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteModifierGroup.DeleteModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteModifierGroupItemAssociations.DeleteModifierGroupItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteModifierGroupModifier.DeleteModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteOptionItemAssociations.DeleteOptionItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteTag.DeleteTagCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteTagItemAssociations.DeleteTagItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteTaxRateItemAssociations.DeleteTaxRateItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttribute.GetAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttributeOption.GetAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttributeOptions.GetAttributeOptionsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttributes.GetAttributesCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetCategories.GetCategoriesCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetCategory.GetCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetCategoryItems.GetCategoryItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItem.GetItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemCategories.GetItemCategoriesCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemGroup.GetItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemGroups.GetItemGroupsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemStock.GetItemStockCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemStocks.GetItemStocksCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemSummary.GetItemSummaryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemTags.GetItemTagsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItems.GetItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroup.GetModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroupModifier.GetModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroupModifiers.GetModifierGroupModifiersCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroups.GetModifierGroupsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifiers.GetModifiersCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetOptions.GetOptionsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTag.GetTagCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTagItems.GetTagItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTags.GetTagsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTaxRateItems.GetTaxRateItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.InventoryService;
import com.trubeacon.cloverandroidapi.inventory.service.InventoryServiceImpl;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateAttribute.UpdateAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateAttributeOption.UpdateAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateCategory.UpdateCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateItem.UpdateItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateItemGroup.UpdateItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateItemStock.UpdateItemStockCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateModifierGroup.UpdateModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateModifierGroupModifier.UpdateModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateTag.UpdateTagCallback;
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
import com.trubeacon.cloverandroidapi.merchant.service.MerchantService;
import com.trubeacon.cloverandroidapi.merchant.service.MerchantServiceImpl;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateMerchant.UpdateMerchantCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateMerchantProperties.UpdateMerchantPropertiesCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateOpeningHour.UpdateOpeningHourCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateOrderType.UpdateOrderTypeCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateRole.UpdateRoleCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateTaxRate.UpdateTaxRateCallback;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateTipSuggestion.UpdateTipSuggestionCallback;
import com.trubeacon.cloverandroidapi.notification.AppNotification;
import com.trubeacon.cloverandroidapi.notification.service.CreateAppNotification.CreateAppNotificationCallback;
import com.trubeacon.cloverandroidapi.notification.service.CreateDeviceNotification.CreateDeviceNotificationCallback;
import com.trubeacon.cloverandroidapi.notification.service.NotificationService;
import com.trubeacon.cloverandroidapi.notification.service.NotificationServiceImpl;
import com.trubeacon.cloverandroidapi.order.Discount;
import com.trubeacon.cloverandroidapi.order.DiscountSummary;
import com.trubeacon.cloverandroidapi.order.LineItem;
import com.trubeacon.cloverandroidapi.order.Modification;
import com.trubeacon.cloverandroidapi.order.Order;
import com.trubeacon.cloverandroidapi.order.OrderType;
import com.trubeacon.cloverandroidapi.order.service.CreateOrder.CreateOrderCallback;
import com.trubeacon.cloverandroidapi.order.service.CreateOrderDiscount.CreateOrderDiscountCallback;
import com.trubeacon.cloverandroidapi.order.service.CreateOrderLineItem.CreateOrderLineItemCallback;
import com.trubeacon.cloverandroidapi.order.service.CreateOrderLineItemDiscount.CreateOrderLineItemDiscountCallback;
import com.trubeacon.cloverandroidapi.order.service.CreateOrderLineItemModification.CreateOrderLineItemModificationCallback;
import com.trubeacon.cloverandroidapi.order.service.CreateOrderPayment.CreateOrderPaymentCallback;
import com.trubeacon.cloverandroidapi.order.service.CreateOrderServiceCharge.CreateOrderServiceChargeCallback;
import com.trubeacon.cloverandroidapi.order.service.DeleteOrder.DeleteOrderCallback;
import com.trubeacon.cloverandroidapi.order.service.DeleteOrderDiscount.DeleteOrderDiscountCallback;
import com.trubeacon.cloverandroidapi.order.service.DeleteOrderLineItem.DeleteOrderLineItemCallback;
import com.trubeacon.cloverandroidapi.order.service.DeleteOrderLineItemDiscount.DeleteOrderLineItemDiscountCallback;
import com.trubeacon.cloverandroidapi.order.service.DeleteOrderLineItemModification.DeleteOrderLineItemModificationCallback;
import com.trubeacon.cloverandroidapi.order.service.DeleteOrderServiceCharge.DeleteOrderServiceChargeCallback;
import com.trubeacon.cloverandroidapi.order.service.ExchangeOrderLineItem.ExchangeOrderLineItemCallback;
import com.trubeacon.cloverandroidapi.order.service.GetDiscountSummary.GetDiscountSummaryCallback;
import com.trubeacon.cloverandroidapi.order.service.GetLineItem.GetLineItemCallback;
import com.trubeacon.cloverandroidapi.order.service.GetLineItems.GetLineItemsCallback;
import com.trubeacon.cloverandroidapi.order.service.GetOrder.GetOrderCallback;
import com.trubeacon.cloverandroidapi.order.service.GetOrderDiscounts.GetOrderDiscountsCallback;
import com.trubeacon.cloverandroidapi.order.service.GetOrderLineItem.GetOrderLineItemCallback;
import com.trubeacon.cloverandroidapi.order.service.GetOrderLineItems.GetOrderLineItemsCallback;
import com.trubeacon.cloverandroidapi.order.service.GetOrders.GetOrdersCallback;
import com.trubeacon.cloverandroidapi.order.service.OrderService;
import com.trubeacon.cloverandroidapi.order.service.OrderServiceImpl;
import com.trubeacon.cloverandroidapi.order.service.UpdateOrder.UpdateOrderCallback;
import com.trubeacon.cloverandroidapi.order.service.UpdateOrderLineItem.UpdateOrderLineItemCallback;
import com.trubeacon.cloverandroidapi.payment.CardSummary;
import com.trubeacon.cloverandroidapi.payment.Credit;
import com.trubeacon.cloverandroidapi.payment.CreditSummary;
import com.trubeacon.cloverandroidapi.payment.Payment;
import com.trubeacon.cloverandroidapi.payment.PaymentSummary;
import com.trubeacon.cloverandroidapi.payment.Refund;
import com.trubeacon.cloverandroidapi.payment.RefundSummary;
import com.trubeacon.cloverandroidapi.payment.ServiceCharge;
import com.trubeacon.cloverandroidapi.payment.Tender;
import com.trubeacon.cloverandroidapi.payment.TenderSummary;
import com.trubeacon.cloverandroidapi.payment.TipSuggestion;
import com.trubeacon.cloverandroidapi.payment.service.CreatePayment.CreatePaymentCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCardSummary.GetCardSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCredit.GetCreditCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCreditSummary.GetCreditSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCredits.GetCreditsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetEmployeePayments.GetEmployeePaymentsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetOrderPayments.GetOrderPaymentsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetPayment.GetPaymentCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetPaymentSummary.GetPaymentSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetPayments.GetPaymentsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetRefund.GetRefundCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetRefundSummary.GetRefundSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetRefunds.GetRefundsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetTenderSummary.GetTenderSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.PaymentService;
import com.trubeacon.cloverandroidapi.payment.service.PaymentServiceImpl;
import com.trubeacon.cloverandroidapi.payment.service.UpdatePayment.UpdatePaymentCallback;

import java.util.List;

public class CloverService implements AppService, AccountService, AuthenticationService, CustomerService, EmployeeService, InventoryService, DiscountService, MerchantService, NotificationService, OrderService, PaymentService {

	private static CloverService cloverService;
	private static boolean production = true;

	public static class Builder {
		
		private UrlResolver urlResolver = new CloverUrlResolver(Region.US);
		private RESTServiceResolver restServiceResolver = null;

		public Builder withRESTServiceResolver(RESTServiceResolver restServiceResolver) {
			this.restServiceResolver = restServiceResolver;
			return this;
		}
		
		public Builder withRegion(Region region) {
			this.urlResolver.setRegion(region);
			return this;
		}
				
		public CloverService build() {
			
			if (urlResolver == null) throw new IllegalArgumentException("URL resolver must be provided");
			if (restServiceResolver == null) throw new IllegalArgumentException("A rest service resolver must be specified");
			
			CloverService.cloverService = new CloverService();
			UrlResolverHolder.withUrlResolver(this.urlResolver);
			RESTServiceResolverHolder.withRESTServiceResolver(this.restServiceResolver);
			return CloverService.cloverService;
		}
		
	}
	
	// don't let them construct a service manually
	private CloverService() {}
	
	// services are relatively fast to construct, 
	// and using a unique service for each request ensures 
	// we don't have collisions with callbacks
	
	private static AppService appService() {
		return new AppServiceImpl();
	}
	
	private static AccountService accountService() {
		return new AccountServiceImpl();
	}
	
	private static AuthenticationService authenticationService() {
		return new AuthenticationServiceImpl();
	}
	
	private static CustomerService customerService() {
		return new CustomerServiceImpl();
	}
	
	private static EmployeeService employeeService() {
		return new EmployeeServiceImpl();
	}
	
	private static InventoryService inventoryService() {
		return new InventoryServiceImpl();
	}
	
	private static MerchantService merchantService() {
		return new MerchantServiceImpl();
	}
	
	private static DiscountService discountService() {
		return new DiscountServiceImpl();
	}
	
	private static NotificationService notificationService() {
		return new NotificationServiceImpl();
	}
	
	private static OrderService orderService() {
		return new OrderServiceImpl();
	}
	
	private static PaymentService paymentService() {
		return new PaymentServiceImpl();
	}
	
	public static boolean isProduction() {
		return CloverService.production;
	}
	public static void setProduction(boolean production) {
		CloverService.production = production;
	}
	
	public static String getBaseUrl() {
		return UrlResolverHolder.getBaseUrl();
	}
	
	public static String getBaseUrl(Version version) {
		return UrlResolverHolder.getBaseUrl(version);
	}
	
	public static String getOAuthBaseUrl() {
		return UrlResolverHolder.getOAuthBaseUrl();
	}
	
	@Override
	public AccessToken getAccessToken(String clientId, String secret, String authCode) throws RESTException {
		return authenticationService().getAccessToken(clientId, secret, authCode);
	}

	@Override
    public List<Customer> getCustomers(String mId, String token, Object... params) throws RESTException {
	    return customerService().getCustomers(mId, token, params);
    }

	@Override
    public Customer createCustomer(String mId, String token, Customer customer) throws RESTException {
	    return customerService().createCustomer(mId, token, customer);
    }

	@Override
    public Customer getCustomer(String mId, String token, String customerId) throws RESTException {
	    return customerService().getCustomer(mId, token, customerId);
    }

	@Override
    public Customer updateCustomer(String mId, String token, String customerId, Customer customer) throws RESTException {
	    return customerService().updateCustomer(mId, token, customerId, customer);
    }

	@Override
    public Customer deleteCustomer(String mId, String token, String customerId) throws RESTException {
	    return customerService().deleteCustomer(mId, token, customerId);
    }

	@Override
    public PhoneNumber createCustomerPhoneNumber(String mId, String token, String customerId, PhoneNumber phoneNumber) throws RESTException {
	    return customerService().createCustomerPhoneNumber(mId, token, customerId, phoneNumber);
    }

	@Override
    public PhoneNumber updateCustomerPhoneNumber(String mId, String token, String customerId, String phoneId, PhoneNumber update) throws RESTException {
	    return customerService().updateCustomerPhoneNumber(mId, token, customerId, phoneId, update);
    }

	@Override
    public PhoneNumber deleteCustomerPhoneNumber(String mId, String token, String customerId, String phoneId) throws RESTException {
	    return customerService().deleteCustomerPhoneNumber(mId, token, customerId, phoneId);
    }

	@Override
    public EmailAddress createCustomerEmailAddress(String mId, String token, String customerId, EmailAddress emailAddress) throws RESTException {
	    return customerService().createCustomerEmailAddress(mId, token, customerId, emailAddress);
    }

	@Override
    public EmailAddress updateCustomerEmailAddress(String mId, String token, String customerId, String emailId, EmailAddress update) throws RESTException {
	    return customerService().updateCustomerEmailAddress(mId, token, customerId, emailId, update);
    }

	@Override
    public EmailAddress deleteCustomerEmailAddress(String mId, String token, String customerId, String emailId) throws RESTException {
	    return customerService().deleteCustomerEmailAddress(mId, token, customerId, emailId);
    }

	@Override
    public Address createCustomerAddress(String mId, String token, String customerId, Address address) throws RESTException {
	    return customerService().createCustomerAddress(mId, token, customerId, address);
    }

	@Override
    public Address updateCustomerAddress(String mId, String token, String customerId, String addressId, Address address) throws RESTException {
	    return customerService().updateCustomerAddress(mId, token, customerId, addressId, address);
    }

	@Override
    public Address deleteCustomerAddress(String mId, String token, String customerId, String addressId) throws RESTException {
	    return customerService().deleteCustomerAddress(mId, token, customerId, addressId);
    }

	@Override
    public List<Employee> getEmployees(String mId, String token, Object... params) throws RESTException {
	    return employeeService().getEmployees(mId, token, params);
    }

	@Override
    public Employee createEmployee(String mId, String token, Employee employee) throws RESTException {
	    return employeeService().createEmployee(mId, token, employee);
    }

	@Override
    public Employee getEmployee(String mId, String token, String empId) throws RESTException {
	    return employeeService().getEmployee(mId, token, empId);
    }

	@Override
    public Employee updateEmployee(String mId, String token, String empId, Employee update) throws RESTException {
	    return employeeService().updateEmployee(mId, token, empId, update);
    }

	@Override
    public Employee deleteEmployee(String mId, String token, String empId) throws RESTException {
	    return employeeService().deleteEmployee(mId, token, empId);
    }

	@Override
    public List<Shift> getShifts(String mId, String token, Object... params) throws RESTException {
	    return employeeService().getShifts(mId, token, params);
    }

	@Override
    public Shift getShift(String mId, String token, String shiftId) throws RESTException {
	    return employeeService().getShift(mId, token, shiftId);
    }

	@Override
    public List<Shift> getEmployeeShifts(String mId, String token, String empId, Object... params) throws RESTException {
	    return employeeService().getEmployeeShifts(mId, token, empId, params);
    }

	@Override
    public Shift createEmployeeShift(String mId, String token, String empId, Shift shift) throws RESTException {
	    return employeeService().createEmployeeShift(mId, token, empId, shift);
    }

	@Override
    public Shift getEmployeeShift(String mId, String token, String empId, String shiftId) throws RESTException {
	    return employeeService().getEmployeeShift(mId, token, empId, shiftId);
    }

	@Override
    public Shift updateEmployeeShift(String mId, String token, String empId, String shiftId, Shift update) throws RESTException {
	    return employeeService().updateEmployeeShift(mId, token, empId, shiftId, update);
    }

	@Override
    public List<Order> getEmployeeOrders(String mId, String token, String empId, Object... params) throws RESTException {
	    return employeeService().getEmployeeOrders(mId, token, empId, params);
    }

	@Override
	public List<Item> getItems(String mId, String token, Object... params) throws RESTException {
	    return inventoryService().getItems(mId, token, params);
    }

	@Override
	public Item createItem(String mId, String token, Item item)
            throws RESTException {
	    return inventoryService().createItem(mId, token, item);
    }

	@Override
	public Item getItem(String mId, String token, String itemId)
            throws RESTException {
	    return inventoryService().getItem(mId, token, itemId);
    }

	@Override
	public Item updateItem(String mId, String token, String itemId, Item item)
            throws RESTException {
	    return inventoryService().updateItem(mId, token, itemId, item);
    }

	@Override
	public Item deleteItem(String mId, String token, String itemId)
            throws RESTException {
	    return inventoryService().deleteItem(mId, token, itemId);
    }

	@Override
	public List<ItemStock> getItemStocks(String mId, String token, Object... params)
            throws RESTException {
	    return inventoryService().getItemStocks(mId, token, params);
    }

	@Override
	public ItemStock getItemStock(String mId, String token, String itemId)
            throws RESTException {
	    return inventoryService().getItemStock(mId, token, itemId);
    }

	@Override
	public ItemStock updateItemStock(String mId, String token, String itemId,
            ItemStock itemStock) throws RESTException {
	    return inventoryService().updateItemStock(mId, token, itemId, itemStock);
    }

	@Override
	public ItemStock deleteItemStock(String mId, String token, String itemId)
            throws RESTException {
	    return inventoryService().deleteItemStock(mId, token, itemId);
    }

	@Override
	public List<ItemGroup> getItemGroups(String mId, String token, Object... params)
            throws RESTException {
	    return inventoryService().getItemGroups(mId, token, params);
    }

	@Override
	public ItemGroup createItemGroup(String mId, String token,
            ItemGroup itemGroup) throws RESTException {
	    return inventoryService().createItemGroup(mId, token, itemGroup);
    }

	@Override
	public ItemGroup getItemGroup(String mId, String token, String itemGroupId)
            throws RESTException {
	    return inventoryService().getItemGroup(mId, token, itemGroupId);
    }

	@Override
	public ItemGroup updateItemGroup(String mId, String token,
            String itemGroupId, ItemGroup itemGroup) throws RESTException {
	    return inventoryService().updateItemGroup(mId, token, itemGroupId,
	            itemGroup);
    }

	@Override
	public ItemGroup deleteItemGroup(String mId, String token,
            String itemGroupId) throws RESTException {
	    return inventoryService().deleteItemGroup(mId, token, itemGroupId);
    }

	@Override
	public List<Tag> getTags(String mId, String token, Object... params) throws RESTException {
	    return inventoryService().getTags(mId, token, params);
    }

	@Override
	public Tag createTag(String mId, String token, Tag tag)
            throws RESTException {
	    return inventoryService().createTag(mId, token, tag);
    }

	@Override
	public Tag getTag(String mId, String token, String tagId)
            throws RESTException {
	    return inventoryService().getTag(mId, token, tagId);
    }

	@Override
	public Tag updateTag(String mId, String token, String tagId, Tag tag)
            throws RESTException {
	    return inventoryService().updateTag(mId, token, tagId, tag);
    }

	@Override
	public Tag deleteTag(String mId, String token, String tagId)
            throws RESTException {
	    return inventoryService().deleteTag(mId, token, tagId);
    }

	@Override
	public List<Tag> getItemTags(String mId, String token, String itemId, Object... params)
            throws RESTException {
	    return inventoryService().getItemTags(mId, token, itemId, params);
    }

	@Override
	public List<Item> getTagItems(String mId, String token, String tagId, Object... params)
            throws RESTException {
	    return inventoryService().getTagItems(mId, token, tagId, params);
    }

	@Override
	public List<TagItemAssociation> createTagItemAssociations(String mId, String token,
            TagItemAssociation... associations) throws RESTException {
	    return inventoryService().createTagItemAssociations(mId, token,
	            associations);
    }
	
	@Override
	public List<TagItemAssociation> deleteTagItemAssociations(String mId, String token,
            TagItemAssociation... associations) throws RESTException {
	    return inventoryService().deleteTagItemAssociations(mId, token,
	            associations);
    }

	@Override
	public List<TaxRate> getTaxRates(String mId, String token, Object... params)
            throws RESTException {
	    return merchantService().getTaxRates(mId, token, params);
    }

	@Override
	public TaxRate createTaxRate(String mId, String token, TaxRate taxRate)
            throws RESTException {
	    return merchantService().createTaxRate(mId, token, taxRate);
    }

	@Override
	public TaxRate getTaxRate(String mId, String token, String taxId)
            throws RESTException {
	    return merchantService().getTaxRate(mId, token, taxId);
    }

	@Override
	public TaxRate updateTaxRate(String mId, String token, String taxId,
            TaxRate update) throws RESTException {
	    return merchantService().updateTaxRate(mId, token, taxId, update);
    }

	@Override
	public TaxRate deleteTaxRate(String mId, String token, String taxId)
            throws RESTException {
	    return merchantService().deleteTaxRate(mId, token, taxId);
    }

	@Override
	public List<Category> getCategories(String mId, String token, Object... params)
            throws RESTException {
	    return inventoryService().getCategories(mId, token, params);
    }

	@Override
	public Category createCategory(String mId, String token, Category category)
            throws RESTException {
	    return inventoryService().createCategory(mId, token, category);
    }

	@Override
	public Category getCategory(String mId, String token, String catId)
            throws RESTException {
	    return inventoryService().getCategory(mId, token, catId);
    }

	@Override
	public Category updateCategory(String mId, String token, String catId,
            Category update) throws RESTException {
	    return inventoryService().updateCategory(mId, token, catId, update);
    }

	@Override
	public Category deleteCategory(String mId, String token, String catId)
            throws RESTException {
	    return inventoryService().deleteCategory(mId, token, catId);
    }

	@Override
	public List<Item> getCategoryItems(String mId, String token, String catId, Object... params)
            throws RESTException {
	    return inventoryService().getCategoryItems(mId, token, catId, params);
    }

	@Override
	public List<Category> getItemCategories(String mId, String token,
            String itemId, Object... params) throws RESTException {
	    return inventoryService().getItemCategories(mId, token, itemId, params);
    }

	@Override
	public List<CategoryItemAssociation> createCategoryItemAssociations(String mId, String token,
            CategoryItemAssociation... associations) throws RESTException {
	    return inventoryService().createCategoryItemAssociations(mId, token,
	            associations);
    }
	
	@Override
	public List<CategoryItemAssociation> deleteCategoryItemAssociations(String mId, String token,
            CategoryItemAssociation... associations) throws RESTException {
	    return inventoryService().deleteCategoryItemAssociations(mId, token,
	            associations);
    }

	@Override
	public List<TaxRateItemAssociation> createTaxRateItemAssociations(String mId, String token,
            TaxRateItemAssociation... associations) throws RESTException {
	    return inventoryService().createTaxRateItemAssociations(mId, token,
	            associations);
    }
	
	@Override
	public List<TaxRateItemAssociation> deleteTaxRateItemAssociations(String mId, String token,
            TaxRateItemAssociation... associations) throws RESTException {
	    return inventoryService().deleteTaxRateItemAssociations(mId, token,
	            associations);
    }

	@Override
	public List<Item> getTaxRateItems(String mId, String token, String taxId, Object... params)
            throws RESTException {
	    return inventoryService().getTaxRateItems(mId, token, taxId, params);
    }

	@Override
	public List<ModifierGroup> getModifierGroups(String mId, String token, Object... params)
            throws RESTException {
	    return inventoryService().getModifierGroups(mId, token, params);
    }

	@Override
	public ModifierGroup getModifierGroup(String mId, String token,
            String modGroupId) throws RESTException {
	    return inventoryService().getModifierGroup(mId, token, modGroupId);
    }

	@Override
	public ModifierGroup createModifierGroup(String mId, String token,
            ModifierGroup modifierGroup) throws RESTException {
	    return inventoryService().createModifierGroup(mId, token, modifierGroup);
    }

	@Override
	public ModifierGroup updateModifierGroup(String mId, String token,
            String modGroupId, ModifierGroup update) throws RESTException {
	    return inventoryService().updateModifierGroup(mId, token, modGroupId,
	            update);
    }

	@Override
	public ModifierGroup deleteModifierGroup(String mId, String token,
            String modGroupId) throws RESTException {
	    return inventoryService().deleteModifierGroup(mId, token, modGroupId);
    }

	@Override
	public List<ModifierGroupItemAssociation> createModifierGroupItemAssociations(String mId, String token,
            ModifierGroupItemAssociation... associations) throws RESTException {
	    return inventoryService().createModifierGroupItemAssociations(mId, token,
	            associations);
    }
	
	@Override
	public List<ModifierGroupItemAssociation> deleteModifierGroupItemAssociations(String mId, String token,
            ModifierGroupItemAssociation... associations) throws RESTException {
	    return inventoryService().deleteModifierGroupItemAssociations(mId, token,
	            associations);
    }

	public List<Modifier> getModifiers(String mId, String token)
            throws RESTException {
	    return inventoryService().getModifiers(mId, token);
    }

	@Override
	public List<Modifier> getModifierGroupModifiers(String mId, String token,
            String modGroupId, Object... params) throws RESTException {
	    return inventoryService().getModifierGroupModifiers(mId, token,
	            modGroupId, params);
    }

	@Override
	public Modifier createModifierGroupModifier(String mId, String token,
            String modGroupId, Modifier modifier) throws RESTException {
	    return inventoryService().createModifierGroupModifier(mId, token,
	            modGroupId, modifier);
    }

	@Override
	public Modifier getModifierGroupModifier(String mId, String token,
            String modGroupId, String modId) throws RESTException {
	    return inventoryService().getModifierGroupModifier(mId, token,
	            modGroupId, modId);
    }

	@Override
	public Modifier updateModifierGroupModifier(String mId, String token,
            String modGroupId, String modId, Modifier update)
            throws RESTException {
	    return inventoryService().updateModifierGroupModifier(mId, token,
	            modGroupId, modId, update);
    }

	public Modifier deleteModifierGroupModifier(String mId, String token,
            String modGroupId, String modId) throws RESTException {
	    return inventoryService().deleteModifierGroupModifier(mId, token,
	            modGroupId, modId);
    }

	@Override
	public List<Attribute> getAttributes(String mId, String token, Object... params)
            throws RESTException {
	    return inventoryService().getAttributes(mId, token, params);
    }

	@Override
	public Attribute createAttribute(String mId, String token,
            Attribute attribute) throws RESTException {
	    return inventoryService().createAttribute(mId, token, attribute);
    }

	@Override
	public Attribute getAttribute(String mId, String token, String attributeId)
            throws RESTException {
	    return inventoryService().getAttribute(mId, token, attributeId);
    }

	@Override
	public Attribute updateAttribute(String mId, String token,
            String attributeId, Attribute attribute) throws RESTException {
	    return inventoryService().updateAttribute(mId, token, attributeId,
	            attribute);
    }

	@Override
	public Attribute deleteAttribute(String mId, String token,
            String attributeId) throws RESTException {
	    return inventoryService().deleteAttribute(mId, token, attributeId);
    }

	@Override
	public List<Option> getOptions(String mId, String token, Object... params)
            throws RESTException {
	    return inventoryService().getOptions(mId, token, params);
    }

	@Override
	public List<Option> getAttributeOptions(String mId, String token,
            String attributeId, Object... params) throws RESTException {
	    return inventoryService().getAttributeOptions(mId, token, attributeId, params);
    }

	@Override
	public Option createAttributeOption(String mId, String token,
            String attributeId, Option option) throws RESTException {
	    return inventoryService().createAttributeOption(mId, token, attributeId,
	            option);
    }

	@Override
	public Option getAttributeOption(String mId, String token,
            String attributeId, String optionId) throws RESTException {
	    return inventoryService().getAttributeOption(mId, token, attributeId,
	            optionId);
    }

	@Override
	public Option updateAttributeOption(String mId, String token,
            String attributeId, String optionId, Option update)
            throws RESTException {
	    return inventoryService().updateAttributeOption(mId, token, attributeId,
	            optionId, update);
    }
	
	@Override
	public Option deleteAttributeOption(String mId, String token,
            String attributeId, String optionId) throws RESTException {
	    return inventoryService().deleteAttributeOption(mId, token, attributeId,
	            optionId);
    }

	@Override
	public List<OptionItemAssociation> createOptionItemAssociations(String mId, String token,
            OptionItemAssociation... associations) throws RESTException {
	    return inventoryService().createOptionItemAssociations(mId, token,
	            associations);
    }
	
	@Override
	public List<OptionItemAssociation> deleteOptionItemAssociations(String mId, String token,
            OptionItemAssociation... associations) throws RESTException {
	    return inventoryService().deleteOptionItemAssociations(mId, token,
	            associations);
    }
	
	@Override
	public Merchant getMerchant(String mid, String token) throws RESTException {
	    return merchantService().getMerchant(mid, token);
    }

	@Override
	public Merchant updateMerchant(String mid, String token, Merchant update)
            throws RESTException {
	    return merchantService().updateMerchant(mid, token, update);
    }

	@Override
	public Address getMerchantAddress(String mid, String token)
            throws RESTException {
	    return merchantService().getMerchantAddress(mid, token);
    }

	@Override
	public Properties getMerchantProperties(String mid, String token)
            throws RESTException {
	    return merchantService().getMerchantProperties(mid, token);
    }

	@Override
	public Properties updateMerchantProperties(String mid, String token,
            Properties update) throws RESTException {
	    return merchantService().updateMerchantProperties(mid, token, update);
    }

	@Override
	public List<TipSuggestion> getTipSuggestions(String mid, String token, Object... params)
            throws RESTException {
	    return merchantService().getTipSuggestions(mid, token, params);
    }

	@Override
	public TipSuggestion getTipSuggestion(String mid, String token, String tid)
            throws RESTException {
	    return merchantService().getTipSuggestion(mid, token, tid);
    }

	@Override
	public TipSuggestion updateTipSuggestion(String mid, String token,
            String id, TipSuggestion update) throws RESTException {
	    return merchantService().updateTipSuggestion(mid, token, id, update);
    }

	@Override
	public List<OrderType> getOrderTypes(String mid, String token, Object... params)
            throws RESTException {
	    return merchantService().getOrderTypes(mid, token, params);
    }

	@Override
	public OrderType createOrderType(String mid, String token,
            OrderType orderType) throws RESTException {
	    return merchantService().createOrderType(mid, token, orderType);
    }

	@Override
	public OrderType getOrderType(String mid, String token, String orderTypeId)
            throws RESTException {
	    return merchantService().getOrderType(mid, token, orderTypeId);
    }

	@Override
	public OrderType updateOrderType(String mid, String token,
            String orderTypeId, OrderType update) throws RESTException {
	    return merchantService().updateOrderType(mid, token, orderTypeId, update);
    }

	@Override
	public OrderType deleteOrderType(String mid, String token,
            String orderTypeId) throws RESTException {
	    return merchantService().deleteOrderType(mid, token, orderTypeId);
    }

	@Override
	public List<Role> getRoles(String mid, String token, Object... params) throws RESTException {
	    return merchantService().getRoles(mid, token, params);
    }

	@Override
	public Role createRole(String mid, String token, Role role)
            throws RESTException {
	    return merchantService().createRole(mid, token, role);
    }
	
	@Override
	public Role getRole(String mid, String token, String rId)
            throws RESTException {
	    return merchantService().getRole(mid, token, rId);
    }

	@Override
	public Role updateRole(String mid, String token, String rId, Role role)
            throws RESTException {
	    return merchantService().updateRole(mid, token, rId, role);
    }

	@Override
	public Role deleteRole(String mid, String token, String rId)
            throws RESTException {
	    return merchantService().deleteRole(mid, token, rId);
    }

	@Override
	public List<Tender> getTenders(String mid, String token, Object... params)
            throws RESTException {
	    return merchantService().getTenders(mid, token, params);
    }

	@Override
	public Tender getTender(String mid, String token, String tenderId)
            throws RESTException {
	    return merchantService().getTender(mid, token, tenderId);
    }

	@Override
	public List<HoursSet> getOpeningHours(String mid, String token, Object... params)
            throws RESTException {
	    return merchantService().getOpeningHours(mid, token, params);
    }

	@Override
	public HoursSet createOpeningHour(String mid, String token,
            HoursSet openingHours) throws RESTException {
	    return merchantService().createOpeningHour(mid, token, openingHours);
    }

	@Override
	public HoursSet getOpeningHour(String mid, String token, String hId)
            throws RESTException {
	    return merchantService().getOpeningHour(mid, token, hId);
    }

	@Override
	public HoursSet updateOpeningHour(String mid, String token, String hId,
            HoursSet hoursSet) throws RESTException {
	    return merchantService().updateOpeningHour(mid, token, hId, hoursSet);
    }

	@Override
	public HoursSet deleteOpeningHour(String mid, String token, String hId)
            throws RESTException {
	    return merchantService().deleteOpeningHour(mid, token, hId);
    }

	@Override
    public AppNotification createAppNotification(String appId, String mId,
            String token, AppNotification notification) throws RESTException {
	    return notificationService().createAppNotification(appId, mId, token, notification);
    }

	@Override
    public AppNotification createDeviceNotification(String appId,
            String deviceId, String token, AppNotification notification)
            throws RESTException {
	    return notificationService().createDeviceNotification(appId, deviceId, token, notification);
    }
	
	@Override
	public List<Order> getOrders(String mId, String token, Object... params) throws RESTException {
	    return orderService().getOrders(mId, token, params);
    }

	@Override
	public Order createOrder(String mId, String token, Order order)
            throws RESTException {
	    return orderService().createOrder(mId, token, order);
    }

	@Override
	public Order getOrder(String mId, String token, String orderId)
            throws RESTException {
	    return orderService().getOrder(mId, token, orderId);
    }

	@Override
	public Order updateOrder(String mId, String token, String orderId,
            Order update) throws RESTException {
	    return orderService().updateOrder(mId, token, orderId, update);
    }

	@Override
	public Order deleteOrder(String mId, String token, String orderId)
            throws RESTException {
	    return orderService().deleteOrder(mId, token, orderId);
    }

	@Override
	public List<Discount> getOrderDiscounts(String mId, String token,
            String orderId, Object... params) throws RESTException {
	    return orderService().getOrderDiscounts(mId, token, orderId, params);
    }

	@Override
	public Discount createOrderDiscount(String mId, String token,
            String orderId, Discount discount) throws RESTException {
	    return orderService().createOrderDiscount(mId, token, orderId, discount);
    }

	@Override
	public Discount deleteOrderDiscount(String mId, String token,
            String orderId, String discountId) throws RESTException {
	    return orderService().deleteOrderDiscount(mId, token, orderId, discountId);
    }

	@Override
	public List<LineItem> getOrderLineItems(String mId, String token,
            String orderId, Object... params) throws RESTException {
	    return orderService().getOrderLineItems(mId, token, orderId, params);
    }

	@Override
	public LineItem createOrderLineItem(String mId, String token,
            String orderId, LineItem lineItem) throws RESTException {
	    return orderService().createOrderLineItem(mId, token, orderId, lineItem);
    }

	@Override
	public LineItem getOrderLineItem(String mId, String token, String orderId,
            String lineItemId) throws RESTException {
	    return orderService().getOrderLineItem(mId, token, orderId, lineItemId);
    }

	@Override
	public LineItem updateOrderLineItem(String mId, String token,
            String orderId, String lineItemId, LineItem update)
            throws RESTException {
	    return orderService().updateOrderLineItem(mId, token, orderId,
	            lineItemId, update);
    }

	@Override
	public LineItem deleteOrderLineItem(String mId, String token,
            String orderId, String lineItemId) throws RESTException {
	    return orderService().deleteOrderLineItem(mId, token, orderId, lineItemId);
    }

	@Override
	public Discount createOrderLineItemDiscount(String mid, String token,
            String orderId, String lineItemId, Discount discount)
            throws RESTException {
	    return orderService().createOrderLineItemDiscount(mid, token, orderId,
	            lineItemId, discount);
    }

	@Override
	public Discount deleteOrderLineItemDiscount(String mId, String token,
            String orderId, String lineItemId, String discountId)
            throws RESTException {
	    return orderService().deleteOrderLineItemDiscount(mId, token, orderId,
	            lineItemId, discountId);
    }

	@Override
	public Modification createOrderLineItemModification(String mId,
            String token, String orderId, String lineItemId,
            Modification modification) throws RESTException {
	    return orderService().createOrderLineItemModification(mId, token,
	            orderId, lineItemId, modification);
    }

	@Override
	public Modification deleteOrderLineItemModification(String mId,
            String token, String orderId, String lineItemId,
            String modificationId) throws RESTException {
	    return orderService().deleteOrderLineItemModification(mId, token,
	            orderId, lineItemId, modificationId);
    }

	@Override
	public Payment createOrderPayment(String mId, String token, String orderId,
            Payment payment) throws RESTException {
	    return orderService().createOrderPayment(mId, token, orderId, payment);
    }
	
	@Override
	public ServiceCharge createOrderServiceCharge(String mId, String token,
            String orderId, ServiceCharge serviceCharge) throws RESTException {
	    return orderService().createOrderServiceCharge(mId, token, orderId,
	            serviceCharge);
    }

	@Override
	public ServiceCharge deleteOrderServiceCharge(String mId, String token,
            String orderId, String serviceChargeId) throws RESTException {
	    return orderService().deleteOrderServiceCharge(mId, token, orderId,
	            serviceChargeId);
    }

	@Override
	public LineItem exchangeOrderLineItem(String mId, String token,
            String orderId, String oldLineItemId, String lineItemId)
            throws RESTException {
	    return orderService().exchangeOrderLineItem(mId, token, orderId,
	            oldLineItemId, lineItemId);
    }
	
	@Override
	public List<Payment> getPayments(String mId, String token, Object... params)
            throws RESTException {
	    return paymentService().getPayments(mId, token, params);
    }

	@Override
	public Payment createPayment(String mId, String token, Payment payment)
            throws RESTException {
	    return paymentService().createPayment(mId, token, payment);
    }

	@Override
	public Payment getPayment(String mId, String token, String paymentId)
            throws RESTException {
	    return paymentService().getPayment(mId, token, paymentId);
    }

	@Override
	public Payment updatePayment(String mId, String token, String paymentId,
            Payment update) throws RESTException {
	    return paymentService().updatePayment(mId, token, paymentId, update);
    }

	@Override
	public List<Payment> getOrderPayments(String mId, String token,
            String orderId, Object... params) throws RESTException {
	    return paymentService().getOrderPayments(mId, token, orderId, params);
    }

	@Override
	public List<Payment> getEmployeePayments(String mId, String token,
            String empId, Object... params) throws RESTException {
	    return paymentService().getEmployeePayments(mId, token, empId, params);
    }

	@Override
	public List<Refund> getRefunds(String mId, String token, Object... params)
            throws RESTException {
	    return paymentService().getRefunds(mId, token, params);
    }

	@Override
	public Refund getRefund(String mId, String token, String refundId)
            throws RESTException {
	    return paymentService().getRefund(mId, token, refundId);
    }

	@Override
	public List<Credit> getCredits(String mId, String token, Object... params)
            throws RESTException {
	    return paymentService().getCredits(mId, token, params);
    }

	@Override
	public Credit getCredit(String mId, String token, String cId)
            throws RESTException {
	    return paymentService().getCredit(mId, token, cId);
    }

	@Override
    public List<Modifier> getModifiers(String mId, String token, Object... params) throws RESTException {
	    return inventoryService().getModifiers(mId, token, params);
    }

	@Override
    public List<LineItem> getLineItems(String mId, String token,
            Object... params) throws RESTException {
	    return orderService().getLineItems(mId, token, params);
    }

	@Override
    public LineItem getLineItem(String mId, String token, String lineItemId)
            throws RESTException {
	    return orderService().getLineItem(mId, token, lineItemId);
    }

	@Override
    public PaymentSummary getPaymentSummary(String mId, String token,
            Object... params) throws RESTException {
	    return paymentService().getPaymentSummary(mId, token, params);
    }

	@Override
    public CreditSummary getCreditSummary(String mId, String token,
            Object... params) throws RESTException {
	    return paymentService().getCreditSummary(mId, token, params);
    }

	@Override
    public RefundSummary getRefundSummary(String mId, String token,
            Object... params) throws RESTException {
	    return paymentService().getRefundSummary(mId, token, params);
    }

	@Override
    public WrappedList<TenderSummary> getTenderSummary(String mId, String token,
            Object... params) throws RESTException {
	    return paymentService().getTenderSummary(mId, token, params);
    }

	@Override
    public WrappedList<CardSummary> getCardSummary(String mId, String token,
            Object... params) throws RESTException {
	    return paymentService().getCardSummary(mId, token, params);
    }

	@Override
    public DiscountSummary getDiscountSummary(String mId, String token,
            Object... params) throws RESTException {
	    return orderService().getDiscountSummary(mId, token, params);
    }

	@Override
    public TaxRateSummary getTaxRateSummary(String mId, String token,
            Object... params) throws RESTException {
	    return merchantService().getTaxRateSummary(mId, token, params);
    }

	@Override
    public ItemSummary getItemSummary(String mId, String token,
            Object... params) throws RESTException {
	    return inventoryService().getItemSummary(mId, token, params);
    }

	@Override
    public WrappedList<EmployeeSummary> getEmployeeSummary(String mId,
            String token, Object... params) throws RESTException {
	    return employeeService().getEmployeeSummary(mId, token, params);
    }
	
	@Override
	public void getAccessToken(String clientId, String secret, String authCode,
            GetAccessTokenCallback callback) {
	    authenticationService().getAccessToken(clientId, secret, authCode,
	            callback);
    }
	
	@Override
	public void getCustomers(String mId, String token,
            GetCustomersCallback callback, Object... params) {
	    customerService().getCustomers(mId, token, callback, params);
    }
	
	@Override
	public void createCustomer(String mId, String token, Customer customer,
            CreateCustomerCallback callback) {
	    customerService().createCustomer(mId, token, customer, callback);
    }

	@Override
	public void getCustomer(String mId, String token, String customerId,
            GetCustomerCallback callback) {
	    customerService().getCustomer(mId, token, customerId, callback);
    }

	@Override
	public void updateCustomer(String mId, String token, String customerId,
            Customer customer, UpdateCustomerCallback callback) {
	    customerService().updateCustomer(mId, token, customerId, customer,
	            callback);
    }

	@Override
	public void deleteCustomer(String mId, String token, String customerId,
            DeleteCustomerCallback callback) {
	    customerService().deleteCustomer(mId, token, customerId, callback);
    }

	@Override
	public void createCustomerPhoneNumber(String mId, String token,
            String customerId, PhoneNumber phoneNumber,
            CreateCustomerPhoneNumberCallback callback) {
	    customerService().createCustomerPhoneNumber(mId, token, customerId,
	            phoneNumber, callback);
    }

	@Override
	public void updateCustomerPhoneNumber(String mId, String token,
            String customerId, String phoneId, PhoneNumber update,
            UpdateCustomerPhoneNumberCallback callback) {
	    customerService().updateCustomerPhoneNumber(mId, token, customerId,
	            phoneId, update, callback);
    }

	@Override
	public void deleteCustomerPhoneNumber(String mId, String token,
            String customerId, String phoneId,
            DeleteCustomerPhoneNumberCallback callback) {
	    customerService().deleteCustomerPhoneNumber(mId, token, customerId,
	            phoneId, callback);
    }

	@Override
	public void createCustomerEmailAddress(String mId, String token,
            String customerId, EmailAddress emailAddress,
            CreateCustomerEmailAddressCallback callback) {
	    customerService().createCustomerEmailAddress(mId, token, customerId,
	            emailAddress, callback);
    }

	@Override
	public void updateCustomerEmailAddress(String mId, String token,
            String customerId, String emailId, EmailAddress update,
            UpdateCustomerEmailAddressCallback callback) {
	    customerService().updateCustomerEmailAddress(mId, token, customerId,
	            emailId, update, callback);
    }

	@Override
	public void deleteCustomerEmailAddress(String mId, String token,
            String customerId, String emailId,
            DeleteCustomerEmailAddressCallback callback) {
	    customerService().deleteCustomerEmailAddress(mId, token, customerId,
	            emailId, callback);
    }

	@Override
	public void createCustomerAddress(String mId, String token,
            String customerId, Address address,
            CreateCustomerAddressCallback callback) {
	    customerService().createCustomerAddress(mId, token, customerId, address,
	            callback);
    }

	@Override
	public void updateCustomerAddress(String mId, String token,
            String customerId, String addressId, Address address,
            UpdateCustomerAddressCallback callback) {
	    customerService().updateCustomerAddress(mId, token, customerId,
	            addressId, address, callback);
    }

	@Override
	public void deleteCustomerAddress(String mId, String token,
            String customerId, String addressId,
            DeleteCustomerAddressCallback callback) {
	    customerService().deleteCustomerAddress(mId, token, customerId,
	            addressId, callback);
    }

	@Override
	public void getEmployees(String mId, String token,
            GetEmployeesCallback callback, Object... params) {
	    employeeService().getEmployees(mId, token, callback, params);
    }

	@Override
	public void createEmployee(String mId, String token, Employee employee,
            CreateEmployeeCallback callback) {
	    employeeService().createEmployee(mId, token, employee, callback);
    }

	@Override
	public void getEmployee(String mId, String token, String empId,
            GetEmployeeCallback callback) {
	    employeeService().getEmployee(mId, token, empId, callback);
    }

	@Override
	public void updateEmployee(String mId, String token, String empId,
            Employee update, UpdateEmployeeCallback callback) {
	    employeeService().updateEmployee(mId, token, empId, update, callback);
    }
	
	@Override
	public void deleteEmployee(String mId, String token, String empId,
            DeleteEmployeeCallback callback) {
	    employeeService().deleteEmployee(mId, token, empId, callback);
    }

	@Override
	public void getShifts(String mId, String token, GetShiftsCallback callback,
            Object... params) {
	    employeeService().getShifts(mId, token, callback, params);
    }

	@Override
	public void getShift(String mId, String token, String shiftId,
            GetShiftCallback callback) {
	    employeeService().getShift(mId, token, shiftId, callback);
    }

	@Override
	public void getEmployeeShifts(String mId, String token, String empId,
            GetEmployeeShiftsCallback callback, Object... params) {
	    employeeService().getEmployeeShifts(mId, token, empId, callback, params);
    }

	@Override
	public void createEmployeeShift(String mId, String token, String empId,
            Shift shift, CreateEmployeeShiftCallback callback) {
	    employeeService().createEmployeeShift(mId, token, empId, shift, callback);
    }

	@Override
	public void getEmployeeShift(String mId, String token, String empId,
            String shiftId, GetEmployeeShiftCallback callback) {
	    employeeService().getEmployeeShift(mId, token, empId, shiftId, callback);
    }

	@Override
	public void updateEmployeeShift(String mId, String token, String empId,
            String shiftId, Shift update, UpdateEmployeeShiftCallback callback) {
	    employeeService().updateEmployeeShift(mId, token, empId, shiftId, update,
	            callback);
    }

	@Override
	public void getEmployeeOrders(String mId, String token, String empId,
            GetEmployeeOrdersCallback callback, Object... params) {
	    employeeService().getEmployeeOrders(mId, token, empId, callback, params);
    }

	@Override
	public void getEmployeeSummary(String mId, String token,
            GetEmployeeSummaryCallback callback, Object... params) {
	    employeeService().getEmployeeSummary(mId, token, callback, params);
    }

	@Override
	public void getItems(String mId, String token, GetItemsCallback callback,
            Object... params) {
	    inventoryService().getItems(mId, token, callback, params);
    }

	@Override
	public void createItem(String mId, String token, Item item,
            CreateItemCallback callback) {
	    inventoryService().createItem(mId, token, item, callback);
    }

	@Override
	public void getItem(String mId, String token, String itemId,
            GetItemCallback callback) {
	    inventoryService().getItem(mId, token, itemId, callback);
    }

	@Override
	public void updateItem(String mId, String token, String itemId, Item item,
            UpdateItemCallback callback) {
	    inventoryService().updateItem(mId, token, itemId, item, callback);
    }

	@Override
	public void deleteItem(String mId, String token, String itemId,
            DeleteItemCallback callback) {
	    inventoryService().deleteItem(mId, token, itemId, callback);
    }

	@Override
	public void getItemStocks(String mId, String token,
            GetItemStocksCallback callback, Object... params) {
	    inventoryService().getItemStocks(mId, token, callback, params);
    }

	@Override
	public void getItemStock(String mId, String token, String itemId,
            GetItemStockCallback callback) {
	    inventoryService().getItemStock(mId, token, itemId, callback);
    }

	@Override
	public void updateItemStock(String mId, String token, String itemId,
            ItemStock itemStock, UpdateItemStockCallback callback) {
	    inventoryService().updateItemStock(mId, token, itemId, itemStock,
	            callback);
    }

	@Override
	public void deleteItemStock(String mId, String token, String itemId,
            DeleteItemStockCallback callback) {
	    inventoryService().deleteItemStock(mId, token, itemId, callback);
    }

	@Override
	public void getItemGroups(String mId, String token,
            GetItemGroupsCallback callback, Object... params) {
	    inventoryService().getItemGroups(mId, token, callback, params);
    }

	@Override
	public void createItemGroup(String mId, String token, ItemGroup itemGroup,
            CreateItemGroupCallback callback) {
	    inventoryService().createItemGroup(mId, token, itemGroup, callback);
    }

	@Override
	public void getItemGroup(String mId, String token, String itemGroupId,
            GetItemGroupCallback callback) {
	    inventoryService().getItemGroup(mId, token, itemGroupId, callback);
    }

	@Override
	public void updateItemGroup(String mId, String token, String itemGroupId,
            ItemGroup itemGroup, UpdateItemGroupCallback callback) {
	    inventoryService().updateItemGroup(mId, token, itemGroupId, itemGroup,
	            callback);
    }

	@Override
	public void deleteItemGroup(String mId, String token, String itemGroupId,
            DeleteItemGroupCallback callback) {
	    inventoryService().deleteItemGroup(mId, token, itemGroupId, callback);
    }

	@Override
	public void getTags(String mId, String token, GetTagsCallback callback,
            Object... params) {
	    inventoryService().getTags(mId, token, callback, params);
    }

	@Override
	public void createTag(String mId, String token, Tag tag,
            CreateTagCallback callback) {
	    inventoryService().createTag(mId, token, tag, callback);
    }

	@Override
	public void getTag(String mId, String token, String tagId,
            GetTagCallback callback) {
	    inventoryService().getTag(mId, token, tagId, callback);
    }

	@Override
	public void updateTag(String mId, String token, String tagId, Tag tag,
            UpdateTagCallback callback) {
	    inventoryService().updateTag(mId, token, tagId, tag, callback);
    }

	@Override
	public void deleteTag(String mId, String token, String tagId,
            DeleteTagCallback callback) {
	    inventoryService().deleteTag(mId, token, tagId, callback);
    }

	@Override
	public void getItemTags(String mId, String token, String itemId,
            GetItemTagsCallback callback, Object... params) {
	    inventoryService().getItemTags(mId, token, itemId, callback, params);
    }

	@Override
	public void getTagItems(String mId, String token, String tagId,
            GetTagItemsCallback callback, Object... params) {
	    inventoryService().getTagItems(mId, token, tagId, callback, params);
    }

	@Override
	public void createTagItemAssociations(String mId, String token,
            CreateTagItemAssociationsCallback callback,
            TagItemAssociation... associations) {
	    inventoryService().createTagItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void deleteTagItemAssociations(String mId, String token,
            DeleteTagItemAssociationsCallback callback,
            TagItemAssociation... associations) {
	    inventoryService().deleteTagItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void getCategories(String mId, String token,
            GetCategoriesCallback callback, Object... params) {
	    inventoryService().getCategories(mId, token, callback, params);
    }

	@Override
	public void createCategory(String mId, String token, Category category,
            CreateCategoryCallback callback) {
	    inventoryService().createCategory(mId, token, category, callback);
    }

	@Override
	public void getCategory(String mId, String token, String catId,
            GetCategoryCallback callback) {
	    inventoryService().getCategory(mId, token, catId, callback);
    }

	@Override
	public void updateCategory(String mId, String token, String catId,
            Category update, UpdateCategoryCallback callback) {
	    inventoryService().updateCategory(mId, token, catId, update, callback);
    }

	@Override
	public void deleteCategory(String mId, String token, String catId,
            DeleteCategoryCallback callback) {
	    inventoryService().deleteCategory(mId, token, catId, callback);
    }

	@Override
	public void getCategoryItems(String mId, String token, String catId,
            GetCategoryItemsCallback callback, Object... params) {
	    inventoryService().getCategoryItems(mId, token, catId, callback, params);
    }

	@Override
	public void getItemCategories(String mId, String token, String itemId,
            GetItemCategoriesCallback callback, Object... params) {
	    inventoryService().getItemCategories(mId, token, itemId, callback, params);
    }

	@Override
	public void createCategoryItemAssociations(String mId, String token,
            CreateCategoryItemAssociationsCallback callback,
            CategoryItemAssociation... associations) {
	    inventoryService().createCategoryItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void deleteCategoryItemAssociations(String mId, String token,
            DeleteCategoryItemAssociationsCallback callback,
            CategoryItemAssociation... associations) {
	    inventoryService().deleteCategoryItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void createTaxRateItemAssociations(String mId, String token,
            CreateTaxRateItemAssociationsCallback callback,
            TaxRateItemAssociation... associations) {
	    inventoryService().createTaxRateItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void deleteTaxRateItemAssociations(String mId, String token,
            DeleteTaxRateItemAssociationsCallback callback,
            TaxRateItemAssociation... associations) {
	    inventoryService().deleteTaxRateItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void getTaxRateItems(String mId, String token, String taxId,
            GetTaxRateItemsCallback callback, Object... params) {
	    inventoryService().getTaxRateItems(mId, token, taxId, callback, params);
    }

	@Override
	public void getModifierGroups(String mId, String token,
            GetModifierGroupsCallback callback, Object... params) {
	    inventoryService().getModifierGroups(mId, token, callback, params);
    }

	@Override
	public void getModifierGroup(String mId, String token, String modGroupId,
            GetModifierGroupCallback callback) {
	    inventoryService().getModifierGroup(mId, token, modGroupId, callback);
    }

	@Override
	public void createModifierGroup(String mId, String token,
            ModifierGroup modifierGroup, CreateModifierGroupCallback callback) {
	    inventoryService().createModifierGroup(mId, token, modifierGroup,
	            callback);
    }

	@Override
	public void updateModifierGroup(String mId, String tokne,
            String modGroupId, ModifierGroup update,
            UpdateModifierGroupCallback callback) {
	    inventoryService().updateModifierGroup(mId, tokne, modGroupId, update,
	            callback);
    }

	@Override
	public void deleteModifierGroup(String mId, String token,
            String modGroupId, DeleteModifierGroupCallback callback) {
	    inventoryService().deleteModifierGroup(mId, token, modGroupId, callback);
    }

	@Override
	public void createModifierGroupItemAssociations(String mId, String token,
            CreateModifierGroupItemAssociationsCallback callback,
            ModifierGroupItemAssociation... associations) {
	    inventoryService().createModifierGroupItemAssociations(mId, token,
	            callback, associations);
    }

	@Override
	public void deleteModifierGroupItemAssociations(String mId, String token,
            DeleteModifierGroupItemAssociationsCallback callback,
            ModifierGroupItemAssociation... associations) {
	    inventoryService().deleteModifierGroupItemAssociations(mId, token,
	            callback, associations);
    }

	@Override
	public void getModifiers(String mId, String token,
            GetModifiersCallback callback, Object... params) {
	    inventoryService().getModifiers(mId, token, callback, params);
    }

	@Override
	public void getModifierGroupModifiers(String mId, String token,
            String modGroupId, GetModifierGroupModifiersCallback callback,
            Object... params) {
	    inventoryService().getModifierGroupModifiers(mId, token, modGroupId,
	            callback, params);
    }

	@Override
	public void createModifierGroupModifier(String mId, String token,
            String modGroupId, Modifier modifier,
            CreateModifierGroupModifierCallback callback) {
	    inventoryService().createModifierGroupModifier(mId, token, modGroupId,
	            modifier, callback);
    }

	@Override
	public void getModifierGroupModifier(String mId, String token,
            String modGroupId, String modId,
            GetModifierGroupModifierCallback callback) {
	    inventoryService().getModifierGroupModifier(mId, token, modGroupId,
	            modId, callback);
    }

	@Override
	public void updateModifierGroupModifier(String mId, String token,
            String modGroupId, String modId, Modifier update,
            UpdateModifierGroupModifierCallback callback) {
	    inventoryService().updateModifierGroupModifier(mId, token, modGroupId,
	            modId, update, callback);
    }

	@Override
	public void deleteModifierGroupModifier(String mId, String token,
            String modGroupId, String modId,
            DeleteModifierGroupModifierCallback callback) {
	    inventoryService().deleteModifierGroupModifier(mId, token, modGroupId,
	            modId, callback);
    }

	@Override
	public void getAttributes(String mId, String token,
            GetAttributesCallback callback, Object... params) {
	    inventoryService().getAttributes(mId, token, callback, params);
    }

	@Override
	public void createAttribute(String mId, String token, Attribute attribute,
            CreateAttributeCallback callback) {
	    inventoryService().createAttribute(mId, token, attribute, callback);
    }
	
	@Override
	public void getAttribute(String mId, String token, String attributeId,
            GetAttributeCallback callback) {
	    inventoryService().getAttribute(mId, token, attributeId, callback);
    }

	@Override
	public void updateAttribute(String mId, String token, String attributeId,
            Attribute attribute, UpdateAttributeCallback callback) {
	    inventoryService().updateAttribute(mId, token, attributeId, attribute,
	            callback);
    }

	@Override
	public void deleteAttribute(String mId, String token, String attributeId,
            DeleteAttributeCallback callback) {
	    inventoryService().deleteAttribute(mId, token, attributeId, callback);
    }

	@Override
	public void getOptions(String mId, String token,
            GetOptionsCallback callback, Object... params) {
	    inventoryService().getOptions(mId, token, callback, params);
    }

	@Override
	public void getAttributeOptions(String mId, String token,
            String attributeId, GetAttributeOptionsCallback callback,
            Object... params) {
	    inventoryService().getAttributeOptions(mId, token, attributeId, callback,
	            params);
    }

	@Override
	public void createAttributeOption(String mId, String token,
            String attributeId, Option option,
            CreateAttributeOptionCallback callback) {
	    inventoryService().createAttributeOption(mId, token, attributeId, option,
	            callback);
    }

	@Override
	public void getAttributeOption(String mId, String token,
            String attributeId, String optionId,
            GetAttributeOptionCallback callback) {
	    inventoryService().getAttributeOption(mId, token, attributeId, optionId,
	            callback);
    }

	@Override
	public void updateAttributeOption(String mId, String token,
            String attributeId, String optionId, Option update,
            UpdateAttributeOptionCallback callback) {
	    inventoryService().updateAttributeOption(mId, token, attributeId,
	            optionId, update, callback);
    }

	@Override
	public void deleteAttributeOption(String mId, String token,
            String attributeId, String optionId,
            DeleteAttributeOptionCallback callback) {
	    inventoryService().deleteAttributeOption(mId, token, attributeId,
	            optionId, callback);
    }

	@Override
	public void createOptionItemAssociations(String mId, String token,
            CreateOptionItemAssociationsCallback callback,
            OptionItemAssociation... associations) {
	    inventoryService().createOptionItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void deleteOptionItemAssociations(String mId, String token,
            DeleteOptionItemAssociationsCallback callback,
            OptionItemAssociation... associations) {
	    inventoryService().deleteOptionItemAssociations(mId, token, callback,
	            associations);
    }

	@Override
	public void getItemSummary(String mId, String token,
            GetItemSummaryCallback callback, Object... params) {
	    inventoryService().getItemSummary(mId, token, callback, params);
    }

	@Override
	public void getMerchant(String mId, String token,
            GetMerchantCallback callback) {
	    merchantService().getMerchant(mId, token, callback);
    }

	@Override
	public void updateMerchant(String mId, String token, Merchant update,
            UpdateMerchantCallback callback) {
	    merchantService().updateMerchant(mId, token, update, callback);
    }

	@Override
	public void getMerchantAddress(String mId, String token,
            GetMerchantAddressCallback callback) {
	    merchantService().getMerchantAddress(mId, token, callback);
    }

	@Override
	public void getMerchantProperties(String mId, String token,
            GetMerchantPropertiesCallback callback) {
	    merchantService().getMerchantProperties(mId, token, callback);
    }

	@Override
	public void updateMerchantProperties(String mId, String token,
            Properties update, UpdateMerchantPropertiesCallback callback) {
	    merchantService().updateMerchantProperties(mId, token, update, callback);
    }

	@Override
    public Devices getDevices(String mId, String token) throws RESTException {
	    return merchantService().getDevices(mId, token);
    }

	@Override
    public void getDevices(String mId, String token, GetDevicesCallback callback) {
	    merchantService().getDevices(mId, token, callback);
    }
	
	@Override
	public void getTipSuggestions(String mId, String token,
            GetTipSuggestionsCallback callback, Object... params) {
	    merchantService().getTipSuggestions(mId, token, callback, params);
    }

	@Override
	public void getTipSuggestion(String mId, String token, String tId,
            GetTipSuggestionCallback callback) {
	    merchantService().getTipSuggestion(mId, token, tId, callback);
    }

	@Override
	public void updateTipSuggestion(String mId, String token, String id,
            TipSuggestion update, UpdateTipSuggestionCallback callback) {
	    merchantService().updateTipSuggestion(mId, token, id, update, callback);
    }

	@Override
	public void getOrderTypes(String mId, String token,
            GetOrderTypesCallback callback, Object... params) {
	    merchantService().getOrderTypes(mId, token, callback, params);
    }

	@Override
	public void createOrderType(String mId, String token, OrderType orderType,
            CreateOrderTypeCallback callback) {
	    merchantService().createOrderType(mId, token, orderType, callback);
    }

	@Override
	public void getOrderType(String mId, String token, String orderTypeId,
            GetOrderTypeCallback callback) {
	    merchantService().getOrderType(mId, token, orderTypeId, callback);
    }

	@Override
	public void updateOrderType(String mId, String token, String orderTypeId,
            OrderType update, UpdateOrderTypeCallback callback) {
	    merchantService().updateOrderType(mId, token, orderTypeId, update,
	            callback);
    }

	@Override
	public void deleteOrderType(String mId, String token, String orderTypeId,
            DeleteOrderTypeCallback callback) {
	    merchantService().deleteOrderType(mId, token, orderTypeId, callback);
    }

	@Override
	public void getRoles(String mId, String token, GetRolesCallback callback,
            Object... params) {
	    merchantService().getRoles(mId, token, callback, params);
    }

	@Override
	public void createRole(String mId, String token, Role role,
            CreateRoleCallback callback) {
	    merchantService().createRole(mId, token, role, callback);
    }

	@Override
	public void getRole(String mId, String token, String rId,
            GetRoleCallback callback) {
	    merchantService().getRole(mId, token, rId, callback);
    }

	@Override
	public void updateRole(String mId, String token, String rId, Role role,
            UpdateRoleCallback callback) {
	    merchantService().updateRole(mId, token, rId, role, callback);
    }

	@Override
	public void deleteRole(String mId, String token, String rId,
            DeleteRoleCallback callback) {
	    merchantService().deleteRole(mId, token, rId, callback);
    }

	@Override
	public void getTenders(String mId, String token,
            GetTendersCallback callback, Object... params) {
	    merchantService().getTenders(mId, token, callback, params);
    }

	@Override
	public void getTender(String mId, String token, String tenderId,
            GetTenderCallback callback) {
	    merchantService().getTender(mId, token, tenderId, callback);
    }

	@Override
	public void getOpeningHours(String mId, String token,
            GetOpeningHoursCallback callback, Object... params) {
	    merchantService().getOpeningHours(mId, token, callback, params);
    }

	@Override
	public void createOpeningHour(String mId, String token,
            HoursSet openingHours, CreateOpeningHourCallback callback) {
	    merchantService().createOpeningHour(mId, token, openingHours, callback);
    }

	@Override
	public void getOpeningHour(String mId, String token, String hId,
            GetOpeningHourCallback callback) {
	    merchantService().getOpeningHour(mId, token, hId, callback);
    }

	@Override
	public void updateOpeningHour(String mId, String token, String hid,
            HoursSet hoursSet, UpdateOpeningHourCallback callback) {
	    merchantService().updateOpeningHour(mId, token, hid, hoursSet, callback);
    }

	@Override
	public void deleteOpeningHour(String mId, String token, String hId,
            DeleteOpeningHourCallback callback) {
	    merchantService().deleteOpeningHour(mId, token, hId, callback);
    }

	@Override
	public void getTaxRates(String mId, String token,
            GetTaxRatesCallback callback, Object... params) {
	    merchantService().getTaxRates(mId, token, callback, params);
    }

	@Override
	public void createTaxRate(String mId, String token, TaxRate taxRate,
            CreateTaxRateCallback callback) {
	    merchantService().createTaxRate(mId, token, taxRate, callback);
    }
	
	@Override
	public void getTaxRate(String mId, String token, String taxId,
            GetTaxRateCallback callback) {
	    merchantService().getTaxRate(mId, token, taxId, callback);
    }

	@Override
	public void updateTaxRate(String mId, String token, String taxId,
            TaxRate update, UpdateTaxRateCallback callback) {
	    merchantService().updateTaxRate(mId, token, taxId, update, callback);
    }

	@Override
	public void deleteTaxRate(String mId, String token, String taxId,
            DeleteTaxRateCallback callback) {
	    merchantService().deleteTaxRate(mId, token, taxId, callback);
    }

	@Override
	public void getTaxRateSummary(String mId, String token,
            GetTaxRateSummaryCallback callback, Object... params) {
	    merchantService().getTaxRateSummary(mId, token, callback, params);
    }

	@Override
	public void createAppNotification(String appId, String mId, String token,
            AppNotification notification, CreateAppNotificationCallback callback) {
	    notificationService().createAppNotification(appId, mId, token,
	            notification, callback);
    }

	@Override
	public void createDeviceNotification(String appId, String deviceId,
            String token, AppNotification notification,
            CreateDeviceNotificationCallback callback) {
	    notificationService().createDeviceNotification(appId, deviceId, token,
	            notification, callback);
    }

	@Override
	public void getOrders(String mId, String token, GetOrdersCallback callback,
            Object... params) {
	    orderService().getOrders(mId, token, callback, params);
    }

	@Override
	public void createOrder(String mId, String token, Order order,
            CreateOrderCallback callback) {
	    orderService().createOrder(mId, token, order, callback);
    }

	@Override
	public void getOrder(String mId, String token, String orderId,
            GetOrderCallback callback) {
	    orderService().getOrder(mId, token, orderId, callback);
    }

	@Override
	public void updateOrder(String mId, String token, String orderId,
            Order update, UpdateOrderCallback callback) {
	    orderService().updateOrder(mId, token, orderId, update, callback);
    }

	@Override
	public void deleteOrder(String mId, String token, String orderId,
            DeleteOrderCallback callback) {
	    orderService().deleteOrder(mId, token, orderId, callback);
    }

	@Override
	public void getOrderDiscounts(String mId, String token, String orderId,
            GetOrderDiscountsCallback callback, Object... params) {
	    orderService().getOrderDiscounts(mId, token, orderId, callback, params);
    }

	@Override
	public void createOrderDiscount(String mId, String token, String orderId,
            Discount discount, CreateOrderDiscountCallback callback) {
	    orderService().createOrderDiscount(mId, token, orderId, discount,
	            callback);
    }

	@Override
	public void deleteOrderDiscount(String mId, String token, String orderId,
            String discountId, DeleteOrderDiscountCallback callback) {
	    orderService().deleteOrderDiscount(mId, token, orderId, discountId,
	            callback);
    }

	@Override
	public void getLineItems(String mId, String token,
            GetLineItemsCallback callback, Object... params) {
	    orderService().getLineItems(mId, token, callback, params);
    }

	@Override
	public void getLineItem(String mId, String token, String lineItemId,
            GetLineItemCallback callback) {
	    orderService().getLineItem(mId, token, lineItemId, callback);
    }

	@Override
	public void getOrderLineItems(String mId, String token, String orderId,
            GetOrderLineItemsCallback callback, Object... params) {
	    orderService().getOrderLineItems(mId, token, orderId, callback, params);
    }

	@Override
	public void createOrderLineItem(String mId, String token, String orderId,
            LineItem lineItem, CreateOrderLineItemCallback callback) {
	    orderService().createOrderLineItem(mId, token, orderId, lineItem,
	            callback);
    }

	@Override
	public void getOrderLineItem(String mId, String token, String orderId,
            String lineItemId, GetOrderLineItemCallback callback) {
	    orderService().getOrderLineItem(mId, token, orderId, lineItemId, callback);
    }

	@Override
	public void updateOrderLineItem(String mId, String token, String orderId,
            String lineItemId, LineItem update,
            UpdateOrderLineItemCallback callback) {
	    orderService().updateOrderLineItem(mId, token, orderId, lineItemId,
	            update, callback);
    }

	@Override
	public void deleteOrderLineItem(String mId, String token, String orderId,
            String lineItemId, DeleteOrderLineItemCallback callback) {
	    orderService().deleteOrderLineItem(mId, token, orderId, lineItemId,
	            callback);
    }

	@Override
	public void createOrderLineItemDiscount(String mId, String token,
            String orderid, String lineItemId, Discount discount,
            CreateOrderLineItemDiscountCallback callback) {
	    orderService().createOrderLineItemDiscount(mId, token, orderid,
	            lineItemId, discount, callback);
    }

	@Override
	public void deleteOrderLineItemDiscount(String mId, String token,
            String orderId, String lineItemId, String discountId,
            DeleteOrderLineItemDiscountCallback callback) {
	    orderService().deleteOrderLineItemDiscount(mId, token, orderId,
	            lineItemId, discountId, callback);
    }

	@Override
	public void createOrderLineItemModification(String mId, String token,
            String orderId, String lineItemId, Modification modification,
            CreateOrderLineItemModificationCallback callback) {
	    orderService().createOrderLineItemModification(mId, token, orderId,
	            lineItemId, modification, callback);
    }

	@Override
	public void deleteOrderLineItemModification(String mId, String token,
            String orderId, String lineItemId, String modificationId,
            DeleteOrderLineItemModificationCallback callback) {
	    orderService().deleteOrderLineItemModification(mId, token, orderId,
	            lineItemId, modificationId, callback);
    }

	@Override
	public void createOrderPayment(String mId, String token, String orderId,
            Payment payment, CreateOrderPaymentCallback callback) {
	    orderService().createOrderPayment(mId, token, orderId, payment, callback);
    }

	@Override
	public void createOrderServiceCharge(String mId, String token,
            String orderId, ServiceCharge serviceCharge,
            CreateOrderServiceChargeCallback callback) {
	    orderService().createOrderServiceCharge(mId, token, orderId,
	            serviceCharge, callback);
    }

	@Override
	public void deleteOrderServiceCharge(String mId, String token,
            String orderId, String serviceChargeId,
            DeleteOrderServiceChargeCallback callback) {
	    orderService().deleteOrderServiceCharge(mId, token, orderId,
	            serviceChargeId, callback);
    }

	@Override
	public void exchangeOrderLineItem(String mId, String token, String orderId,
            String oldLineItemId, String lineItemId,
            ExchangeOrderLineItemCallback callback) {
	    orderService().exchangeOrderLineItem(mId, token, orderId, oldLineItemId,
	            lineItemId, callback);
    }

	@Override
	public void getDiscountSummary(String mId, String token,
            GetDiscountSummaryCallback callback, Object... params) {
	    orderService().getDiscountSummary(mId, token, callback, params);
    }

	@Override
	public void getPayments(String mId, String token,
            GetPaymentsCallback callback, Object... params) {
	    paymentService().getPayments(mId, token, callback, params);
    }

	@Override
	public void createPayment(String mId, String token, Payment payment,
            CreatePaymentCallback callback) {
	    paymentService().createPayment(mId, token, payment, callback);
    }

	@Override
	public void getPayment(String mId, String token, String paymentId,
            GetPaymentCallback callback) {
	    paymentService().getPayment(mId, token, paymentId, callback);
    }

	@Override
	public void updatePayment(String mId, String token, String paymentId,
            Payment update, UpdatePaymentCallback callback) {
	    paymentService().updatePayment(mId, token, paymentId, update, callback);
    }

	@Override
	public void getOrderPayments(String mId, String token, String orderId,
            GetOrderPaymentsCallback callback, Object... params) {
	    paymentService().getOrderPayments(mId, token, orderId, callback, params);
    }

	@Override
	public void getEmployeePayments(String mId, String token, String empId,
            GetEmployeePaymentsCallback callback, Object... params) {
	    paymentService().getEmployeePayments(mId, token, empId, callback, params);
    }

	@Override
	public void getRefunds(String mId, String token,
            GetRefundsCallback callback, Object... params) {
	    paymentService().getRefunds(mId, token, callback, params);
    }

	@Override
	public void getRefund(String mId, String token, String refundId,
            GetRefundCallback callback) {
	    paymentService().getRefund(mId, token, refundId, callback);
    }

	@Override
	public void getCredits(String mId, String token,
            GetCreditsCallback callback, Object... params) {
	    paymentService().getCredits(mId, token, callback, params);
    }

	@Override
	public void getCredit(String mId, String token, String cId,
            GetCreditCallback callback) {
	    paymentService().getCredit(mId, token, cId, callback);
    }

	@Override
	public void getPaymentSummary(String mId, String token,
            GetPaymentSummaryCallback callback, Object... params) {
	    paymentService().getPaymentSummary(mId, token, callback, params);
    }

	@Override
	public void getTenderSummary(String mId, String token,
            GetTenderSummaryCallback callback, Object... params) {
	    paymentService().getTenderSummary(mId, token, callback, params);
    }

	@Override
	public void getCardSummary(String mId, String token,
            GetCardSummaryCallback callback, Object... params) {
	    paymentService().getCardSummary(mId, token, callback, params);
    }

	@Override
	public void getCreditSummary(String mId, String token,
            GetCreditSummaryCallback callback, Object... params) {
	    paymentService().getCreditSummary(mId, token, callback, params);
    }

	@Override
	public void getRefundSummary(String mId, String token,
            GetRefundSummaryCallback callback, Object... params) {
	    paymentService().getRefundSummary(mId, token, callback, params);
    }

	@Override
    public AppBillingInfo getBillingInfo(String merchantId, String token,
            String appId) throws RESTException {
	    return appService().getBillingInfo(merchantId, token, appId);
    }

	@Override
    public void getBillingInfo(String merchantId, String token, String appId,
            GetBillingInfoCallback callback) {
	    appService().getBillingInfo(merchantId, token, appId, callback);
    }

	@Override
    public AppMeteredEvent createMeteredEvent(String merchantId, String token,
            String appId, String meteredId, AppMeteredEvent event)
            throws RESTException {
	    return appService().createMeteredEvent(merchantId, token, appId, meteredId, event);
    }

	@Override
    public void createMeteredEvent(String merchantId, String token,
            String appId, String meteredId, AppMeteredEvent event,
            CreateMeteredEventCallback callback) {
	    appService().createMeteredEvent(merchantId, token, appId, meteredId, event, callback);
    }

	@Override
    public List<com.trubeacon.cloverandroidapi.discount.Discount> getDiscounts(
            String merchantId, String token, Object... params)
            throws RESTException {
	    return discountService().getDiscounts(merchantId, token, params);
    }

	@Override
    public void getDiscounts(String merchantId, String token,
            GetDiscountsCallback callback, Object... params) {
	    discountService().getDiscounts(merchantId, token, callback, params);
    }

	@Override
    public com.trubeacon.cloverandroidapi.discount.Discount getDiscount(String merchantId,
            String token, String id) throws RESTException {
	    return discountService().getDiscount(merchantId, token, id);
    }

	@Override
    public void getDiscount(String merchantId, String token, String id,
            GetDiscountCallback callback) {
	    discountService().getDiscount(merchantId, token, id, callback);
    }

	@Override
    public com.trubeacon.cloverandroidapi.discount.Discount createDiscount(
            String merchantId, String token,
            com.trubeacon.cloverandroidapi.discount.Discount discount) throws RESTException {
	    return discountService().createDiscount(merchantId, token, discount);
    }

	@Override
    public void createDiscount(String merchantId, String token,
            com.trubeacon.cloverandroidapi.discount.Discount discount,
            CreateDiscountCallback callback) {
	    discountService().createDiscount(merchantId, token, discount, callback);
    }

	@Override
    public com.trubeacon.cloverandroidapi.discount.Discount updateDiscount(
            String merchantId, String token, String id,
            com.trubeacon.cloverandroidapi.discount.Discount discount) throws RESTException {
	    return discountService().updateDiscount(merchantId, token, id, discount);
    }

	@Override
    public void updateDiscount(String merchantId, String token, String id,
            com.trubeacon.cloverandroidapi.discount.Discount discount,
            UpdateDiscountCallback callback) {
	    discountService().updateDiscount(merchantId, token, id, discount, callback);
    }

	@Override
    public com.trubeacon.cloverandroidapi.discount.Discount deleteDiscount(
            String merchantId, String token, String id) throws RESTException {
	    return discountService().deleteDiscount(merchantId, token, id);
    }

	@Override
    public void deleteDiscount(String merchantId, String token, String id,
            DeleteDiscountCallback callback) {
	    discountService().deleteDiscount(merchantId, token, id, callback);
    }

	@Override
    public List<Permission> getPermissions(String mId, String token)
            throws RESTException {
	    return merchantService().getPermissions(mId, token);
    }

	@Override
    public void getPermissions(String mId, String token,
            GetPermissionsCallback callback) {
	    merchantService().getPermissions(mId, token, callback);
    }

	@Override
    public List<Merchant> getMerchants(String accountId, String token)
            throws RESTException {
	    return accountService().getMerchants(accountId, token);
    }

	@Override
    public void getMerchants(String accountId, String token,
            GetMerchantsCallback callback) {
	    accountService().getMerchants(accountId, token, callback);
    }
	
}
