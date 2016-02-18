package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.Tender;

public class GetTender extends RESTMethod<Tender> {

	private static final String URL = "/merchants/{merchantId}/tenders/{tenderId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTenderCallback {
	    public void onGetTender(Tender result);
	    public void onFailGetTender(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTender(String merchantId, String token, String tenderId) {
		super(token, new TypeReference<Tender>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("tenderId", tenderId));
	}
	
	@Override
	public Tender execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTenderCallback callback) {
	    super.execute(new Callback<Tender>() {
	        @Override
	        public void onReceiveResult(Tender result) {
	            if (callback != null) {
	                callback.onGetTender(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTender(error);
	            }
	        }
	    });
	}

}
