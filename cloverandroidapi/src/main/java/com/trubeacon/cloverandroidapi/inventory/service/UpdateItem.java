package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Item;

public class UpdateItem extends RESTMethod<Item> {

	private static final String URL = "/merchants/{merchantId}/items/{itemId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateItemCallback {
	    public void onUpdateItem(Item result);
	    public void onFailUpdateItem(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateItem(String merchantId, String token, String itemId, Item item) {
		super(token, new TypeReference<Item>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("itemId", itemId));
		super.setEntity(item);
	}
	
	@Override
	public Item execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateItemCallback callback) {
	    super.execute(new Callback<Item>() {
	        @Override
	        public void onReceiveResult(Item result) {
	            if (callback != null) {
	                callback.onUpdateItem(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateItem(error);
	            }
	        }
	    });
	}

}
