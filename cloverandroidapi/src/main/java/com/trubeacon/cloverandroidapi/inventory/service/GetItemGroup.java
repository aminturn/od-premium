package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ItemGroup;

public class GetItemGroup extends RESTMethod<ItemGroup> {
	
	private static final String URL = "/merchants/{merchantId}/item_groups/{itemGroupId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetItemGroupCallback {
	    public void onGetItemGroup(ItemGroup result);
	    public void onFailGetItemGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetItemGroup(String merchantId, String token, String itemGroupId) {
		super(token, new TypeReference<ItemGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("itemGroupId", itemGroupId));
	}
	
	@Override
	public ItemGroup execute() throws RESTException {
		return super.execute();
	}

	public void execute(final GetItemGroupCallback callback) {
	    super.execute(new Callback<ItemGroup>() {
	        @Override
	        public void onReceiveResult(ItemGroup result) {
	            if (callback != null) {
	                callback.onGetItemGroup(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetItemGroup(error);
	            }
	        }
	    });
	}

}
