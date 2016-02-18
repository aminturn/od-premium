package com.trubeacon.cloverandroidapi.payment.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.Credit;
import com.trubeacon.cloverandroidapi.payment.Refund;

public class GetCredits extends RESTMethod<WrappedList<Credit>> {

	private static final String URL = "/merchants/{merchantId}/credits";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetCreditsCallback {
	    public void onGetCredits(WrappedList<Credit> result);
	    public void onFailGetCredits(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCredits(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Credit>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Credit> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetCreditsCallback callback) {
	    super.execute(new Callback<WrappedList<Credit>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Credit> result) {
	            if (callback != null) {
	                callback.onGetCredits(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCredits(error);
	            }
	        }
	    });
	}

}
