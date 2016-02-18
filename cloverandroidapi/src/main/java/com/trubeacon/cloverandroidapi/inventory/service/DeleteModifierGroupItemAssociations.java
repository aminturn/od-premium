package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroupItemAssociation;

public class DeleteModifierGroupItemAssociations extends RESTMethod<WrappedList<ModifierGroupItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/item_modifier_groups";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface DeleteModifierGroupItemAssociationsCallback {
	    public void onDeleteModifierGroupItemAssociations(WrappedList<ModifierGroupItemAssociation> result);
	    public void onFailDeleteModifierGroupItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteModifierGroupItemAssociations(String merchantId, String token, ModifierGroupItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<ModifierGroupItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.setEntity(new WrappedList<ModifierGroupItemAssociation>(associations));
		super.addPathParam(Param.path("merchantId", merchantId));
		// why use proper HTTP verbs when you can add a retarded query param??? ugh
		super.addQueryParam(Param.query("delete", "true"));
	}
	
	@Override
	public WrappedList<ModifierGroupItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteModifierGroupItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<ModifierGroupItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<ModifierGroupItemAssociation> result) {
	            if (callback != null) {
	                callback.onDeleteModifierGroupItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteModifierGroupItemAssociations(error);
	            }
	        }
	    });
	}

}
