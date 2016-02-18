package com.trubeacon.cloverandroidapi.order.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.order.LineItem;

public class GetOrderLineItems extends RESTMethod<WrappedList<LineItem>> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/line_items";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetOrderLineItemsCallback {
	    public void onGetOrderLineItems(WrappedList<LineItem> result);
	    public void onFailGetOrderLineItems(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetOrderLineItems(String merchantId, String token, String orderId, Object... params) {
		super(token, new TypeReference<WrappedList<LineItem>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
	}
	
	@Override
	public WrappedList<LineItem> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetOrderLineItemsCallback callback) {
	    super.execute(new Callback<WrappedList<LineItem>>() {
	        @Override
	        public void onReceiveResult(WrappedList<LineItem> result) {
	            if (callback != null) {
	                callback.onGetOrderLineItems(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetOrderLineItems(error);
	            }
	        }
	    });
	}

}
