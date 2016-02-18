package com.trubeacon.cloverandroidapi.notification.service;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.notification.AppNotification;
import com.trubeacon.cloverandroidapi.notification.service.CreateAppNotification.CreateAppNotificationCallback;
import com.trubeacon.cloverandroidapi.notification.service.CreateDeviceNotification.CreateDeviceNotificationCallback;

public interface NotificationService {
	public AppNotification createAppNotification(String appId, String mId, String token, AppNotification notification) throws RESTException;
	public void createAppNotification(String appId, String mId, String token, AppNotification notification, CreateAppNotificationCallback callback);
	public AppNotification createDeviceNotification(String appId, String deviceId, String token, AppNotification notification) throws RESTException;
	public void createDeviceNotification(String appId, String deviceId, String token, AppNotification notification, CreateDeviceNotificationCallback callback);
}
