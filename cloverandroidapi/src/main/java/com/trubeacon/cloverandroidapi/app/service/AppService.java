package com.trubeacon.cloverandroidapi.app.service;

import com.trubeacon.cloverandroidapi.app.AppBillingInfo;
import com.trubeacon.cloverandroidapi.app.AppMeteredEvent;
import com.trubeacon.cloverandroidapi.app.service.CreateMeteredEvent.CreateMeteredEventCallback;
import com.trubeacon.cloverandroidapi.app.service.GetBillingInfo.GetBillingInfoCallback;
import com.trubeacon.cloverandroidapi.client.RESTException;

public interface AppService {
	public AppBillingInfo getBillingInfo(String merchantId, String token, String appId) throws RESTException;
	public void getBillingInfo(String merchantId, String token, String appId, GetBillingInfoCallback callback);
	public AppMeteredEvent createMeteredEvent(String merchantId, String token, String appId, String meteredId, AppMeteredEvent event) throws RESTException;
	public void createMeteredEvent(String merchantId, String token, String appId, String meteredId, AppMeteredEvent event, CreateMeteredEventCallback callback);
}
