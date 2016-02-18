package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.Tender;

public class GetTenders extends RESTMethod<WrappedList<Tender>> {

	private static final String URL = "/merchants/{merchantId}/tenders";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTendersCallback {
	    public void onGetTenders(WrappedList<Tender> result);
	    public void onFailGetTenders(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTenders(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Tender>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Tender> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTendersCallback callback) {
	    super.execute(new Callback<WrappedList<Tender>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Tender> result) {
	            if (callback != null) {
	                callback.onGetTenders(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTenders(error);
	            }
	        }
	    });
	}

}
