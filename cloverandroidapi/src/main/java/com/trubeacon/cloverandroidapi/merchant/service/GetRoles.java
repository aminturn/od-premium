package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.Role;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class GetRoles extends RESTMethod<WrappedList<Role>> {

	private static final String URL = "/merchants/{merchantId}/roles";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetRolesCallback {
	    public void onGetRoles(WrappedList<Role> result);
	    public void onFailGetRoles(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetRoles(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Role>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Role> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetRolesCallback callback) {
	    super.execute(new Callback<WrappedList<Role>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Role> result) {
	            if (callback != null) {
	                callback.onGetRoles(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetRoles(error);
	            }
	        }
	    });
	}

}
