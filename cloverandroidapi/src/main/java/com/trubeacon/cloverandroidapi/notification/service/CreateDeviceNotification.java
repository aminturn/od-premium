package com.trubeacon.cloverandroidapi.notification.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.notification.AppNotification;

public class CreateDeviceNotification extends RESTMethod<AppNotification> {

	private static final String URL = "/app/{appId}/devices/{deviceId}/notifications";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateDeviceNotificationCallback {
		public void onCreateDeviceNotification(AppNotification result);
		public void onFailCreateDeviceNotification(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateDeviceNotification(String appId, String deviceId, String token, AppNotification notification) {
		super(token, new TypeReference<AppNotification>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("appId", appId));
		super.addPathParam(Param.path("deviceId", deviceId));
		super.setEntity(notification);
	}
	
	@Override
	public AppNotification execute() throws RESTException { 
		return super.execute();
	}
	
	public void execute(final CreateDeviceNotificationCallback callback) {
		super.execute(new Callback<AppNotification>() {

			@Override
            public void onReceiveResult(AppNotification result) {
	            if (callback != null) {
	            	callback.onCreateDeviceNotification(result);
	            }
            }

			@Override
            public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	            	callback.onFailCreateDeviceNotification(error);
	            }
            }
			
		});
	}
	
}
