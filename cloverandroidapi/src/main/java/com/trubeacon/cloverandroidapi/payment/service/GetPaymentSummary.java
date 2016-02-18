package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.PaymentSummary;

public class GetPaymentSummary extends RESTMethod<PaymentSummary> {

	private static final String URL = "/merchants/{merchantId}/summaries/payments";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetPaymentSummaryCallback {
	    public void onGetPaymentSummary(PaymentSummary result);
	    public void onFailGetPaymentSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetPaymentSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<PaymentSummary>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", mId));
	}
	
	@Override
	public PaymentSummary execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetPaymentSummaryCallback callback) {
	    super.execute(new Callback<PaymentSummary>() {
	        @Override
	        public void onReceiveResult(PaymentSummary result) {
	            if (callback != null) {
	                callback.onGetPaymentSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetPaymentSummary(error);
	            }
	        }
	    });
	}

}
