package com.trubeacon.cloverandroidapi.auth.service;

import com.trubeacon.cloverandroidapi.auth.AccessToken;
import com.trubeacon.cloverandroidapi.auth.service.GetAccessToken.GetAccessTokenCallback;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;

public class AuthenticationServiceImpl implements AuthenticationService, GetAccessTokenCallback {

	private GetAccessTokenCallback getAccessTokenCallback;
	
	@Override
    public AccessToken getAccessToken(String clientId, String secret, String authCode) throws RESTException {
	    return new GetAccessToken(clientId, secret, authCode).execute();
    }

	@Override
    public void getAccessToken(String clientId, String secret, String authCode, GetAccessTokenCallback callback) {
		this.getAccessTokenCallback = callback;
	    new GetAccessToken(clientId, secret, authCode).execute(this);
    }

	@Override
    public void onGetAccessToken(AccessToken result) {
	    if (this.getAccessTokenCallback != null) {
	    	this.getAccessTokenCallback.onGetAccessToken(result);
	    }
    }

	@Override
    public void onFailGetAccessToken(Error error) {
	    if (this.getAccessTokenCallback != null) {
	    	this.getAccessTokenCallback.onFailGetAccessToken(error);
	    }
    }

}
