package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Tag;

public class UpdateTag extends RESTMethod<Tag> {

	private static final String URL = "/merchants/{merchantId}/tags/{tagId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateTagCallback {
	    public void onUpdateTag(Tag result);
	    public void onFailUpdateTag(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateTag(String merchantId, String token, String tagId, Tag tag) {
		super(token, new TypeReference<Tag>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("tagId", tagId));
		super.setEntity(tag);
	}
	
	@Override
	public Tag execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateTagCallback callback) {
	    super.execute(new Callback<Tag>() {
	        @Override
	        public void onReceiveResult(Tag result) {
	            if (callback != null) {
	                callback.onUpdateTag(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateTag(error);
	            }
	        }
	    });
	}

}
