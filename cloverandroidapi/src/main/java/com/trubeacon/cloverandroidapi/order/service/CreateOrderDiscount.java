package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.Discount;

public class CreateOrderDiscount extends RESTMethod<Discount> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/discounts";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOrderDiscountCallback {
	    public void onCreateOrderDiscount(Discount result);
	    public void onFailCreateOrderDiscount(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOrderDiscount(String merchantId, String token, String orderId, Discount discount) {
		super(token, new TypeReference<Discount>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.setEntity(discount);
	}
	
	@Override
	public Discount execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOrderDiscountCallback callback) {
	    super.execute(new Callback<Discount>() {
	        @Override
	        public void onReceiveResult(Discount result) {
	            if (callback != null) {
	                callback.onCreateOrderDiscount(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOrderDiscount(error);
	            }
	        }
	    });
	}
	
}
