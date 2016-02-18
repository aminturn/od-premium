package com.trubeacon.cloverandroidapi.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.customer.EmailAddress;

public class CreateCustomerEmailAddress extends RESTMethod<EmailAddress> {

	private static final String URL = "/merchants/{merchantId}/customers/{customerId}/email_addresses";
	private static final HttpMethod METHOD = HttpMethod.POST;

	public interface CreateCustomerEmailAddressCallback {
		public void onCreateCustomerEmailAddress(EmailAddress result);
		public void onFailCreateCustomerEmailAddress(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateCustomerEmailAddress(String merchantId, String token, String customerId, EmailAddress emailAddress) {
		super(token, new TypeReference<EmailAddress>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("customerId", customerId));
		super.setEntity(emailAddress);
	}
	
	@Override
	public EmailAddress execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateCustomerEmailAddressCallback callback) {
		super.execute(new Callback<EmailAddress>() {

			@Override
            public void onReceiveResult(EmailAddress result) {
	            if (callback != null) {
	            	callback.onCreateCustomerEmailAddress(result);
	            }
            }

			@Override
            public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	            	callback.onFailCreateCustomerEmailAddress(error);
	            }
            }
			
		});
	}
	
}
