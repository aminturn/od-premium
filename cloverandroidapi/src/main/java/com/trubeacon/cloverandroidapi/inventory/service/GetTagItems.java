package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Item;

public class GetTagItems extends RESTMethod<WrappedList<Item>> {

	private static final String URL = "/merchants/{merchantId}/tags/{tagId}/items";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTagItemsCallback {
	    public void onGetTagItems(WrappedList<Item> result);
	    public void onFailGetTagItems(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTagItems(String merchantId, String token, String tagId, Object... params) {
		super(token, new TypeReference<WrappedList<Item>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("tagId", tagId));
	}
	
	@Override
	public WrappedList<Item> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTagItemsCallback callback) {
	    super.execute(new Callback<WrappedList<Item>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Item> result) {
	            if (callback != null) {
	                callback.onGetTagItems(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTagItems(error);
	            }
	        }
	    });
	}

}
