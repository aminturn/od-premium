package com.trubeacon.cloverandroidapi.notification.service;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.notification.AppNotification;
import com.trubeacon.cloverandroidapi.notification.service.CreateAppNotification.CreateAppNotificationCallback;
import com.trubeacon.cloverandroidapi.notification.service.CreateDeviceNotification.CreateDeviceNotificationCallback;

public class NotificationServiceImpl implements NotificationService,
												CreateAppNotificationCallback,
												CreateDeviceNotificationCallback {

	private CreateAppNotificationCallback createAppNotificationCallback;
	private CreateDeviceNotificationCallback createDeviceNotificationCallback;
	
	@Override
    public AppNotification createAppNotification(String appId, String mId, String token, AppNotification notification) throws RESTException {
	    return new CreateAppNotification(appId, mId, token, notification).execute();
    }

	@Override
    public AppNotification createDeviceNotification(String appId, String deviceId, String token, AppNotification notification) throws RESTException {
	    return new CreateDeviceNotification(appId, deviceId, token, notification).execute();
    }

	@Override
    public void createAppNotification(String appId, String mId, String token,
            AppNotification notification, CreateAppNotificationCallback callback) {
	    this.createAppNotificationCallback = callback;
	    new CreateAppNotification(appId, mId, token, notification).execute(this);
    }

	@Override
    public void createDeviceNotification(String appId, String deviceId,
            String token, AppNotification notification,
            CreateDeviceNotificationCallback callback) {
	    this.createDeviceNotificationCallback = callback;
	    new CreateDeviceNotification(appId, deviceId, token, notification).execute(this);
    }

	@Override
	public void onCreateAppNotification(AppNotification result) {
		if (createAppNotificationCallback != null) {
			createAppNotificationCallback.onCreateAppNotification(result);
		}
    }

	@Override
	public void onFailCreateAppNotification(Error error) {
		if (createAppNotificationCallback != null) {
			createAppNotificationCallback.onFailCreateAppNotification(error);
		}
    }

	@Override
	public void onCreateDeviceNotification(AppNotification result) {
		if (createDeviceNotificationCallback != null) {
			createDeviceNotificationCallback.onCreateDeviceNotification(result);
		}
    }

	@Override
	public void onFailCreateDeviceNotification(Error error) {
		if (createDeviceNotificationCallback != null) {
			createDeviceNotificationCallback.onFailCreateDeviceNotification(error);
		}
    }

}
