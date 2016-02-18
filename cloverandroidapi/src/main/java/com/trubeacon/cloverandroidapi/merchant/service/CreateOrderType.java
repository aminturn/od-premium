package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.OrderType;

public class CreateOrderType extends RESTMethod<OrderType> {

	private static final String URL = "/merchants/{merchantId}/order_types";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOrderTypeCallback {
	    public void onCreateOrderType(OrderType result);
	    public void onFailCreateOrderType(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOrderType(String merchantId, String token, OrderType orderType) {
		super(token, new TypeReference<OrderType>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(orderType);
	}
	
	@Override
	public OrderType execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOrderTypeCallback callback) {
	    super.execute(new Callback<OrderType>() {
	        @Override
	        public void onReceiveResult(OrderType result) {
	            if (callback != null) {
	                callback.onCreateOrderType(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOrderType(error);
	            }
	        }
	    });
	}
	
}
