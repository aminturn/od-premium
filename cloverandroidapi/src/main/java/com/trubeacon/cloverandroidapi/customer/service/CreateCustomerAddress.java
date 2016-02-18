package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.Address;

public class CreateCustomerAddress extends RESTMethod<Address> {

	private static final String URL = "/merchants/{merchantId}/customers/{customerId}/addresses";
	private static final HttpMethod METHOD = HttpMethod.POST;
		
	public interface CreateCustomerAddressCallback {
		public void onCreateCustomerAddress(Address result);
		public void onFailCreateCustomerAddress(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateCustomerAddress(String merchantId, String token, String customerId, Address address) {
		super(token, new TypeReference<Address>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
		super.setEntity(address);
	}
	
	@Override
	public Address execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateCustomerAddressCallback callback) {
		super.execute(new Callback<Address>() {

			@Override
            public void onReceiveResult(Address result) {
	            if (callback != null) {
	            	callback.onCreateCustomerAddress(result);
	            }
            }

			@Override
            public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	            	callback.onFailCreateCustomerAddress(error);
	            }
            }
			
		});
	}
	
}
