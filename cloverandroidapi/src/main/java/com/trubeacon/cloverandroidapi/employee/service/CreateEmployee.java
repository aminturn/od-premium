package com.trubeacon.cloverandroidapi.employee.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.employee.Employee;

public class CreateEmployee extends RESTMethod<Employee> {

	private static final String URL = "/merchants/{merchantId}/employees";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateEmployeeCallback {
		public void onCreateEmployee(Employee result);
		public void onFailCreateEmployee(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateEmployee(String merchantId, String token, Employee employee) {
		super(token, new TypeReference<Employee>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(employee);
	}
	
	@Override
	public Employee execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateEmployeeCallback callback) {
		super.execute(new Callback<Employee>() {

			@Override
            public void onReceiveResult(Employee result) {
	            if (callback != null) {
	            	callback.onCreateEmployee(result);
	            }
            }

			@Override
            public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	            	callback.onFailCreateEmployee(error);
	            }
            }
			
		});
	}
	
}
