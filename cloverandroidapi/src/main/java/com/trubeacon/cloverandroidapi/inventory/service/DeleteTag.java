package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Tag;

public class DeleteTag extends RESTMethod<Tag> {

	private static final String URL = "/merchants/{merchantId}/tags/{tagId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteTagCallback {
	    public void onDeleteTag(Tag result);
	    public void onFailDeleteTag(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteTag(String merchantId, String token, String tagId) {
		super(token, new TypeReference<Tag>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("tagId", tagId));
	}
	
	@Override
	public Tag execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteTagCallback callback) {
	    super.execute(new Callback<Tag>() {
	        @Override
	        public void onReceiveResult(Tag result) {
	            if (callback != null) {
	                callback.onDeleteTag(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteTag(error);
	            }
	        }
	    });
	}

}
