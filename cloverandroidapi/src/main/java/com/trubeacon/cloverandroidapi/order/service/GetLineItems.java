package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.order.LineItem;

public class GetLineItems extends RESTMethod<WrappedList<LineItem>> {

	private static final String URL = "/merchants/{merchantId}/line_items";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetLineItemsCallback {
	    public void onGetLineItems(WrappedList<LineItem> result);
	    public void onFailGetLineItems(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetLineItems(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<LineItem>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<LineItem> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetLineItemsCallback callback) {
	    super.execute(new Callback<WrappedList<LineItem>>() {
	        @Override
	        public void onReceiveResult(WrappedList<LineItem> result) {
	            if (callback != null) {
	                callback.onGetLineItems(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetLineItems(error);
	            }
	        }
	    });
	}

}
