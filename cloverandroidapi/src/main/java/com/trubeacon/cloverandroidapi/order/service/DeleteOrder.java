package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.Order;

public class DeleteOrder extends RESTMethod<Order> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface DeleteOrderCallback {
	    public void onDeleteOrder(Order result);
	    public void onFailDeleteOrder(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOrder(String merchantId, String token, String orderId) {
		super(token, new TypeReference<Order>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
	}
	
	@Override
	public Order execute() throws RESTException {
		return super.execute();
	}

	public void execute(final DeleteOrderCallback callback) {
	    super.execute(new Callback<Order>() {
	        @Override
	        public void onReceiveResult(Order result) {
	            if (callback != null) {
	                callback.onDeleteOrder(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOrder(error);
	            }
	        }
	    });
	}

}
