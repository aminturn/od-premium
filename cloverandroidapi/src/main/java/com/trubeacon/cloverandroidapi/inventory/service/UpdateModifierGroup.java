package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;

public class UpdateModifierGroup extends RESTMethod<ModifierGroup> {

	private static final String URL = "/merchants/{merchantId}/modifier_groups/{modifierGroupId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateModifierGroupCallback {
	    public void onUpdateModifierGroup(ModifierGroup result);
	    public void onFailUpdateModifierGroup(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateModifierGroup(String merchantId, String token, String modifierGroupId, ModifierGroup modifierGroup) {
		super(token, new TypeReference<ModifierGroup>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("modifierGroupId", modifierGroupId));
		super.setEntity(modifierGroup);
	}
	
	@Override
	public ModifierGroup execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateModifierGroupCallback callback) {
	    super.execute(new Callback<ModifierGroup>() {
	        @Override
	        public void onReceiveResult(ModifierGroup result) {
	            if (callback != null) {
	                callback.onUpdateModifierGroup(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateModifierGroup(error);
	            }
	        }
	    });
	}

}
