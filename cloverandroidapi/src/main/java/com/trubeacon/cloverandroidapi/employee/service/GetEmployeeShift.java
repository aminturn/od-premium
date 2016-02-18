package com.trubeacon.cloverandroidapi.employee.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.employee.Shift;

public class GetEmployeeShift extends RESTMethod<Shift> {

	private static final String URL = "/merchants/{merchantId}/employees/{employeeId}/shifts/{shiftId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetEmployeeShiftCallback {
	    public void onGetEmployeeShift(Shift result);
	    public void onFailGetEmployeeShift(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetEmployeeShift(String merchantId, String token, String employeeId, String shiftId) {
		super(token, new TypeReference<Shift>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("employeeId", employeeId));
		super.addPathParam(Param.path("shiftId", shiftId));
	}
	
	@Override
	public Shift execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetEmployeeShiftCallback callback) {
	    super.execute(new Callback<Shift>() {
	        @Override
	        public void onReceiveResult(Shift result) {
	            if (callback != null) {
	                callback.onGetEmployeeShift(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetEmployeeShift(error);
	            }
	        }
	    });
	}

}
