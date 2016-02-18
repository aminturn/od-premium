package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Modifier;

public class GetModifierGroupModifiers extends RESTMethod<WrappedList<Modifier>> {

	private static final String URL = "/merchants/{merchantId}/modifier_groups/{modifierGroupId}/modifiers";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetModifierGroupModifiersCallback {
	    public void onGetModifierGroupModifiers(WrappedList<Modifier> result);
	    public void onFailGetModifierGroupModifiers(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetModifierGroupModifiers(String merchantId, String token, String modifierGroupId, Object... params) {
		super(token, new TypeReference<WrappedList<Modifier>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("modifierGroupId", modifierGroupId));
	}
	
	@Override
	public WrappedList<Modifier> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetModifierGroupModifiersCallback callback) {
	    super.execute(new Callback<WrappedList<Modifier>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Modifier> result) {
	            if (callback != null) {
	                callback.onGetModifierGroupModifiers(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetModifierGroupModifiers(error);
	            }
	        }
	    });
	}

}
