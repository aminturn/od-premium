package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Item;

public class GetItem extends RESTMethod<Item> {

	private static final String URL = "/merchants/{merchantId}/items/{itemId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetItemCallback {
	    public void onGetItem(Item result);
	    public void onFailGetItem(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetItem(String merchantId, String token, String itemId) {
		super(token, new TypeReference<Item>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("itemId", itemId));
	}
	
	@Override
	public Item execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetItemCallback callback) {
	    super.execute(new Callback<Item>() {
	        @Override
	        public void onReceiveResult(Item result) {
	            if (callback != null) {
	                callback.onGetItem(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetItem(error);
	            }
	        }
	    });
	}

}
