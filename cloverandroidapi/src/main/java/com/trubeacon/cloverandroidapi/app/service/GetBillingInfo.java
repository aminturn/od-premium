package com.trubeacon.cloverandroidapi.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.app.AppBillingInfo;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;

public class GetBillingInfo extends RESTMethod<AppBillingInfo> {

	private static final String URL = "/apps/{appId}/merchants/{merchantId}/billing_info";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetBillingInfoCallback {
		public void onGetBillingInfo(AppBillingInfo billingInfo);
		public void onFailGetBillingInfo(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetBillingInfo(String merchantId, String token, String appId) {
		super(token, new TypeReference<AppBillingInfo>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("appId", appId));
	}

	public void execute(final GetBillingInfoCallback callback) {
		super.execute(new Callback<AppBillingInfo>() {

			@Override
            public void onReceiveResult(AppBillingInfo result) {
	            if (callback != null) {
	            	callback.onGetBillingInfo(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailGetBillingInfo(error);
	            }
            }
			
		});
	}
	
	@Override
	public AppBillingInfo execute() throws RESTException {
		return super.execute();
	}
	
}
