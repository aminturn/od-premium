package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.Role;

public class DeleteRole extends RESTMethod<Role> {

	private static final String URL = "/merchants/{merchantId}/roles/{roleId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteRoleCallback {
	    public void onDeleteRole(Role result);
	    public void onFailDeleteRole(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteRole(String merchantId, String token, String roleId) {
		super(token, new TypeReference<Role>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("roleId", roleId));
	}
	
	@Override
	public Role execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteRoleCallback callback) {
	    super.execute(new Callback<Role>() {
	        @Override
	        public void onReceiveResult(Role result) {
	            if (callback != null) {
	                callback.onDeleteRole(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteRole(error);
	            }
	        }
	    });
	}

}
