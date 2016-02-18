package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;

public class CreateModifierGroup extends RESTMethod<ModifierGroup> {

	private static final String URL = "/merchants/{merchantId}/modifier_groups";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateModifierGroupCallback {
	    public void onCreateModifierGroup(ModifierGroup result);
	    public void onFailCreateModifierGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public CreateModifierGroup(String merchantId, String token, ModifierGroup modifierGroup) {
		super(token, new TypeReference<ModifierGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(modifierGroup);
	}
	
	@Override
	public ModifierGroup execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateModifierGroupCallback callback) {
	    super.execute(new Callback<ModifierGroup>() {
	        public void onReceiveResult(ModifierGroup result) {
	            if (callback != null) {
	                callback.onCreateModifierGroup(result);
	            }
	        }
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateModifierGroup(error);
	            }
	        }
	    });
	}
	
}
