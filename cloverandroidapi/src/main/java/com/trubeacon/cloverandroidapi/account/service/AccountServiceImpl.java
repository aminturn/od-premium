package com.trubeacon.cloverandroidapi.account.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.account.Merchants;
import com.trubeacon.cloverandroidapi.account.service.GetMerchants.GetMerchantsCallback;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.merchant.Merchant;

public class AccountServiceImpl implements AccountService, GetMerchantsCallback {

	private GetMerchantsCallback getMerchantsCallback;
	
	@Override
    public List<Merchant> getMerchants(String accountId, String token) throws RESTException {
	    return new GetMerchants(accountId, token).execute();
    }

	@Override
    public void getMerchants(String accountId, String token, GetMerchantsCallback callback) {
		this.getMerchantsCallback = callback;
	    new GetMerchants(accountId, token).execute(this);
    }

	@Override
    public void onGetMerchants(Merchants merchants) {
	    if (this.getMerchantsCallback != null) {
	    	this.getMerchantsCallback.onGetMerchants(merchants);
	    }
    }

	@Override
    public void onFailGetMerchants(Error error) {
		if (this.getMerchantsCallback != null) {
			this.getMerchantsCallback.onFailGetMerchants(error);
		}
    }

}
