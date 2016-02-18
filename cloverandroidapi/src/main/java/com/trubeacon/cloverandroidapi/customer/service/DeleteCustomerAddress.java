package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.Address;

public class DeleteCustomerAddress extends RESTMethod<Address> {

	private static final String URL = "/merchants/{merchantId}/customers/{customerId}/addresses/{addressId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
		
	public interface DeleteCustomerAddressCallback {
	    public void onDeleteCustomerAddress(Address result);
	    public void onFailDeleteCustomerAddress(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteCustomerAddress(String merchantId, String token, String customerId, String addressId) {
		super(token, new TypeReference<Address>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
		super.addPathParam(Param.path("addressId", addressId));
	}
	
	@Override
	public Address execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteCustomerAddressCallback callback) {
	    super.execute(new Callback<Address>() {
	        @Override
	        public void onReceiveResult(Address result) {
	            if (callback != null) {
	                callback.onDeleteCustomerAddress(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteCustomerAddress(error);
	            }
	        }
	    });
	}

}
