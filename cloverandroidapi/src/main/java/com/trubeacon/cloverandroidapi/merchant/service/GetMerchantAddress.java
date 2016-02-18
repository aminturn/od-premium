package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.Address;

public class GetMerchantAddress extends RESTMethod<Address> {

	private static final String URL = "/merchants/{merchantId}/address";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetMerchantAddressCallback {
	    public void onGetMerchantAddress(Address result);
	    public void onFailGetMerchantAddress(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetMerchantAddress(String merchantId, String token) {
		super(token, new TypeReference<Address>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public Address execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetMerchantAddressCallback callback) {
	    super.execute(new Callback<Address>() {
	        @Override
	        public void onReceiveResult(Address result) {
	            if (callback != null) {
	                callback.onGetMerchantAddress(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetMerchantAddress(error);
	            }
	        }
	    });
	}

}
