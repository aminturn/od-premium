package com.trubeacon.cloverandroidapi.employee.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.Employee;

public class GetEmployees extends RESTMethod<WrappedList<Employee>> {

	private static final String URL = "/merchants/{merchantId}/employees";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetEmployeesCallback {
	    public void onGetEmployees(WrappedList<Employee> result);
	    public void onFailGetEmployees(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetEmployees(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Employee>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Employee> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetEmployeesCallback callback) {
	    super.execute(new Callback<WrappedList<Employee>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Employee> result) {
	            if (callback != null) {
	                callback.onGetEmployees(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetEmployees(error);
	            }
	        }
	    });
	}

}
