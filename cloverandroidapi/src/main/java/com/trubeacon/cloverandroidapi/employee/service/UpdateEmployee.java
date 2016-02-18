package com.trubeacon.cloverandroidapi.employee.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.employee.Employee;

public class UpdateEmployee extends RESTMethod<Employee> {
	
	private static final String URL = "/merchants/{merchantId}/employees/{employeeId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateEmployeeCallback {
	    public void onUpdateEmployee(Employee result);
	    public void onFailUpdateEmployee(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateEmployee(String merchantId, String token, String employeeId, Employee employee) {
		super(token, new TypeReference<Employee>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("employeeId", employeeId));
		super.setEntity(employee);
	}
	
	@Override
	public Employee execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateEmployeeCallback callback) {
	    super.execute(new Callback<Employee>() {
	        @Override
	        public void onReceiveResult(Employee result) {
	            if (callback != null) {
	                callback.onUpdateEmployee(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateEmployee(error);
	            }
	        }
	    });
	}

}
