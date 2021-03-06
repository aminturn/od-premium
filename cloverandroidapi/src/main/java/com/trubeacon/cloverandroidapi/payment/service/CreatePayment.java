package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.Payment;

public class CreatePayment extends RESTMethod<Payment> {

	private static final String URL = "/merchants/{merchantId}/payments";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreatePaymentCallback {
	    public void onCreatePayment(Payment result);
	    public void onFailCreatePayment(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreatePayment(String merchantId, String token, Payment payment) {
		super(token, new TypeReference<Payment>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(payment);
	}
	
	@Override
	public Payment execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreatePaymentCallback callback) {
	    super.execute(new Callback<Payment>() {
	        @Override
	        public void onReceiveResult(Payment result) {
	            if (callback != null) {
	                callback.onCreatePayment(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreatePayment(error);
	            }
	        }
	    });
	}
	
}
