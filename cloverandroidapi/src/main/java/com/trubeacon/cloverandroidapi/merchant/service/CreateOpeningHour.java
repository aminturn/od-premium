package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.HoursSet;

public class CreateOpeningHour extends RESTMethod<HoursSet> {

	private static final String URL = "/merchants/{merchantId}/opening_hours";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOpeningHourCallback {
	    public void onCreateOpeningHour(HoursSet result);
	    public void onFailCreateOpeningHour(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOpeningHour(String merchantId, String token, HoursSet openingHours) {
		super(token, new TypeReference<HoursSet>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(openingHours);
	}
	
	@Override
	public HoursSet execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOpeningHourCallback callback) {
	    super.execute(new Callback<HoursSet>() {
	        @Override
	        public void onReceiveResult(HoursSet result) {
	            if (callback != null) {
	                callback.onCreateOpeningHour(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOpeningHour(error);
	            }
	        }
	    });
	}
	
}
