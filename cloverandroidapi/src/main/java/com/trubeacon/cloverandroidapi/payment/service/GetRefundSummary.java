package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.RefundSummary;

public class GetRefundSummary extends RESTMethod<RefundSummary> {

	private static final String URL = "/merchants/{merchantId}/summaries/refunds";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetRefundSummaryCallback {
	    public void onGetRefundSummary(RefundSummary result);
	    public void onFailGetRefundSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetRefundSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<RefundSummary>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", mId));
	}
	
	@Override
	public RefundSummary execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetRefundSummaryCallback callback) {
	    super.execute(new Callback<RefundSummary>() {
	        @Override
	        public void onReceiveResult(RefundSummary result) {
	            if (callback != null) {
	                callback.onGetRefundSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetRefundSummary(error);
	            }
	        }
	    });
	}

}
