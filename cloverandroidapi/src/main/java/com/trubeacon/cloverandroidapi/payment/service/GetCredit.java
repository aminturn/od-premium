package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.Credit;

public class GetCredit extends RESTMethod<Credit> {

	private static final String URL = "/merchants/{merchantId}/credits/{creditId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetCreditCallback {
	    public void onGetCredit(Credit result);
	    public void onFailGetCredit(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCredit(String merchantId, String token, String creditId) {
		super(token, new TypeReference<Credit>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("creditId", creditId));
	}
	
	@Override
	public Credit execute() throws RESTException {
		return super.execute();
	}

	public void execute(final GetCreditCallback callback) {
	    super.execute(new Callback<Credit>() {
	        @Override
	        public void onReceiveResult(Credit result) {
	            if (callback != null) {
	                callback.onGetCredit(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCredit(error);
	            }
	        }
	    });
	}

}
