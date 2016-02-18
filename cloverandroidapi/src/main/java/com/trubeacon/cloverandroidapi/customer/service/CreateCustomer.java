package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.customer.Customer;

public class CreateCustomer extends RESTMethod<Customer> {

	private static final String URL = "/merchants/{merchantId}/customers";
	private static final HttpMethod METHOD = HttpMethod.POST;
		
	public interface CreateCustomerCallback {
		public void onCreateCustomer(Customer result);
		public void onFailCreateCustomer(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateCustomer(String merchantId, String token, Customer customer) {
		super(token, new TypeReference<Customer>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(customer);
	}
	
	@Override
	public Customer execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateCustomerCallback callback) {
		super.execute(new Callback<Customer>() {

			@Override
            public void onReceiveResult(Customer result) {
	            if (callback != null) {
	            	callback.onCreateCustomer(result);
	            }
            }

			@Override
            public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	            	callback.onFailCreateCustomer(error);
	            }
            }
			
		});
	}
	
}
