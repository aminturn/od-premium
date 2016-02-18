package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.customer.Customer;

public class GetCustomer extends RESTMethod<Customer> {

	private static final String URL = "/merchants/{merchantId}/customers/{customerId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
		
	public interface GetCustomerCallback {
	    public void onGetCustomer(Customer result);
	    public void onFailGetCustomer(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCustomer(String merchantId, String token, String customerId) {
		super(token, new TypeReference<Customer>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
	}
	
	@Override
	public Customer execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetCustomerCallback callback) {
	    super.execute(new Callback<Customer>() {
	        @Override
	        public void onReceiveResult(Customer result) {
	            if (callback != null) {
	                callback.onGetCustomer(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCustomer(error);
	            }
	        }
	    });
	}

}
