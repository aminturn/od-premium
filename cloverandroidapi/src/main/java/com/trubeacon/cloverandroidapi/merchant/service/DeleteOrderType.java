package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.OrderType;

public class DeleteOrderType extends RESTMethod<OrderType> {
	
	private static final String URL = "/merchants/{merchantId}/order_types/{orderTypeId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteOrderTypeCallback {
	    public void onDeleteOrderType(OrderType result);
	    public void onFailDeleteOrderType(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOrderType(String merchantId, String token, String orderTypeId) {
		super(token, new TypeReference<OrderType>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderTypeId", orderTypeId));
	}
	
	@Override
	public OrderType execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteOrderTypeCallback callback) {
	    super.execute(new Callback<OrderType>() {
	        @Override
	        public void onReceiveResult(OrderType result) {
	            if (callback != null) {
	                callback.onDeleteOrderType(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOrderType(error);
	            }
	        }
	    });
	}

}
