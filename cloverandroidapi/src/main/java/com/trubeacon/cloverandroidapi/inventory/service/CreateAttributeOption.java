package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Option;
import com.trubeacon.cloverandroidapi.client.error.Error;

public class CreateAttributeOption extends RESTMethod<Option> {
	
	private static final String URL = "/merchants/{merchantId}/attributes/{attributeId}/options";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateAttributeOptionCallback {
		public void onCreateAttributeOption(Option result);
		public void onFailCreateAttributeOption(Error error);
	}
	
	public CreateAttributeOption(String merchantId, String token, String attributeId, Option option) {
		super(token, new TypeReference<Option>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("attributeId", attributeId));
		super.setEntity(option);
	}
	
	@Override
	public Option execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateAttributeOptionCallback callback) {
		super.execute(new Callback<Option>() {

			@Override
            public void onReceiveResult(Option result) {
	            if (callback != null) {
	            	callback.onCreateAttributeOption(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailCreateAttributeOption(error);
	            }
            }
			
		});
	}

}
