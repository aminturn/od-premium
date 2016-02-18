package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.ItemGroup;

public class GetItemGroups extends RESTMethod<WrappedList<ItemGroup>> {

	private static final String URL = "/merchants/{merchantId}/item_groups";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetItemGroupsCallback {
	    public void onGetItemGroups(WrappedList<ItemGroup> result);
	    public void onFailGetItemGroups(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetItemGroups(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<ItemGroup>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<ItemGroup> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetItemGroupsCallback callback) {
	    super.execute(new Callback<WrappedList<ItemGroup>>() {
	        @Override
	        public void onReceiveResult(WrappedList<ItemGroup> result) {
	            if (callback != null) {
	                callback.onGetItemGroups(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetItemGroups(error);
	            }
	        }
	    });
	}

}
