package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Modifier;

public class CreateModifierGroupModifier extends RESTMethod<Modifier> {

	private static final String URL = "/merchants/{merchantId}/modifier_groups/{modifierGroupId}/modifiers";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateModifierGroupModifierCallback {
	    public void onCreateModifierGroupModifier(Modifier result);
	    public void onFailCreateModifierGroupModifier(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateModifierGroupModifier(String merchantId, String token, String modifierGroupId, Modifier modifier) {
		super(token, new TypeReference<Modifier>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("modifierGroupId", modifierGroupId));
		super.setEntity(modifier);
	}
	
	@Override
	public Modifier execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateModifierGroupModifierCallback callback) {
	    super.execute(new Callback<Modifier>() {
	    	@Override
	        public void onReceiveResult(Modifier result) {
	            if (callback != null) {
	                callback.onCreateModifierGroupModifier(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateModifierGroupModifier(error);
	            }
	        }
	    });
	}

}
