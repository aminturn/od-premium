package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;

public class GetModifierGroup extends RESTMethod<ModifierGroup> {

	private static final String URL = "/merchants/{merchantId}/modifier_groups/{modifierGroupId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetModifierGroupCallback {
	    public void onGetModifierGroup(ModifierGroup result);
	    public void onFailGetModifierGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetModifierGroup(String merchantId, String token, String modifierGroupId) {
		super(token, new TypeReference<ModifierGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("modifierGroupId", modifierGroupId));
	}
	
	@Override
	public ModifierGroup execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetModifierGroupCallback callback) {
	    super.execute(new Callback<ModifierGroup>() {
	        @Override
	        public void onReceiveResult(ModifierGroup result) {
	            if (callback != null) {
	                callback.onGetModifierGroup(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetModifierGroup(error);
	            }
	        }
	    });
	}

}
