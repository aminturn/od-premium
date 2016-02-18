package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.LineItem;

public class ExchangeOrderLineItem extends RESTMethod<LineItem> {

	private static final String URL = "/merchants/{merchantId}/order/{orderId}/line_items/{oldLineItemId}/exchange/{newLineItemId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface ExchangeOrderLineItemCallback {
		public void onExchangeOrderLineItem(LineItem result);
		public void onFailExchangeOrderLineItem(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public ExchangeOrderLineItem(String merchantId, String token, String orderId, String oldLineItemId, String newLineItemId) {
		super(token, new TypeReference<LineItem>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.addPathParam(Param.path("oldLineItemId", oldLineItemId));
		super.addPathParam(Param.path("newLineItemId", newLineItemId));
	}
	
	@Override
	public LineItem execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final ExchangeOrderLineItemCallback callback) {
		super.execute(new Callback<LineItem>() {

			@Override
            public void onReceiveResult(LineItem result) {
	            if (callback != null) {
	            	callback.onExchangeOrderLineItem(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailExchangeOrderLineItem(error);
	            }
            }
			
		});
	}
	
}
