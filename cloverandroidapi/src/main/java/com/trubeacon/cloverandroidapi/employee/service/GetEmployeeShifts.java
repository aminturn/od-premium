package com.trubeacon.cloverandroidapi.employee.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.Shift;

public class GetEmployeeShifts extends RESTMethod<WrappedList<Shift>> {

	private static final String URL = "/merchants/{merchantId}/employees/{employeeId}/shifts";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetEmployeeShiftsCallback {
	    public void onGetEmployeeShifts(WrappedList<Shift> result);
	    public void onFailGetEmployeeShifts(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetEmployeeShifts(String merchantId, String token, String employeeId, Object... params) {
		super(token, new TypeReference<WrappedList<Shift>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("employeeId", employeeId));
	}
	
	@Override
	public WrappedList<Shift> execute() throws RESTException {
		return super.execute();
	}

	public void execute(final GetEmployeeShiftsCallback callback) {
	    super.execute(new Callback<WrappedList<Shift>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Shift> result) {
	            if (callback != null) {
	                callback.onGetEmployeeShifts(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetEmployeeShifts(error);
	            }
	        }
	    });
	}

}
