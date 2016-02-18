package com.trubeacon.cloverandroidapi.app.service;

import com.trubeacon.cloverandroidapi.app.AppBillingInfo;
import com.trubeacon.cloverandroidapi.app.AppMeteredEvent;
import com.trubeacon.cloverandroidapi.app.service.CreateMeteredEvent.CreateMeteredEventCallback;
import com.trubeacon.cloverandroidapi.app.service.GetBillingInfo.GetBillingInfoCallback;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;

public class AppServiceImpl implements AppService, GetBillingInfoCallback, CreateMeteredEventCallback {

	private GetBillingInfoCallback getBillingInfoCallback;
	private CreateMeteredEventCallback createMeteredEventCallback;
	
	@Override
    public AppBillingInfo getBillingInfo(String merchantId, String token, String appId) throws RESTException {
	    return new GetBillingInfo(merchantId, token, appId).execute();
    }

	@Override
    public void getBillingInfo(String merchantId, String token, String appId, GetBillingInfoCallback callback) {
	    this.getBillingInfoCallback = callback;
	    new GetBillingInfo(merchantId, token, appId).execute(this);
    }

	@Override
    public AppMeteredEvent createMeteredEvent(String merchantId, String token, String appId, String meteredId, AppMeteredEvent event) throws RESTException {
	    return new CreateMeteredEvent(merchantId, token, appId, meteredId, event).execute();
    }

	@Override
    public void createMeteredEvent(String merchantId, String token, String appId, String meteredId, AppMeteredEvent event, CreateMeteredEventCallback callback) {
		this.createMeteredEventCallback = callback;
	    new CreateMeteredEvent(merchantId, token, appId, meteredId, event).execute(this);
    }

	@Override
    public void onCreateMeteredEvent(AppMeteredEvent event) {
	    if (this.createMeteredEventCallback != null) {
	    	this.createMeteredEventCallback.onCreateMeteredEvent(event);
	    }
    }

	@Override
    public void onFailCreateMeteredEvent(Error error) {
	    if (this.createMeteredEventCallback != null) {
	    	this.createMeteredEventCallback.onFailCreateMeteredEvent(error);
	    }
    }

	@Override
    public void onGetBillingInfo(AppBillingInfo billingInfo) {
	    if (this.getBillingInfoCallback != null) {
	    	this.getBillingInfoCallback.onGetBillingInfo(billingInfo);
	    }
    }

	@Override
    public void onFailGetBillingInfo(Error error) {
	    if (this.getBillingInfoCallback != null) {
	    	this.getBillingInfoCallback.onFailGetBillingInfo(error);
	    }
    }

}
