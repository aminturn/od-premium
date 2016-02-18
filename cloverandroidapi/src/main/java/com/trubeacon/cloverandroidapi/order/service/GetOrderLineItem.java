package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.LineItem;

public class GetOrderLineItem extends RESTMethod<LineItem> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/line_items/{lineItemId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetOrderLineItemCallback {
	    public void onGetOrderLineItem(LineItem result);
	    public void onFailGetOrderLineItem(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetOrderLineItem(String merchantId, String token, String orderId, String lineItemId) {
		super(token, new TypeReference<LineItem>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.addPathParam(Param.path("lineItemId", lineItemId));
	}
	
	@Override
	public LineItem execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetOrderLineItemCallback callback) {
	    super.execute(new Callback<LineItem>() {
	        @Override
	        public void onReceiveResult(LineItem result) {
	            if (callback != null) {
	                callback.onGetOrderLineItem(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetOrderLineItem(error);
	            }
	        }
	    });
	}

}
