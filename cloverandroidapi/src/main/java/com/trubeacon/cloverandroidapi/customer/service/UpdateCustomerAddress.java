package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.Address;

public class UpdateCustomerAddress extends RESTMethod<Address> {

	private static final String URL = "/merchants/{merchantId}/customers/{customerId}/addresses/{addressId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
		
	public interface UpdateCustomerAddressCallback {
	    public void onUpdateCustomerAddress(Address result);
	    public void onFailUpdateCustomerAddress(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateCustomerAddress(String merchantId, String token, String customerId, String addressId, Address address) {
		super(token, new TypeReference<Address>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
		super.addPathParam(Param.path("addressId", addressId));
		super.setEntity(address);
	}
	
	@Override
	public Address execute() throws RESTException {
		return super.execute();
	}

	public void execute(final UpdateCustomerAddressCallback callback) {
	    super.execute(new Callback<Address>() {
	        @Override
	        public void onReceiveResult(Address result) {
	            if (callback != null) {
	                callback.onUpdateCustomerAddress(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateCustomerAddress(error);
	            }
	        }
	    });
	}

}
