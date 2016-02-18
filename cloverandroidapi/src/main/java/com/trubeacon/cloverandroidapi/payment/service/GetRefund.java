package com.trubeacon.cloverandroidapi.payment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.Refund;

public class GetRefund extends RESTMethod<Refund> {

	private static final String URL = "/merchants/{merchantId}/refunds/{refundId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetRefundCallback {
	    public void onGetRefund(Refund result);
	    public void onFailGetRefund(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetRefund(String merchantId, String token, String refundId) {
		super(token, new TypeReference<Refund>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("refundId", refundId));
	}
	
	@Override
	public Refund execute() throws RESTException {
		return super.execute();
	}

	public void execute(final GetRefundCallback callback) {
	    super.execute(new Callback<Refund>() {
	        @Override
	        public void onReceiveResult(Refund result) {
	            if (callback != null) {
	                callback.onGetRefund(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetRefund(error);
	            }
	        }
	    });
	}

}
