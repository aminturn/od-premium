package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Attribute;
import com.trubeacon.cloverandroidapi.client.error.Error;

public class CreateAttribute extends RESTMethod<Attribute> {

	private static final String URL = "/merchants/{merchantId}/attributes";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateAttributeCallback {
		public void onCreateAttribute(Attribute result);
		public void onFailCreateAttribute(Error error);
	}
	
	public CreateAttribute(String merchantId, String token, Attribute attribute) {
		super(token, new TypeReference<Attribute>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(attribute);
	}
	
	@Override
	public Attribute execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateAttributeCallback callback) {
		super.execute(new Callback<Attribute>() {

			@Override
            public void onReceiveResult(Attribute result) {
	            if (callback != null) {
	            	callback.onCreateAttribute(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailCreateAttribute(error);
	            }
            }
			
		});
	}
	
}
