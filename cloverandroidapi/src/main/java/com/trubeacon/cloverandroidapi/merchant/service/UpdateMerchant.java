package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.merchant.Merchant;

public class UpdateMerchant extends RESTMethod<Merchant> {

	private static final String URL = "/merchants/{merchantId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateMerchantCallback {
	    public void onUpdateMerchant(Merchant result);
	    public void onFailUpdateMerchant(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateMerchant(String merchantId, String token, Merchant merchant) {
		super(token, new TypeReference<Merchant>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(merchant);
	}
	
	@Override
	public Merchant execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateMerchantCallback callback) {
	    super.execute(new Callback<Merchant>() {
	        @Override
	        public void onReceiveResult(Merchant result) {
	            if (callback != null) {
	                callback.onUpdateMerchant(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateMerchant(error);
	            }
	        }
	    });
	}

}
