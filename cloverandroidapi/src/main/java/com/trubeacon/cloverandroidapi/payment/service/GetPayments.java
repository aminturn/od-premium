package com.trubeacon.cloverandroidapi.payment.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.Payment;

public class GetPayments extends RESTMethod<WrappedList<Payment>> {

	private static final String URL = "/merchants/{merchantId}/payments";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetPaymentsCallback {
	    public void onGetPayments(WrappedList<Payment> result);
	    public void onFailGetPayments(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetPayments(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Payment>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Payment> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetPaymentsCallback callback) {
	    super.execute(new Callback<WrappedList<Payment>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Payment> result) {
	            if (callback != null) {
	                callback.onGetPayments(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetPayments(error);
	            }
	        }
	    });
	}

}
