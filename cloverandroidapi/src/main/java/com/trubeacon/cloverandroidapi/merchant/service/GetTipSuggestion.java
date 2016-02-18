package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.TipSuggestion;

public class GetTipSuggestion extends RESTMethod<TipSuggestion> {
	
	private static final String URL = "/merchants/{merchantId}/tip_suggestions/{tipSuggestionId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTipSuggestionCallback {
	    public void onGetTipSuggestion(TipSuggestion result);
	    public void onFailGetTipSuggestion(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTipSuggestion(String merchantId, String token, String tipSuggestionId) {
		super(token, new TypeReference<TipSuggestion>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("tipSuggestionId", tipSuggestionId));
	}
	
	@Override
	public TipSuggestion execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTipSuggestionCallback callback) {
	    super.execute(new Callback<TipSuggestion>() {
	        @Override
	        public void onReceiveResult(TipSuggestion result) {
	            if (callback != null) {
	                callback.onGetTipSuggestion(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTipSuggestion(error);
	            }
	        }
	    });
	}

}
