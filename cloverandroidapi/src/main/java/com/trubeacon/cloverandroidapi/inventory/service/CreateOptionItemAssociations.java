package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.OptionItemAssociation;

public class CreateOptionItemAssociations extends RESTMethod<WrappedList<OptionItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/option_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOptionItemAssociationsCallback {
	    public void onCreateOptionItemAssociations(WrappedList<OptionItemAssociation> result);
	    public void onFailCreateOptionItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOptionItemAssociations(String merchantId, String token, OptionItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<OptionItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(new WrappedList<OptionItemAssociation>(associations));
	}
	
	@Override
	public WrappedList<OptionItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOptionItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<OptionItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<OptionItemAssociation> result) {
	            if (callback != null) {
	                callback.onCreateOptionItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOptionItemAssociations(error);
	            }
	        }
	    });
	}
	
}
