package com.trubeacon.cloverandroidapi.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.app.AppMeteredEvent;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;

public class CreateMeteredEvent extends RESTMethod<AppMeteredEvent> {

	private static final String URL = "/apps/{appId}/merchants/{merchantId}/metereds/{meteredId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateMeteredEventCallback {
		public void onCreateMeteredEvent(AppMeteredEvent event);
		public void onFailCreateMeteredEvent(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateMeteredEvent(String merchantId, String token, String appId, String meteredId, AppMeteredEvent event) {
		super(token, new TypeReference<AppMeteredEvent>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("appId", appId));
		super.addPathParam(Param.path("meteredId", meteredId));
		super.setEntity(event);
	}

	public void execute(final CreateMeteredEventCallback callback) {
		super.execute(new Callback<AppMeteredEvent>() {

			@Override
            public void onReceiveResult(AppMeteredEvent result) {
	            if (callback != null) {
	            	callback.onCreateMeteredEvent(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailCreateMeteredEvent(error);
	            }
            }
			
		});
	}
	
	@Override
	public AppMeteredEvent execute() throws RESTException {
		return super.execute();
	}
	
}
