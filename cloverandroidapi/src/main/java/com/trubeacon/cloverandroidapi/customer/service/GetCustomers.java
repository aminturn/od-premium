package com.trubeacon.cloverandroidapi.customer.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.customer.Customer;

public class GetCustomers extends RESTMethod<WrappedList<Customer>> {

	private static final String URL = "/merchants/{merchantId}/customers";
	private static final HttpMethod METHOD = HttpMethod.GET;
		
	public interface GetCustomersCallback {
	    public void onGetCustomers(WrappedList<Customer> result);
	    public void onFailGetCustomers(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCustomers(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Customer>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Customer> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetCustomersCallback callback) {
	    super.execute(new Callback<WrappedList<Customer>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Customer> result) {
	            if (callback != null) {
	                callback.onGetCustomers(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCustomers(error);
	            }
	        }
	    });
	}

}
