package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.customer.Customer;

public class DeleteCustomer extends RESTMethod<Customer> {

	private static final String URL = "/merchants/{merchantId}/customers/{customerId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
		
	public interface DeleteCustomerCallback {
	    public void onDeleteCustomer(Customer result);
	    public void onFailDeleteCustomer(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteCustomer(String merchantId, String token, String customerId) {
		super(token, new TypeReference<Customer>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
	}
	
	@Override
	public Customer execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteCustomerCallback callback) {
	    super.execute(new Callback<Customer>() {
	        @Override
	        public void onReceiveResult(Customer result) {
	            if (callback != null) {
	                callback.onDeleteCustomer(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteCustomer(error);
	            }
	        }
	    });
	}
	
}
