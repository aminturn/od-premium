package com.trubeacon.cloverandroidapi.payment.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.Refund;

public class GetRefunds extends RESTMethod<WrappedList<Refund>> {

	private static final String URL = "/merchants/{merchantId}/refunds";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetRefundsCallback {
	    public void onGetRefunds(WrappedList<Refund> result);
	    public void onFailGetRefunds(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetRefunds(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Refund>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Refund> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetRefundsCallback callback) {
	    super.execute(new Callback<WrappedList<Refund>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Refund> result) {
	            if (callback != null) {
	                callback.onGetRefunds(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetRefunds(error);
	            }
	        }
	    });
	}

}
