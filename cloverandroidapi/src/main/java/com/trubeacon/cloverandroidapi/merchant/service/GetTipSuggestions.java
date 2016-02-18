package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.TipSuggestion;

public class GetTipSuggestions extends RESTMethod<WrappedList<TipSuggestion>> {

	private static final String URL = "/merchants/{merchantId}/tip_suggestions";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTipSuggestionsCallback {
	    public void onGetTipSuggestions(WrappedList<TipSuggestion> result);
	    public void onFailGetTipSuggestions(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTipSuggestions(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<TipSuggestion>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<TipSuggestion> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTipSuggestionsCallback callback) {
	    super.execute(new Callback<WrappedList<TipSuggestion>>() {
	        @Override
	        public void onReceiveResult(WrappedList<TipSuggestion> result) {
	            if (callback != null) {
	                callback.onGetTipSuggestions(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTipSuggestions(error);
	            }
	        }
	    });
	}

}
