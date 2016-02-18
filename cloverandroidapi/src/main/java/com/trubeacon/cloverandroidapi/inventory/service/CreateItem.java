package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Item;

public class CreateItem extends RESTMethod<Item> {

	private static final String URL = "/merchants/{merchantId}/items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateItemCallback {
		public void onCreateItem(Item item);
		public void onFailCreateItem(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateItem(String merchantId, String token, Item item) {
		super(token, new TypeReference<Item>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(item);
	}
	
	@Override
	public Item execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateItemCallback callback) {
		super.execute(new Callback<Item>() {

			@Override
            public void onReceiveResult(Item result) {
	            if (callback != null) {
	            	callback.onCreateItem(result);
	            }
            }

			@Override
            public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	            	callback.onFailCreateItem(error);
	            }
            }
			
		});
	}
	
}
