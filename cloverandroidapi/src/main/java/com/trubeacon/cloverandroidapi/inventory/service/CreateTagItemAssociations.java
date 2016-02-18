package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.TagItemAssociation;

// FIXME: what is the actual return type???
public class CreateTagItemAssociations extends RESTMethod<WrappedList<TagItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/tag_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateTagItemAssociationsCallback {
	    public void onCreateTagItemAssociations(WrappedList<TagItemAssociation> result);
	    public void onFailCreateTagItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateTagItemAssociations(String merchantId, String token, TagItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<TagItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(new WrappedList<TagItemAssociation>(associations));
	}
	
	@Override
	public WrappedList<TagItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateTagItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<TagItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<TagItemAssociation> result) {
	            if (callback != null) {
	                callback.onCreateTagItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateTagItemAssociations(error);
	            }
	        }
	    });
	}
	
}
