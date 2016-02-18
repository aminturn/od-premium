package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Tag;

public class GetTags extends RESTMethod<WrappedList<Tag>> {

	private static final String URL = "/merchants/{merchantId}/tags";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTagsCallback {
	    public void onGetTags(WrappedList<Tag> result);
	    public void onFailGetTags(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTags(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Tag>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Tag> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTagsCallback callback) {
	    super.execute(new Callback<WrappedList<Tag>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Tag> result) {
	            if (callback != null) {
	                callback.onGetTags(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTags(error);
	            }
	        }
	    });
	}

}
