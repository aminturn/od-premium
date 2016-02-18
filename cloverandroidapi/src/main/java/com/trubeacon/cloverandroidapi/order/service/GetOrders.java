package com.trubeacon.cloverandroidapi.order.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.order.Order;

public class GetOrders extends RESTMethod<WrappedList<Order>> {

	private static final String URL = "/merchants/{merchantId}/orders";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetOrdersCallback {
	    public void onGetOrders(WrappedList<Order> result);
	    public void onFailGetOrders(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetOrders(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Order>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Order> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetOrdersCallback callback) {
	    super.execute(new Callback<WrappedList<Order>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Order> result) {
	            if (callback != null) {
	                callback.onGetOrders(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetOrders(error);
	            }
	        }
	    });
	}

}
