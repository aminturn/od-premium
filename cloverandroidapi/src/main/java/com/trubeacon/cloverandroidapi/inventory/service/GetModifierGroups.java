package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;

public class GetModifierGroups extends RESTMethod<WrappedList<ModifierGroup>> {

	private static final String URL = "/merchants/{merchantId}/modifier_groups";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetModifierGroupsCallback {
	    public void onGetModifierGroups(WrappedList<ModifierGroup> result);
	    public void onFailGetModifierGroups(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetModifierGroups(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<ModifierGroup>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<ModifierGroup> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetModifierGroupsCallback callback) {
	    super.execute(new Callback<WrappedList<ModifierGroup>>() {
	        @Override
	        public void onReceiveResult(WrappedList<ModifierGroup> result) {
	            if (callback != null) {
	                callback.onGetModifierGroups(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetModifierGroups(error);
	            }
	        }
	    });
	}

}
