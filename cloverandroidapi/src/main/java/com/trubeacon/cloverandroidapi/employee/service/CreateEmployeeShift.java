package com.trubeacon.cloverandroidapi.employee.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.employee.Shift;

public class CreateEmployeeShift extends RESTMethod<Shift> {

	private static final String URL = "/merchants/{merchantId}/employees/{employeeId}/shifts";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateEmployeeShiftCallback {
		public void onCreateEmployeeShift(Shift result);
		public void onFailCreateEmployeeShift(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateEmployeeShift(String merchantId, String token, String employeeId, Shift shift) {
		super(token, new TypeReference<Shift>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("employeeId", employeeId));
		super.setEntity(shift);
	}
	
	@Override
	public Shift execute() throws RESTException {
		return super.execute();
	}	
	
	public void execute(final CreateEmployeeShiftCallback callback) {
		super.execute(new Callback<Shift>() {

			@Override
            public void onReceiveResult(Shift result) {
	            if (callback != null) {
	            	callback.onCreateEmployeeShift(result);
	            }
            }

			@Override
            public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	            	callback.onFailCreateEmployeeShift(error);
	            }
            }
			
		});
	}
	
}
