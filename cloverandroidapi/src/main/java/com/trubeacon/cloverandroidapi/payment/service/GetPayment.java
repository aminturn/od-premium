package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.Payment;

public class GetPayment extends RESTMethod<Payment> {

	private static final String URL = "/merchants/{merchantId}/payments/{paymentId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetPaymentCallback {
	    public void onGetPayment(Payment result);
	    public void onFailGetPayment(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetPayment(String merchantId, String token, String paymentId) {
		super(token, new TypeReference<Payment>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("paymentId", paymentId));
	}
	
	@Override
	public Payment execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetPaymentCallback callback) {
	    super.execute(new Callback<Payment>() {
	        @Override
	        public void onReceiveResult(Payment result) {
	            if (callback != null) {
	                callback.onGetPayment(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetPayment(error);
	            }
	        }
	    });
	}

}
