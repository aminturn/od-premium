package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.CardSummary;

public class GetCardSummary extends RESTMethod<WrappedList<CardSummary>> {
	
	private static final String URL = "/merchants/{merchantId}/summaries/card_types";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetCardSummaryCallback {
	    public void onGetCardSummary(WrappedList<CardSummary> result);
	    public void onFailGetCardSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCardSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<CardSummary>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", mId));
	}
	
	@Override
	public WrappedList<CardSummary> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetCardSummaryCallback callback) {
	    super.execute(new Callback<WrappedList<CardSummary>>() {
	        @Override
	        public void onReceiveResult(WrappedList<CardSummary> result) {
	            if (callback != null) {
	                callback.onGetCardSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCardSummary(error);
	            }
	        }
	    });
	}

}
