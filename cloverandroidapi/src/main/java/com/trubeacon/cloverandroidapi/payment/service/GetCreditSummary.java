package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.CreditSummary;

public class GetCreditSummary extends RESTMethod<CreditSummary> {

	private static final String URL = "/merchants/{merchantId}/summaries/credits";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetCreditSummaryCallback {
	    public void onGetCreditSummary(CreditSummary result);
	    public void onFailGetCreditSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCreditSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<CreditSummary>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", mId));
	}
	
	@Override
	public CreditSummary execute() throws RESTException {
		return super.execute();
	}

	public void execute(final GetCreditSummaryCallback callback) {
	    super.execute(new Callback<CreditSummary>() {
	        @Override
	        public void onReceiveResult(CreditSummary result) {
	            if (callback != null) {
	                callback.onGetCreditSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCreditSummary(error);
	            }
	        }
	    });
	}

}
