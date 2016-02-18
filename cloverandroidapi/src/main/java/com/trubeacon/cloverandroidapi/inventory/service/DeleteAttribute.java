package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Attribute;

public class DeleteAttribute extends RESTMethod<Attribute> {
	
	private static final String URL = "/merchants/{merchantId}/attributes/{attributeId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteAttributeCallback {
	    public void onDeleteAttribute(Attribute result);
	    public void onFailDeleteAttribute(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteAttribute(String merchantId, String token, String attributeId) {
		super(token, new TypeReference<Attribute>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("attributeId", attributeId));
	}
	
	@Override
	public Attribute execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteAttributeCallback callback) {
	    super.execute(new Callback<Attribute>() {
	        @Override
	        public void onReceiveResult(Attribute result) {
	            if (callback != null) {
	                callback.onDeleteAttribute(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteAttribute(error);
	            }
	        }
	    });
	}

}
