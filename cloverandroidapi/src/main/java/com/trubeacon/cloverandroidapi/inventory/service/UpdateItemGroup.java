package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ItemGroup;

public class UpdateItemGroup extends RESTMethod<ItemGroup> {

	private static final String URL = "/merchants/{merchantId}/item_groups/{itemGroupId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateItemGroupCallback {
	    public void onUpdateItemGroup(ItemGroup result);
	    public void onFailUpdateItemGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateItemGroup(String merchantId, String token, String itemGroupId, ItemGroup itemGroup) {
		super(token, new TypeReference<ItemGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("itemGroupId", itemGroupId));
		super.setEntity(itemGroup);
	}
	
	@Override
	public ItemGroup execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateItemGroupCallback callback) {
	    super.execute(new Callback<ItemGroup>() {
	        @Override
	        public void onReceiveResult(ItemGroup result) {
	            if (callback != null) {
	                callback.onUpdateItemGroup(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateItemGroup(error);
	            }
	        }
	    });
	}

}
