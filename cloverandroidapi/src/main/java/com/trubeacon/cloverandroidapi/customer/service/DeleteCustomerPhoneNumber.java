package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.customer.PhoneNumber;

public class DeleteCustomerPhoneNumber extends RESTMethod<PhoneNumber> {
	
	private static final String URL = "/merchants/{merchantId}/customers/{customerId}/phone_numbers/{phoneNumberId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
		
	public interface DeleteCustomerPhoneNumberCallback {
	    public void onDeleteCustomerPhoneNumber(PhoneNumber result);
	    public void onFailDeleteCustomerPhoneNumber(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteCustomerPhoneNumber(String merchantId, String token, String customerId, String phoneNumberId) {
		super(token, new TypeReference<PhoneNumber>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
		super.addPathParam(Param.path("phoneNumberId", phoneNumberId));
	}
	
	@Override
	public PhoneNumber execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteCustomerPhoneNumberCallback callback) {
	    super.execute(new Callback<PhoneNumber>() {
	        @Override
	        public void onReceiveResult(PhoneNumber result) {
	            if (callback != null) {
	                callback.onDeleteCustomerPhoneNumber(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteCustomerPhoneNumber(error);
	            }
	        }
	    });
	}

}
