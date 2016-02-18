package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.HoursSet;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class GetOpeningHours extends RESTMethod<WrappedList<HoursSet>> {
	
	private static final String URL = "/merchants/{merchantId}/opening_hours";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetOpeningHoursCallback {
	    public void onGetOpeningHours(WrappedList<HoursSet> result);
	    public void onFailGetOpeningHours(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetOpeningHours(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<HoursSet>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<HoursSet> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetOpeningHoursCallback callback) {
	    super.execute(new Callback<WrappedList<HoursSet>>() {
	        @Override
	        public void onReceiveResult(WrappedList<HoursSet> result) {
	            if (callback != null) {
	                callback.onGetOpeningHours(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetOpeningHours(error);
	            }
	        }
	    });
	}

}
