package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;

public class DeleteModifierGroup extends RESTMethod<ModifierGroup> {
	
	private static final String URL = "/merchants/{merchantId}/modifier_groups/{modifierGroupId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteModifierGroupCallback {
	    public void onDeleteModifierGroup(ModifierGroup result);
	    public void onFailDeleteModifierGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteModifierGroup(String merchantId, String token, String modifierGroupId) {
		super(token, new TypeReference<ModifierGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("modifierGroupId", modifierGroupId));
	}
	
	@Override
	public ModifierGroup execute() throws RESTException {
		return super.execute();
	}

	public void execute(final DeleteModifierGroupCallback callback) {
	    super.execute(new Callback<ModifierGroup>() {
	        @Override
	        public void onReceiveResult(ModifierGroup result) {
	            if (callback != null) {
	                callback.onDeleteModifierGroup(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteModifierGroup(error);
	            }
	        }
	    });
	}

}
