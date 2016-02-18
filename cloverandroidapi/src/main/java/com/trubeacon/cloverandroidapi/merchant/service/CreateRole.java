package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.Role;

public class CreateRole extends RESTMethod<Role> {

	private static final String URL = "/merchants/{merchantId}/roles";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateRoleCallback {
	    public void onCreateRole(Role result);
	    public void onFailCreateRole(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateRole(String merchantId, String token, Role role) {
		super(token, new TypeReference<Role>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(role);
	}
	
	@Override
	public Role execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateRoleCallback callback) {
	    super.execute(new Callback<Role>() {
	        @Override
	        public void onReceiveResult(Role result) {
	            if (callback != null) {
	                callback.onCreateRole(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateRole(error);
	            }
	        }
	    });
	}

}
