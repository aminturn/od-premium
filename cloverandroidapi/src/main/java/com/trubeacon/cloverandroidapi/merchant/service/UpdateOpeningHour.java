package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.HoursSet;

public class UpdateOpeningHour extends RESTMethod<HoursSet> {

	private static final String URL = "/merchants/{merchantId}/opening_hours/{openingHoursId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateOpeningHourCallback {
	    public void onUpdateOpeningHour(HoursSet result);
	    public void onFailUpdateOpeningHour(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateOpeningHour(String merchantId, String token, String openingHoursId, HoursSet openingHours) {
		super(token, new TypeReference<HoursSet>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("openingHoursId", openingHoursId));
		super.setEntity(openingHours);
	}
	
	@Override
	public HoursSet execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateOpeningHourCallback callback) {
	    super.execute(new Callback<HoursSet>() {
	        @Override
	        public void onReceiveResult(HoursSet result) {
	            if (callback != null) {
	                callback.onUpdateOpeningHour(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateOpeningHour(error);
	            }
	        }
	    });
	}

}
