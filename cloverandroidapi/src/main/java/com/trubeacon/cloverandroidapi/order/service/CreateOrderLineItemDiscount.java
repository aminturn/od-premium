package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.Discount;

public class CreateOrderLineItemDiscount extends RESTMethod<Discount> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/line_items/{lineItemId}/discounts";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOrderLineItemDiscountCallback {
	    public void onCreateOrderLineItemDiscount(Discount result);
	    public void onFailCreateOrderLineItemDiscount(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOrderLineItemDiscount(String merchantId, String token, String orderId, String lineItemId, Discount discount) {
		super(token, new TypeReference<Discount>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.addPathParam(Param.path("lineItemId", lineItemId));
		super.setEntity(discount);
	}
	
	@Override
	public Discount execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOrderLineItemDiscountCallback callback) {
	    super.execute(new Callback<Discount>() {
	        @Override
	        public void onReceiveResult(Discount result) {
	            if (callback != null) {
	                callback.onCreateOrderLineItemDiscount(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOrderLineItemDiscount(error);
	            }
	        }
	    });
	}
	
}
