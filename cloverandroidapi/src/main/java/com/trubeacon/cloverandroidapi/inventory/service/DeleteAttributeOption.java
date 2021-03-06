package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Option;

public class DeleteAttributeOption extends RESTMethod<Option> {

	private static final String URL = "/merchants/{merchantId}/attributes/{attributeId}/options/{optionId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteAttributeOptionCallback {
	    public void onDeleteAttributeOption(Option result);
	    public void onFailDeleteAttributeOption(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteAttributeOption(String merchantId, String token, String attributeId, String optionId) {
		super(token, new TypeReference<Option>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("attributeId", attributeId));
		super.addPathParam(Param.path("optionId", optionId));
	}
	
	@Override
	public Option execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteAttributeOptionCallback callback) {
	    super.execute(new Callback<Option>() {
	        @Override
	        public void onReceiveResult(Option result) {
	            if (callback != null) {
	                callback.onDeleteAttributeOption(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteAttributeOption(error);
	            }
	        }
	    });
	}
	
}
