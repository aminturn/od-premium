package com.trubeacon.cloverandroidapi.auth.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.auth.AccessToken;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;

public class GetAccessToken extends RESTMethod<AccessToken> {

	private static final String URL = "/token";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetAccessTokenCallback {
	    public void onGetAccessToken(AccessToken result);
	    public void onFailGetAccessToken(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetAccessToken(String clientId, String secret, String authCode) {
		super(null, new TypeReference<AccessToken>(){}, CloverService.getOAuthBaseUrl() + URL, METHOD);
		super.addQueryParam(Param.query("client_id", clientId));
		super.addQueryParam(Param.query("client_secret", secret));
		super.addQueryParam(Param.query("code", authCode));
	}
	
	@Override
	public AccessToken execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetAccessTokenCallback callback) {
	    super.execute(new Callback<AccessToken>() {
	        @Override
	        public void onReceiveResult(AccessToken result) {
	            if (callback != null) {
	                callback.onGetAccessToken(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetAccessToken(error);
	            }
	        }
	    });
	}

}
