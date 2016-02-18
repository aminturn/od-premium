package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.OptionItemAssociation;

public class DeleteOptionItemAssociations extends RESTMethod<WrappedList<OptionItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/option_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface DeleteOptionItemAssociationsCallback {
	    public void onDeleteOptionItemAssociations(WrappedList<OptionItemAssociation> result);
	    public void onFailDeleteOptionItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOptionItemAssociations(String merchantId, String token, OptionItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<OptionItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.setEntity(new WrappedList<OptionItemAssociation>(associations));
		super.addPathParam(Param.path("merchantId", merchantId));
		// why use proper HTTP verbs when you can add a retarded query param??? ugh
		super.addQueryParam(Param.query("delete", "true"));
	}
	
	@Override
	public WrappedList<OptionItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteOptionItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<OptionItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<OptionItemAssociation> result) {
	            if (callback != null) {
	                callback.onDeleteOptionItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOptionItemAssociations(error);
	            }
	        }
	    });
	}

}
