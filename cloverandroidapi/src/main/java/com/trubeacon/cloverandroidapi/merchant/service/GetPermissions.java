package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.merchant.Permission;

public class GetPermissions extends RESTMethod<WrappedList<Permission>> {

	private static final String URL = "/merchants/{merchantId}/permission_sets";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetPermissionsCallback {
		public void onGetPermissions(List<Permission> permissions);
		public void onFailGetPermissions(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetPermissions(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Permission>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Permission> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetPermissionsCallback callback) {
		super.execute(new Callback<WrappedList<Permission>>() {

			@Override
            public void onReceiveResult(WrappedList<Permission> result) {
	            if (callback != null) {
	            	callback.onGetPermissions(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailGetPermissions(error);
	            }
            }
			
		});
	}

}
