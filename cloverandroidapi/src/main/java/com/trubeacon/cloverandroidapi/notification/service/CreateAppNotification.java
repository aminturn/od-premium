package com.trubeacon.cloverandroidapi.notification.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.notification.AppNotification;
import com.trubeacon.cloverandroidapi.client.error.Error;

public class CreateAppNotification extends RESTMethod<AppNotification> {

	private static final String URL = "/app/{appId}/merchants/{merchantId}/notifications";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateAppNotificationCallback {
		public void onCreateAppNotification(AppNotification result);
		public void onFailCreateAppNotification(Error error);
	}
	
	public CreateAppNotification(String appId, String merchantId, String token, AppNotification notification) {
		super(token, new TypeReference<AppNotification>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("appId", appId));
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(notification);
	}
	
	@Override
	public AppNotification execute() throws RESTException { 
		return super.execute();
	}
	
	
	public void execute(final CreateAppNotificationCallback callback) {
		super.execute(new Callback<AppNotification>() {

			@Override
            public void onReceiveResult(AppNotification result) {
	            if (callback != null) {
	            	callback.onCreateAppNotification(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailCreateAppNotification(error);
	            }
            }
			
		});
	}
	
}
