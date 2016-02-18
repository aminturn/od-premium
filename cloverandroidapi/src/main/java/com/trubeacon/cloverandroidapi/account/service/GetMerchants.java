package com.trubeacon.cloverandroidapi.account.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.Version;
import com.trubeacon.cloverandroidapi.account.Merchants;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.merchant.Merchant;

public class GetMerchants extends RESTMethod<Merchants> {

	private static final String URL = "/account/{accountId}/merchants";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetMerchantsCallback {
		public void onGetMerchants(Merchants merchants);
		public void onFailGetMerchants(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetMerchants(String accountId, String token) {
		super(token, new TypeReference<Merchants>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
		super.addPathParam(Param.path("accountId", accountId));
	}
	
	@Override
	public Merchants execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetMerchantsCallback callback) {
		super.execute(new Callback<Merchants>() {

			@Override
            public void onReceiveResult(Merchants result) {
	            if (callback != null) {
	            	callback.onGetMerchants(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailGetMerchants(error);
	            }
            }
			
		});
	}
	
}
