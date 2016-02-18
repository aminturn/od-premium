package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.ItemTags;
import com.trubeacon.cloverandroidapi.inventory.Tag;

public class GetItemTags extends RESTMethod<WrappedList<Tag>> {

	private static final String URL = "/merchants/{merchantId}/items/{itemId}/tags";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetItemTagsCallback {
	    public void onGetItemTags(WrappedList<Tag> result);
	    public void onFailGetItemTags(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetItemTags(String merchantId, String token, String itemId, Object... params) {
		super(token, new TypeReference<WrappedList<Tag>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("itemId", itemId));
	}
	
	@Override
	public WrappedList<Tag> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetItemTagsCallback callback) {
	    super.execute(new Callback<WrappedList<Tag>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Tag> result) {
	            if (callback != null) {
	                callback.onGetItemTags(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetItemTags(error);
	            }
	        }
	    });
	}

}
