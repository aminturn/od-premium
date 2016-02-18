package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.LineItem;

public class CreateOrderLineItem extends RESTMethod<LineItem> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/line_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOrderLineItemCallback {
	    public void onCreateOrderLineItem(LineItem result);
	    public void onFailCreateOrderLineItem(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOrderLineItem(String merchantId, String token, String orderId, LineItem lineItem) {
		super(token, new TypeReference<LineItem>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.setEntity(lineItem);
	}
	
	@Override
	public LineItem execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOrderLineItemCallback callback) {
	    super.execute(new Callback<LineItem>() {
	        @Override
	        public void onReceiveResult(LineItem result) {
	            if (callback != null) {
	                callback.onCreateOrderLineItem(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOrderLineItem(error);
	            }
	        }
	    });
	}
	
}
