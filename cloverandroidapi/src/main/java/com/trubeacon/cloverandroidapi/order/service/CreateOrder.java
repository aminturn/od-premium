package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.Order;

public class CreateOrder extends RESTMethod<Order> {

	private static final String URL = "/merchants/{merchantId}/orders";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOrderCallback {
	    public void onCreateOrder(Order result);
	    public void onFailCreateOrder(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOrder(String merchantId, String token, Order order) {
		super(token, new TypeReference<Order>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(order);
	}
	
	@Override
	public Order execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOrderCallback callback) {
	    super.execute(new Callback<Order>() {
	        @Override
	        public void onReceiveResult(Order result) {
	            if (callback != null) {
	                callback.onCreateOrder(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOrder(error);
	            }
	        }
	    });
	}

}
