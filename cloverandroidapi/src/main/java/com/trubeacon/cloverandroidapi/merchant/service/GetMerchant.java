package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.merchant.Merchant;

public class GetMerchant extends RESTMethod<Merchant> {

	private static final String URL = "/merchants/{merchantId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetMerchantCallback {
	    public void onGetMerchant(Merchant result);
	    public void onFailGetMerchant(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetMerchant(String merchantId, String token) {
		super(token, new TypeReference<Merchant>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public Merchant execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetMerchantCallback callback) {
	    super.execute(new Callback<Merchant>() {
	        @Override
	        public void onReceiveResult(Merchant result) {
	            if (callback != null) {
	                callback.onGetMerchant(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetMerchant(error);
	            }
	        }
	    });
	}

}
