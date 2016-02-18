package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Attribute;

public class GetAttribute extends RESTMethod<Attribute> {
	
	private static final String URL = "/merchants/{merchantId}/attributes/{attributeId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetAttributeCallback {
	    public void onGetAttribute(Attribute result);
	    public void onFailGetAttribute(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetAttribute(String merchantId, String token, String attributeId) {
		super(token, new TypeReference<Attribute>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("attributeId", attributeId));
	}
	
	@Override
	public Attribute execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetAttributeCallback callback) {
	    super.execute(new Callback<Attribute>() {
	        @Override
	        public void onReceiveResult(Attribute result) {
	            if (callback != null) {
	                callback.onGetAttribute(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetAttribute(error);
	            }
	        }
	    });
	}

}
