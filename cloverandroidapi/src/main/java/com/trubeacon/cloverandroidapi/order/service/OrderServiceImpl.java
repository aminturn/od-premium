package com.trubeacon.cloverandroidapi.order.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.order.Discount;
import com.trubeacon.cloverandroidapi.order.DiscountSummary;
import com.trubeacon.cloverandroidapi.order.LineItem;
import com.trubeacon.cloverandroidapi.order.Modification;
import com.trubeacon.cloverandroidapi.order.Order;
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
import com.trubeacon.cloverandroidapi.order.service.UpdateOrder.UpdateOrderCallback;
import com.trubeacon.cloverandroidapi.order.service.UpdateOrderLineItem.UpdateOrderLineItemCallback;
import com.trubeacon.cloverandroidapi.payment.Payment;
import com.trubeacon.cloverandroidapi.payment.ServiceCharge;

public class OrderServiceImpl implements OrderService,
										GetOrdersCallback,
										CreateOrderCallback,
										GetOrderCallback,
										UpdateOrderCallback,
										DeleteOrderCallback,
										GetOrderDiscountsCallback,
										CreateOrderDiscountCallback,
										DeleteOrderDiscountCallback,
										GetLineItemsCallback,
										GetLineItemCallback,
										GetOrderLineItemsCallback,
										CreateOrderLineItemCallback,
										GetOrderLineItemCallback,
										UpdateOrderLineItemCallback,
										DeleteOrderLineItemCallback,
										CreateOrderLineItemDiscountCallback,
										DeleteOrderLineItemDiscountCallback,
										CreateOrderLineItemModificationCallback,
										DeleteOrderLineItemModificationCallback,
										CreateOrderPaymentCallback,
										CreateOrderServiceChargeCallback,
										DeleteOrderServiceChargeCallback,
										ExchangeOrderLineItemCallback,
										GetDiscountSummaryCallback {

	private GetOrdersCallback getOrdersCallback;
	private CreateOrderCallback createOrderCallback;
	private GetOrderCallback getOrderCallback;
	private UpdateOrderCallback updateOrderCallback;
	private DeleteOrderCallback deleteOrderCallback;
	private GetOrderDiscountsCallback getOrderDiscountsCallback;
	private CreateOrderDiscountCallback createOrderDiscountCallback;
	private DeleteOrderDiscountCallback deleteOrderDiscountCallback;
	private GetLineItemsCallback getLineItemsCallback;
	private GetLineItemCallback getLineItemCallback;
	private GetOrderLineItemsCallback getOrderLineItemsCallback;
	private CreateOrderLineItemCallback createOrderLineItemCallback;
	private GetOrderLineItemCallback getOrderLineItemCallback;
	private UpdateOrderLineItemCallback updateOrderLineItemCallback;
	private DeleteOrderLineItemCallback deleteOrderLineItemCallback;
	private CreateOrderLineItemDiscountCallback createOrderLineItemDiscountCallback;
	private DeleteOrderLineItemDiscountCallback deleteOrderLineItemDiscountCallback;
	private CreateOrderLineItemModificationCallback createOrderLineItemModificationCallback;
	private DeleteOrderLineItemModificationCallback deleteOrderLineItemModificationCallback;
	private CreateOrderPaymentCallback createOrderPaymentCallback;
	private CreateOrderServiceChargeCallback createOrderServiceChargeCallback;
	private DeleteOrderServiceChargeCallback deleteOrderServiceChargeCallback;
	private ExchangeOrderLineItemCallback exchangeOrderLineItemCallback;
	private GetDiscountSummaryCallback getDiscountSummaryCallback;
	
	@Override
    public List<Order> getOrders(String mId, String token, Object... params) throws RESTException {
	    return new GetOrders(mId, token, params).execute();
    }

	@Override
    public Order createOrder(String mId, String token, Order order)
            throws RESTException {
	    return new CreateOrder(mId, token, order).execute();
    }

	@Override
    public Order getOrder(String mId, String token, String orderId)
            throws RESTException {
	    return new GetOrder(mId, token, orderId).execute();
    }

	@Override
    public Order updateOrder(String mId, String token, String orderId,
            Order update) throws RESTException {
	    return new UpdateOrder(mId, token, orderId, update).execute();
    }

	@Override
    public Order deleteOrder(String mId, String token, String orderId)
            throws RESTException {
	    return new DeleteOrder(mId, token, orderId).execute();
    }

	@Override
    public List<Discount> getOrderDiscounts(String mId, String token,
            String orderId, Object... params) throws RESTException {
	    return new GetOrderDiscounts(mId, token, orderId, params).execute();
    }

	@Override
    public Discount createOrderDiscount(String mId, String token,
            String orderId, Discount discount) throws RESTException {
	    return new CreateOrderDiscount(mId, token, orderId, discount).execute();
    }

	@Override
    public Discount deleteOrderDiscount(String mId, String token,
            String orderId, String discountId) throws RESTException {
	    return new DeleteOrderDiscount(mId, token, orderId, discountId).execute();
    }

	@Override
    public List<LineItem> getOrderLineItems(String mId, String token,
            String orderId, Object... params) throws RESTException {
	    return new GetOrderLineItems(mId, token, orderId, params).execute();
    }

	@Override
    public LineItem createOrderLineItem(String mId, String token,
            String orderId, LineItem lineItem) throws RESTException {
	    return new CreateOrderLineItem(mId, token, orderId, lineItem).execute();
    }

	@Override
    public LineItem getOrderLineItem(String mId, String token, String orderId,
            String lineItemId) throws RESTException {
	    return new GetOrderLineItem(mId, token, orderId, lineItemId).execute();
    }

	@Override
    public LineItem updateOrderLineItem(String mId, String token,
            String orderId, String lineItemId, LineItem update)
            throws RESTException {
	    return new UpdateOrderLineItem(mId, token, orderId, lineItemId, update).execute();
    }

	@Override
    public LineItem deleteOrderLineItem(String mId, String token,
            String orderId, String lineItemId) throws RESTException {
	    return new DeleteOrderLineItem(mId, token, orderId, lineItemId).execute();
    }

	@Override
    public Discount createOrderLineItemDiscount(String mid, String token,
            String orderId, String lineItemId, Discount discount)
            throws RESTException {
	    return new CreateOrderLineItemDiscount(mid, token, orderId, lineItemId, discount).execute();
    }

	@Override
    public Discount deleteOrderLineItemDiscount(String mId, String token,
            String orderId, String lineItemId, String discountId)
            throws RESTException {
	    return new DeleteOrderLineItemDiscount(mId, token, orderId, lineItemId, discountId).execute();
    }

	@Override
    public Modification createOrderLineItemModification(String mId,
            String token, String orderId, String lineItemId,
            Modification modification) throws RESTException {
	    return new CreateOrderLineItemModification(mId, token, orderId, lineItemId, modification).execute();
    }

	@Override
    public Modification deleteOrderLineItemModification(String mId,
            String token, String orderId, String lineItemId,
            String modificationId) throws RESTException {
	    return new DeleteOrderLineItemModification(mId, token, orderId, lineItemId, modificationId).execute();
    }

	@Override
    public Payment createOrderPayment(String mId, String token, String orderId,
            Payment payment) throws RESTException {
	    return new CreateOrderPayment(mId, token, orderId, payment).execute();
    }

	@Override
    public ServiceCharge createOrderServiceCharge(String mId, String token,
            String orderId, ServiceCharge serviceCharge) throws RESTException {
	    return new CreateOrderServiceCharge(mId, token, orderId, serviceCharge).execute();
    }

	@Override
    public ServiceCharge deleteOrderServiceCharge(String mId, String token,
            String orderId, String serviceChargeId) throws RESTException {
	    return new DeleteOrderServiceCharge(mId, token, orderId, serviceChargeId).execute();
    }

	@Override
    public LineItem exchangeOrderLineItem(String mId, String token,
            String orderId, String oldLineItemId, String lineItemId)
            throws RESTException {
	    return new ExchangeOrderLineItem(mId, token, orderId, oldLineItemId, lineItemId).execute();
    }

	@Override
    public List<LineItem> getLineItems(String mId, String token,
            Object... params) throws RESTException {
	    return new GetLineItems(mId, token, params).execute();
    }

	@Override
    public LineItem getLineItem(String mId, String token, String lineItemId) throws RESTException {
	    return new GetLineItem(mId, token, lineItemId).execute();
    }

	@Override
    public DiscountSummary getDiscountSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetDiscountSummary(mId, token, params).execute();
    }

	@Override
    public void getOrders(String mId, String token, GetOrdersCallback callback,
            Object... params) {
	    this.getOrdersCallback = callback;
	    new GetOrders(mId, token, params).execute(this);
    }

	@Override
    public void createOrder(String mId, String token, Order order,
            CreateOrderCallback callback) {
	    this.createOrderCallback = callback;
	    new CreateOrder(mId, token, order).execute(this);
    }

	@Override
    public void getOrder(String mId, String token, String orderId,
            GetOrderCallback callback) {
	    this.getOrderCallback = callback;
	    new GetOrder(mId, token, orderId).execute(this);
    }

	@Override
    public void updateOrder(String mId, String token, String orderId,
            Order update, UpdateOrderCallback callback) {
	    this.updateOrderCallback = callback;
	    new UpdateOrder(mId, token, orderId, update).execute(this);
    }

	@Override
    public void deleteOrder(String mId, String token, String orderId,
            DeleteOrderCallback callback) {
	    this.deleteOrderCallback = callback;
	    new DeleteOrder(mId, token, orderId).execute(this);
    }

	@Override
    public void getOrderDiscounts(String mId, String token, String orderId,
            GetOrderDiscountsCallback callback, Object... params) {
	    this.getOrderDiscountsCallback = callback;
	    new GetOrderDiscounts(mId, token, orderId, params).execute(this);
    }

	@Override
    public void createOrderDiscount(String mId, String token, String orderId,
            Discount discount, CreateOrderDiscountCallback callback) {
	    this.createOrderDiscountCallback = callback;
	    new CreateOrderDiscount(mId, token, orderId, discount).execute(this);
    }

	@Override
    public void deleteOrderDiscount(String mId, String token, String orderId,
            String discountId, DeleteOrderDiscountCallback callback) {
	    this.deleteOrderDiscountCallback = callback;
	    new DeleteOrderDiscount(mId, token, orderId, discountId).execute(this);
    }

	@Override
    public void getLineItems(String mId, String token,
            GetLineItemsCallback callback, Object... params) {
	    this.getLineItemsCallback = callback;
	    new GetLineItems(mId, token, params).execute(this);
    }

	@Override
    public void getLineItem(String mId, String token, String lineItemId,
            GetLineItemCallback callback) {
	    this.getLineItemCallback = callback;
	    new GetLineItem(mId, token, lineItemId).execute(this);
    }

	@Override
    public void getOrderLineItems(String mId, String token, String orderId,
            GetOrderLineItemsCallback callback, Object... params) {
	    this.getOrderLineItemsCallback = callback;
	    new GetOrderLineItems(mId, token, orderId, params).execute(this);
    }
	
	@Override
    public void createOrderLineItem(String mId, String token, String orderId,
            LineItem lineItem, CreateOrderLineItemCallback callback) {
	    this.createOrderLineItemCallback = callback;
	    new CreateOrderLineItem(mId, token, orderId, lineItem).execute(this);
    }

	@Override
    public void getOrderLineItem(String mId, String token, String orderId,
            String lineItemId, GetOrderLineItemCallback callback) {
	    this.getOrderLineItemCallback = callback;
	    new GetOrderLineItem(mId, token, orderId, lineItemId).execute(this);
    }

	@Override
    public void updateOrderLineItem(String mId, String token, String orderId,
            String lineItemId, LineItem update,
            UpdateOrderLineItemCallback callback) {
	    this.updateOrderLineItemCallback = callback;
	    new UpdateOrderLineItem(mId, token, orderId, lineItemId, update).execute(this);
    }

	@Override
    public void deleteOrderLineItem(String mId, String token, String orderId,
            String lineItemId, DeleteOrderLineItemCallback callback) {
	    this.deleteOrderLineItemCallback = callback;
	    new DeleteOrderLineItem(mId, token, orderId, lineItemId).execute(this);
    }

	@Override
    public void createOrderLineItemDiscount(String mId, String token,
            String orderId, String lineItemId, Discount discount,
            CreateOrderLineItemDiscountCallback callback) {
	    this.createOrderLineItemDiscountCallback = callback;
	    new CreateOrderLineItemDiscount(mId, token, orderId, lineItemId, discount).execute(this);
    }

	@Override
    public void deleteOrderLineItemDiscount(String mId, String token,
            String orderId, String lineItemId, String discountId,
            DeleteOrderLineItemDiscountCallback callback) {
	    this.deleteOrderLineItemDiscountCallback = callback;
	    new DeleteOrderLineItemDiscount(mId, token, orderId, lineItemId, discountId).execute(this);
    }

	@Override
    public void createOrderLineItemModification(String mId, String token,
            String orderId, String lineItemId, Modification modification,
            CreateOrderLineItemModificationCallback callback) {
	    this.createOrderLineItemModificationCallback = callback;
	    new CreateOrderLineItemModification(mId, token, orderId, lineItemId, modification).execute(this);
    }

	@Override
    public void deleteOrderLineItemModification(String mId, String token,
            String orderId, String lineItemId, String modificationId,
            DeleteOrderLineItemModificationCallback callback) {
	    this.deleteOrderLineItemModificationCallback = callback;
	    new DeleteOrderLineItemModification(mId, token, orderId, lineItemId, modificationId).execute(this);
    }
	
	@Override
    public void createOrderPayment(String mId, String token, String orderId,
            Payment payment, CreateOrderPaymentCallback callback) {
	    this.createOrderPaymentCallback = callback;
	    new CreateOrderPayment(mId, token, orderId, payment).execute(this);
    }

	@Override
    public void createOrderServiceCharge(String mId, String token,
            String orderId, ServiceCharge serviceCharge,
            CreateOrderServiceChargeCallback callback) {
	    this.createOrderServiceChargeCallback = callback;
	    new CreateOrderServiceCharge(mId, token, orderId, serviceCharge).execute(this);
    }

	@Override
    public void deleteOrderServiceCharge(String mId, String token,
            String orderId, String serviceChargeId,
            DeleteOrderServiceChargeCallback callback) {
	    this.deleteOrderServiceChargeCallback = callback;
	    new DeleteOrderServiceCharge(mId, token, orderId, serviceChargeId).execute(this);
    }

	@Override
    public void exchangeOrderLineItem(String mId, String token, String orderId,
            String oldLineItemId, String lineItemId,
            ExchangeOrderLineItemCallback callback) {
	    this.exchangeOrderLineItemCallback = callback;
	    new ExchangeOrderLineItem(mId, token, orderId, oldLineItemId, lineItemId).execute(this);
    }

	@Override
    public void getDiscountSummary(String mId, String token,
            GetDiscountSummaryCallback callback, Object... params) {
	    this.getDiscountSummaryCallback = callback;
	    new GetDiscountSummary(mId, token, params).execute(this);
    }

	@Override
	public void onGetOrders(WrappedList<Order> result) {
		if (getOrdersCallback != null) {
			getOrdersCallback.onGetOrders(result);
		}
    }


	@Override
	public void onFailGetOrders(Error error) {
		if (getOrdersCallback != null) {
			getOrdersCallback.onFailGetOrders(error);
		}
    }

	@Override
	public void onCreateOrder(Order result) {
		if (createOrderCallback != null) {
			createOrderCallback.onCreateOrder(result);
		}
    }

	@Override
	public void onFailCreateOrder(Error error) {
		if (createOrderCallback != null) {
			createOrderCallback.onFailCreateOrder(error);
		}
    }

	@Override
	public void onGetOrder(Order result) {
		if (getOrderCallback != null) {
			getOrderCallback.onGetOrder(result);
		}
    }

	@Override
	public void onFailGetOrder(Error error) {
		if (getOrderCallback != null) {
			getOrderCallback.onFailGetOrder(error);
		}
    }

	@Override
	public void onUpdateOrder(Order result) {
		if (updateOrderCallback != null) {
			updateOrderCallback.onUpdateOrder(result);
		}
    }

	@Override
	public void onFailUpdateOrder(Error error) {
		if (updateOrderCallback != null) {
			updateOrderCallback.onFailUpdateOrder(error);
		}
    }

	@Override
	public void onDeleteOrder(Order result) {
		if (deleteOrderCallback != null) {
			deleteOrderCallback.onDeleteOrder(result);
		}
    }

	@Override
	public void onFailDeleteOrder(Error error) {
		if (deleteOrderCallback != null) {
			deleteOrderCallback.onFailDeleteOrder(error);
		}
    }

	@Override
	public void onGetOrderDiscounts(WrappedList<Discount> result) {
		if (getOrderDiscountsCallback != null) {
			getOrderDiscountsCallback.onGetOrderDiscounts(result);
		}
    }

	@Override
	public void onFailGetOrderDiscounts(Error error) {
		if (getOrderDiscountsCallback != null) {
			getOrderDiscountsCallback.onFailGetOrderDiscounts(error);
		}
    }

	@Override
	public void onCreateOrderDiscount(Discount result) {
		if (createOrderDiscountCallback != null) {
			createOrderDiscountCallback.onCreateOrderDiscount(result);
		}
    }

	@Override
	public void onFailCreateOrderDiscount(Error error) {
		if (createOrderDiscountCallback != null) {
			createOrderDiscountCallback.onFailCreateOrderDiscount(error);
		}
    }

	@Override
	public void onDeleteOrderDiscount(Discount result) {
		if (deleteOrderDiscountCallback != null) {
			deleteOrderDiscountCallback.onDeleteOrderDiscount(result);
		}
    }

	@Override
	public void onFailDeleteOrderDiscount(Error error) {
		if (deleteOrderDiscountCallback != null) {
			deleteOrderDiscountCallback.onFailDeleteOrderDiscount(error);
		}
    }

	@Override
	public void onGetLineItems(WrappedList<LineItem> result) {
		if (getLineItemsCallback != null) {
			getLineItemsCallback.onGetLineItems(result);
		}
    }

	@Override
	public void onFailGetLineItems(Error error) {
		if (getLineItemsCallback != null) {
			getLineItemsCallback.onFailGetLineItems(error);
		}
    }

	@Override
	public void onGetLineItem(LineItem result) {
		if (getLineItemCallback != null) {
			getLineItemCallback.onGetLineItem(result);
		}
    }

	@Override
	public void onFailGetLineItem(Error error) {
		if (getLineItemCallback != null) {
			getLineItemCallback.onFailGetLineItem(error);
		}
    }

	@Override
	public void onGetOrderLineItems(WrappedList<LineItem> result) {
		if (getOrderLineItemsCallback != null) {
			getOrderLineItemsCallback.onGetOrderLineItems(result);
		}
    }

	@Override
	public void onFailGetOrderLineItems(Error error) {
		if (getOrderLineItemsCallback != null) {
			getOrderLineItemsCallback.onFailGetOrderLineItems(error);
		}
    }

	@Override
	public void onCreateOrderLineItem(LineItem result) {
		if (createOrderLineItemCallback != null) {
			createOrderLineItemCallback.onCreateOrderLineItem(result);
		}
    }

	@Override
	public void onFailCreateOrderLineItem(Error error) {
		if (createOrderLineItemCallback != null) {
			createOrderLineItemCallback.onFailCreateOrderLineItem(error);
		}
    }

	@Override
	public void onGetOrderLineItem(LineItem result) {
		if (getOrderLineItemCallback != null) {
			getOrderLineItemCallback.onGetOrderLineItem(result);
		}
    }

	@Override
	public void onFailGetOrderLineItem(Error error) {
		if (getOrderLineItemCallback != null) {
			getOrderLineItemCallback.onFailGetOrderLineItem(error);
		}
    }

	@Override
	public void onUpdateOrderLineItem(LineItem result) {
		if (updateOrderLineItemCallback != null) {
			updateOrderLineItemCallback.onUpdateOrderLineItem(result);
		}
    }

	@Override
	public void onFailUpdateOrderLineItem(Error error) {
		if (updateOrderLineItemCallback != null) {
			updateOrderLineItemCallback.onFailUpdateOrderLineItem(error);
		}
    }

	@Override
	public void onDeleteOrderLineItem(LineItem result) {
		if (deleteOrderLineItemCallback != null) {
			deleteOrderLineItemCallback.onDeleteOrderLineItem(result);
		}
    }

	@Override
	public void onFailDeleteOrderLineItem(Error error) {
		if (deleteOrderLineItemCallback != null) {
			deleteOrderLineItemCallback.onFailDeleteOrderLineItem(error);
		}
    }

	@Override
	public void onCreateOrderLineItemDiscount(Discount result) {
		if (createOrderLineItemDiscountCallback != null) {
		    createOrderLineItemDiscountCallback
		            .onCreateOrderLineItemDiscount(result);
		}
    }

	@Override
	public void onFailCreateOrderLineItemDiscount(Error error) {
		if (createOrderLineItemDiscountCallback != null) {
		    createOrderLineItemDiscountCallback
		            .onFailCreateOrderLineItemDiscount(error);
		}
    }

	@Override
	public void onDeleteOrderLineItemDiscount(Discount result) {
		if (deleteOrderLineItemDiscountCallback != null) {
		    deleteOrderLineItemDiscountCallback
		            .onDeleteOrderLineItemDiscount(result);
		}
    }

	@Override
	public void onFailDeleteOrderLineItemDiscount(Error error) {
		if (deleteOrderLineItemDiscountCallback != null) {
		    deleteOrderLineItemDiscountCallback
		            .onFailDeleteOrderLineItemDiscount(error);
		}
    }

	@Override
	public void onCreateOrderLineItemModification(Modification result) {
		if (createOrderLineItemModificationCallback != null) {
		    createOrderLineItemModificationCallback
		            .onCreateOrderLineItemModification(result);
		}
    }

	@Override
	public void onFailCreateOrderLineItemModification(Error error) {
		if (createOrderLineItemModificationCallback != null) {
		    createOrderLineItemModificationCallback
		            .onFailCreateOrderLineItemModification(error);
		}
    }

	@Override
	public void onDeleteOrderLineItemModification(Modification result) {
		if (deleteOrderLineItemModificationCallback != null) {
		    deleteOrderLineItemModificationCallback
		            .onDeleteOrderLineItemModification(result);
		}
    }

	@Override
	public void onFailDeleteOrderLineItemModification(Error error) {
		if (deleteOrderLineItemModificationCallback != null) {
		    deleteOrderLineItemModificationCallback
		            .onFailDeleteOrderLineItemModification(error);
		}
    }

	@Override
	public void onCreateOrderPayment(Payment result) {
		if (createOrderPaymentCallback != null) {
			createOrderPaymentCallback.onCreateOrderPayment(result);
		}
    }

	@Override
	public void onFailCreateOrderPayment(Error error) {
		if (createOrderPaymentCallback != null) {
			createOrderPaymentCallback.onFailCreateOrderPayment(error);
		}
    }

	@Override
	public void onCreateOrderServiceCharge(ServiceCharge result) {
		if (createOrderServiceChargeCallback != null) {
			createOrderServiceChargeCallback.onCreateOrderServiceCharge(result);
		}
    }

	@Override
	public void onFailCreateOrderServiceCharge(Error error) {
		if (createOrderServiceChargeCallback != null) {
			createOrderServiceChargeCallback.onFailCreateOrderServiceCharge(error);
		}
    }

	@Override
	public void onDeleteOrderServiceCharge(ServiceCharge result) {
		if (deleteOrderServiceChargeCallback != null) {
			deleteOrderServiceChargeCallback.onDeleteOrderServiceCharge(result);
		}
    }

	@Override
	public void onFailDeleteOrderServiceCharge(Error error) {
		if (deleteOrderServiceChargeCallback != null) {
			deleteOrderServiceChargeCallback.onFailDeleteOrderServiceCharge(error);
		}
    }

	@Override
	public void onExchangeOrderLineItem(LineItem result) {
		if (exchangeOrderLineItemCallback != null) {
			exchangeOrderLineItemCallback.onExchangeOrderLineItem(result);
		}
    }

	@Override
	public void onFailExchangeOrderLineItem(Error error) {
		if (exchangeOrderLineItemCallback != null) {
			exchangeOrderLineItemCallback.onFailExchangeOrderLineItem(error);
		}
    }

	@Override
	public void onGetDiscountSummary(DiscountSummary result) {
		if (getDiscountSummaryCallback != null) {
			getDiscountSummaryCallback.onGetDiscountSummary(result);
		}
    }

	@Override
	public void onFailGetDiscountSummary(Error error) {
		if (getDiscountSummaryCallback != null) {
			getDiscountSummaryCallback.onFailGetDiscountSummary(error);
		}
    }
	
}
