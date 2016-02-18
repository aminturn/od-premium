package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.customer.EmailAddress;

public class DeleteCustomerEmailAddress extends RESTMethod<EmailAddress> {

	private static final String URL = "/merchants/{merchantId}/customers/{customerId}/email_addresses/{emailAddressId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
		
	public interface DeleteCustomerEmailAddressCallback {
	    public void onDeleteCustomerEmailAddress(EmailAddress result);
	    public void onFailDeleteCustomerEmailAddress(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteCustomerEmailAddress(String merchantId, String token, String customerId, String emailAddressId) {
		super(token, new TypeReference<EmailAddress>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
		super.addPathParam(Param.path("emailAddressId", emailAddressId));
	}
	
	@Override
	public EmailAddress execute() throws RESTException {
		return super.execute();
	}

	public void execute(final DeleteCustomerEmailAddressCallback callback) {
	    super.execute(new Callback<EmailAddress>() {
	        @Override
	        public void onReceiveResult(EmailAddress result) {
	            if (callback != null) {
	                callback.onDeleteCustomerEmailAddress(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteCustomerEmailAddress(error);
	            }
	        }
	    });
	}

}
