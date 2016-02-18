package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.Version;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.merchant.Devices;

public class GetDevices extends RESTMethod<Devices> {

	private static final String URL = "/merchant/{merchantId}/devices";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetDevicesCallback {
		public void onGetDevices(Devices devices);
		public void onFailGetDevices(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetDevices(String mId, String token) {
		super(token, new TypeReference<Devices>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
		super.addPathParam(Param.path("merchantId", mId));
	}
	
	@Override
	public Devices execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetDevicesCallback callback) {
		super.execute(new Callback<Devices>() {

			@Override
            public void onReceiveResult(Devices result) {
	            if (callback != null) {
	            	callback.onGetDevices(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailGetDevices(error);
	            }
            }
			
		});
	}
	
}
