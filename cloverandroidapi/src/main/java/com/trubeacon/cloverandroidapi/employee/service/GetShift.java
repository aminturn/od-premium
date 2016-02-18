package com.trubeacon.cloverandroidapi.employee.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.employee.Shift;

public class GetShift extends RESTMethod<Shift> {

	private static final String URL = "/merchants/{merchantId}/shifts/{shiftId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetShiftCallback {
	    public void onGetShift(Shift result);
	    public void onFailGetShift(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetShift(String merchantId, String token, String shiftId) {
		super(token, new TypeReference<Shift>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("shiftId", shiftId));
	}
	
	@Override
	public Shift execute() throws RESTException {
		return super.execute();
	}

	public void execute(final GetShiftCallback callback) {
	    super.execute(new Callback<Shift>() {
	        @Override
	        public void onReceiveResult(Shift result) {
	            if (callback != null) {
	                callback.onGetShift(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetShift(error);
	            }
	        }
	    });
	}

}
