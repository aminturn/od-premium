package com.trubeacon.cloverandroidapi.employee.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.employee.Shift;

public class UpdateEmployeeShift extends RESTMethod<Shift> {

	private static final String URL = "/merchants/{merchantId}/employees/{employeeId}/shifts/{shiftId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateEmployeeShiftCallback {
	    public void onUpdateEmployeeShift(Shift result);
	    public void onFailUpdateEmployeeShift(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateEmployeeShift(String merchantId, String token, String employeeId, String shiftId, Shift shift) {
		super(token, new TypeReference<Shift>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("employeeId", employeeId));
		super.addPathParam(Param.path("shiftId", shiftId));
		super.setEntity(shift);
	}
	
	@Override
	public Shift execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateEmployeeShiftCallback callback) {
	    super.execute(new Callback<Shift>() {
	        @Override
	        public void onReceiveResult(Shift result) {
	            if (callback != null) {
	                callback.onUpdateEmployeeShift(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateEmployeeShift(error);
	            }
	        }
	    });
	}

}
