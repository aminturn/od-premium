package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Attribute;

public class UpdateAttribute extends RESTMethod<Attribute> {

	private static final String URL = "/merchants/{merchantId}/attributes/{attributeId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateAttributeCallback {
	    public void onUpdateAttribute(Attribute result);
	    public void onFailUpdateAttribute(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateAttribute(String merchantId, String token, String attributeId, Attribute attribute) {
		super(token, new TypeReference<Attribute>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("attributeId", attributeId));
		super.setEntity(attribute);
	}
	
	@Override
	public Attribute execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateAttributeCallback callback) {
	    super.execute(new Callback<Attribute>() {
	        @Override
	        public void onReceiveResult(Attribute result) {
	            if (callback != null) {
	                callback.onUpdateAttribute(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateAttribute(error);
	            }
	        }
	    });
	}

}
