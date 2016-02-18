package com.trubeacon.cloverandroidapi.order.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
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

public interface OrderService {
	
	public List<Order> getOrders(String mId, String token, Object... params) throws RESTException;
	public void getOrders(String mId, String token, GetOrdersCallback callback, Object... params);
	public Order createOrder(String mId, String token, Order order) throws RESTException;
	public void createOrder(String mId, String token, Order order, CreateOrderCallback callback);
	public Order getOrder(String mId, String token, String orderId) throws RESTException;
	public void getOrder(String mId, String token, String orderId, GetOrderCallback callback);
	public Order updateOrder(String mId, String token, String orderId, Order update) throws RESTException;
	public void updateOrder(String mId, String token, String orderId, Order update, UpdateOrderCallback callback);
	public Order deleteOrder(String mId, String token, String orderId) throws RESTException;
	public void deleteOrder(String mId, String token, String orderId, DeleteOrderCallback callback);
	
	public List<Discount> getOrderDiscounts(String mId, String token, String orderId, Object... params) throws RESTException;
	public void getOrderDiscounts(String mId, String token, String orderId, GetOrderDiscountsCallback callback, Object... params);
	public Discount createOrderDiscount(String mId, String token, String orderId, Discount discount) throws RESTException;
	public void createOrderDiscount(String mId, String token, String orderId, Discount discount, CreateOrderDiscountCallback callback);
	public Discount deleteOrderDiscount(String mId, String token, String orderId, String discountId) throws RESTException;
	public void deleteOrderDiscount(String mId, String token, String orderId, String discountId, DeleteOrderDiscountCallback callback);
	
	public List<LineItem> getLineItems(String mId, String token, Object... params) throws RESTException;
	public void getLineItems(String mId, String token, GetLineItemsCallback callback, Object... params);
	public LineItem getLineItem(String mId, String token, String lineItemId) throws RESTException;
	public void getLineItem(String mId, String token, String lineItemId, GetLineItemCallback callback);
	
	public List<LineItem> getOrderLineItems(String mId, String token, String orderId, Object... params) throws RESTException;
	public void getOrderLineItems(String mId, String token, String orderId, GetOrderLineItemsCallback callback, Object... params);
	public LineItem createOrderLineItem(String mId, String token, String orderId, LineItem lineItem) throws RESTException;
	public void createOrderLineItem(String mId, String token, String orderId, LineItem lineItem, CreateOrderLineItemCallback callback);
	public LineItem getOrderLineItem(String mId, String token, String orderId, String lineItemId) throws RESTException;
	public void getOrderLineItem(String mId, String token, String orderId, String lineItemId, GetOrderLineItemCallback callback);
	public LineItem updateOrderLineItem(String mId, String token, String orderId, String lineItemId, LineItem update) throws RESTException;
	public void updateOrderLineItem(String mId, String token, String orderId, String lineItemId, LineItem update, UpdateOrderLineItemCallback callback);
	public LineItem deleteOrderLineItem(String mId, String token, String orderId, String lineItemId) throws RESTException;
	public void deleteOrderLineItem(String mId, String token, String orderId, String lineItemId, DeleteOrderLineItemCallback callback);
	
	public Discount createOrderLineItemDiscount(String mid, String token, String orderId, String lineItemId, Discount discount) throws RESTException;
	public void createOrderLineItemDiscount(String mId, String token, String orderid, String lineItemId, Discount discount, CreateOrderLineItemDiscountCallback callback);
	public Discount deleteOrderLineItemDiscount(String mId, String token, String orderId, String lineItemId, String discountId) throws RESTException;
	public void deleteOrderLineItemDiscount(String mId, String token, String orderId, String lineItemId, String discountId, DeleteOrderLineItemDiscountCallback callback);
	
	public Modification createOrderLineItemModification(String mId, String token, String orderId, String lineItemId, Modification modification) throws RESTException;
	public void createOrderLineItemModification(String mId, String token, String orderId, String lineItemId, Modification modification, CreateOrderLineItemModificationCallback callback);
	public Modification deleteOrderLineItemModification(String mId, String token, String orderId, String lineItemId, String modificationId) throws RESTException;
	public void deleteOrderLineItemModification(String mId, String token, String orderId, String lineItemId, String modificationId, DeleteOrderLineItemModificationCallback callback);
	
	public Payment createOrderPayment(String mId, String token, String orderId, Payment payment) throws RESTException;
	public void createOrderPayment(String mId, String token, String orderId, Payment payment, CreateOrderPaymentCallback callback);
	
	public ServiceCharge createOrderServiceCharge(String mId, String token, String orderId, ServiceCharge serviceCharge) throws RESTException;
	public void createOrderServiceCharge(String mId, String token, String orderId, ServiceCharge serviceCharge, CreateOrderServiceChargeCallback callback);
	public ServiceCharge deleteOrderServiceCharge(String mId, String token, String orderId, String serviceChargeId) throws RESTException;
	public void deleteOrderServiceCharge(String mId, String token, String orderId, String serviceChargeId, DeleteOrderServiceChargeCallback callback);
	public LineItem exchangeOrderLineItem(String mId, String token, String orderId, String oldLineItemId, String lineItemId) throws RESTException;
	public void exchangeOrderLineItem(String mId, String token, String orderId, String oldLineItemId, String lineItemId, ExchangeOrderLineItemCallback callback);
	
	public DiscountSummary getDiscountSummary(String mId, String token, Object... params) throws RESTException;
	public void getDiscountSummary(String mId, String token, GetDiscountSummaryCallback callback, Object... params);
	
}
