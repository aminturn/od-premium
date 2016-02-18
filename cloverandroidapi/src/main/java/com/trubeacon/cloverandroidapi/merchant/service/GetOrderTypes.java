package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.order.OrderType;

public class GetOrderTypes extends RESTMethod<WrappedList<OrderType>> {

	private static final String URL = "/merchants/{merchantId}/order_types";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetOrderTypesCallback {
	    public void onGetOrderTypes(WrappedList<OrderType> result);
	    public void onFailGetOrderTypes(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetOrderTypes(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<OrderType>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<OrderType> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetOrderTypesCallback callback) {
	    super.execute(new Callback<WrappedList<OrderType>>() {
	        @Override
	        public void onReceiveResult(WrappedList<OrderType> result) {
	            if (callback != null) {
	                callback.onGetOrderTypes(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetOrderTypes(error);
	            }
	        }
	    });
	}

}
