package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.merchant.Properties;

public class GetMerchantProperties extends RESTMethod<Properties> {

	private static final String URL = "/merchants/{merchantId}/properties";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetMerchantPropertiesCallback {
	    public void onGetMerchantProperties(Properties result);
	    public void onFailGetMerchantProperties(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetMerchantProperties(String merchantId, String token) {
		super(token, new TypeReference<Properties>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public Properties execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetMerchantPropertiesCallback callback) {
	    super.execute(new Callback<Properties>() {
	        @Override
	        public void onReceiveResult(Properties result) {
	            if (callback != null) {
	                callback.onGetMerchantProperties(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetMerchantProperties(error);
	            }
	        }
	    });
	}

}
