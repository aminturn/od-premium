package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ItemGroup;

public class CreateItemGroup extends RESTMethod<ItemGroup> {

	private static final String URL = "/merchants/{merchantId}/item_groups";
	private static final HttpMethod METHOD = HttpMethod.POST;

	public interface CreateItemGroupCallback {
	    public void onCreateItemGroup(ItemGroup result);
	    public void onFailCreateItemGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateItemGroup(String merchantId, String token, ItemGroup itemGroup) {
		super(token, new TypeReference<ItemGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(itemGroup);
	}
	
	@Override
	public ItemGroup execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateItemGroupCallback callback) {
	    super.execute(new Callback<ItemGroup>() {
	        public void onReceiveResult(ItemGroup result) {
	            if (callback != null) {
	                callback.onCreateItemGroup(result);
	            }
	        }
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateItemGroup(error);
	            }
	        }
	    });
	}

}
