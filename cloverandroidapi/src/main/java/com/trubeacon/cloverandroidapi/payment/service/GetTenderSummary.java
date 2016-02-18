package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.TenderSummary;

public class GetTenderSummary extends RESTMethod<WrappedList<TenderSummary>> {

	private static final String URL = "/merchants/{merchantId}/summaries/tenders";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTenderSummaryCallback {
	    public void onGetTenderSummary(WrappedList<TenderSummary> result);
	    public void onFailGetTenderSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTenderSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<TenderSummary>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", mId));
	}
	
	@Override
	public WrappedList<TenderSummary> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTenderSummaryCallback callback) {
	    super.execute(new Callback<WrappedList<TenderSummary>>() {
	        @Override
	        public void onReceiveResult(WrappedList<TenderSummary> result) {
	            if (callback != null) {
	                callback.onGetTenderSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTenderSummary(error);
	            }
	        }
	    });
	}

}
