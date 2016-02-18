package com.trubeacon.cloverandroidapi.order.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.order.Discount;

public class GetOrderDiscounts extends RESTMethod<WrappedList<Discount>> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/discounts";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetOrderDiscountsCallback {
	    public void onGetOrderDiscounts(WrappedList<Discount> result);
	    public void onFailGetOrderDiscounts(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetOrderDiscounts(String merchantId, String token, String orderId, Object... params) {
		super(token, new TypeReference<WrappedList<Discount>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
	}
	
	@Override
	public WrappedList<Discount> execute() throws RESTException {
		return super.execute();
	}

	public void execute(final GetOrderDiscountsCallback callback) {
	    super.execute(new Callback<WrappedList<Discount>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Discount> result) {
	            if (callback != null) {
	                callback.onGetOrderDiscounts(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetOrderDiscounts(error);
	            }
	        }
	    });
	}

}
