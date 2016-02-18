package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroupItemAssociation;

public class CreateModifierGroupItemAssociations extends RESTMethod<WrappedList<ModifierGroupItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/item_modifier_groups";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateModifierGroupItemAssociationsCallback {
	    public void onCreateModifierGroupItemAssociations(WrappedList<ModifierGroupItemAssociation> result);
	    public void onFailCreateModifierGroupItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateModifierGroupItemAssociations(String merchantId, String token, ModifierGroupItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<ModifierGroupItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(new WrappedList<ModifierGroupItemAssociation>(associations));
	}
	
	@Override
	public WrappedList<ModifierGroupItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateModifierGroupItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<ModifierGroupItemAssociation>>() {
	        public void onReceiveResult(WrappedList<ModifierGroupItemAssociation> result) {
	            if (callback != null) {
	                callback.onCreateModifierGroupItemAssociations(result);
	            }
	        }
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateModifierGroupItemAssociations(error);
	            }
	        }
	    });
	}
	
}
