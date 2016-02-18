package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.LineItem;

public class GetLineItem extends RESTMethod<LineItem> {

	private static final String URL = "/merchants/{merchantId}/line_items/{lineItemId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetLineItemCallback {
	    public void onGetLineItem(LineItem result);
	    public void onFailGetLineItem(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetLineItem(String merchantId, String token, String lineItemId) {
		super(token, new TypeReference<LineItem>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("lineItemId", lineItemId));
	}
	
	@Override
	public LineItem execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetLineItemCallback callback) {
	    super.execute(new Callback<LineItem>() {
	        @Override
	        public void onReceiveResult(LineItem result) {
	            if (callback != null) {
	                callback.onGetLineItem(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetLineItem(error);
	            }
	        }
	    });
	}

}
