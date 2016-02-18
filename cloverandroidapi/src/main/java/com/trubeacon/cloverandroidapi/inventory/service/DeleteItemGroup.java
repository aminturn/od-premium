package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ItemGroup;

public class DeleteItemGroup extends RESTMethod<ItemGroup> {

	private static final String URL = "/merchants/{merchantId}/item_groups/{itemGroupId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteItemGroupCallback {
	    public void onDeleteItemGroup(ItemGroup result);
	    public void onFailDeleteItemGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteItemGroup(String merchantId, String token, String itemGroupId) {
		super(token, new TypeReference<ItemGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("itemGroupId", itemGroupId));
	}
	
	@Override
	public ItemGroup execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteItemGroupCallback callback) {
	    super.execute(new Callback<ItemGroup>() {
	        @Override
	        public void onReceiveResult(ItemGroup result) {
	            if (callback != null) {
	                callback.onDeleteItemGroup(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteItemGroup(error);
	            }
	        }
	    });
	}

}
