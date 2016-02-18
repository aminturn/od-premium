package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Modifier;

public class DeleteModifierGroupModifier extends RESTMethod<Modifier> {

	private static final String URL = "/merchants/{merchantId}/modifier_groups/{modifierGroupId}/modifiers/{modifierId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteModifierGroupModifierCallback {
	    public void onDeleteModifierGroupModifier(Modifier result);
	    public void onFailDeleteModifierGroupModifier(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteModifierGroupModifier(String merchantId, String token, String modifierGroupId, String modifierId) {
		super(token, new TypeReference<Modifier>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("modifierGroupId", modifierGroupId));
		super.addPathParam(Param.path("modifierId", modifierId));
	}
	
	@Override
	public Modifier execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteModifierGroupModifierCallback callback) {
	    super.execute(new Callback<Modifier>() {
	        @Override
	        public void onReceiveResult(Modifier result) {
	            if (callback != null) {
	                callback.onDeleteModifierGroupModifier(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteModifierGroupModifier(error);
	            }
	        }
	    });
	}

}
