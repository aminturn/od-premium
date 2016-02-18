package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.HoursSet;

public class DeleteOpeningHour extends RESTMethod<HoursSet> {

	private static final String URL = "/merchants/{merchantId}/opening_hours/{openingHoursId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteOpeningHourCallback {
	    public void onDeleteOpeningHour(HoursSet result);
	    public void onFailDeleteOpeningHour(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOpeningHour(String merchantId, String token, String openingHoursId) {
		super(token, new TypeReference<HoursSet>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("openingHoursId", openingHoursId));
	}
	
	@Override
	public HoursSet execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteOpeningHourCallback callback) {
	    super.execute(new Callback<HoursSet>() {
	        @Override
	        public void onReceiveResult(HoursSet result) {
	            if (callback != null) {
	                callback.onDeleteOpeningHour(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOpeningHour(error);
	            }
	        }
	    });
	}

}
