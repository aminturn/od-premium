package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.Payment;

public class CreateOrderPayment extends RESTMethod<Payment> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/payments";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOrderPaymentCallback {
	    public void onCreateOrderPayment(Payment result);
	    public void onFailCreateOrderPayment(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOrderPayment(String merchantId, String token, String orderId, Payment payment) {
		super(token, new TypeReference<Payment>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.setEntity(payment);
	}
	
	@Override
	public Payment execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOrderPaymentCallback callback) {
	    super.execute(new Callback<Payment>() {
	        @Override
	        public void onReceiveResult(Payment result) {
	            if (callback != null) {
	                callback.onCreateOrderPayment(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOrderPayment(error);
	            }
	        }
	    });
	}
	
}
