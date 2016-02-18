package com.trubeacon.cloverandroidapi.auth.service;

import com.trubeacon.cloverandroidapi.auth.AccessToken;
import com.trubeacon.cloverandroidapi.auth.service.GetAccessToken.GetAccessTokenCallback;
import com.trubeacon.cloverandroidapi.client.RESTException;

public interface AuthenticationService {
	public AccessToken getAccessToken(String clientId, String secret, String authCode) throws RESTException;
	public void getAccessToken(String clientId, String secret, String authCode, GetAccessTokenCallback callback);
}
