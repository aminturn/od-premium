package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Option;

public class GetAttributeOptions extends RESTMethod<WrappedList<Option>> {
	
	private static final String URL = "/merchants/{merchantId}/attributes/{attributeId}/options";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetAttributeOptionsCallback {
	    public void onGetAttributeOptions(WrappedList<Option> result);
	    public void onFailGetAttributeOptions(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetAttributeOptions(String merchantId, String token, String attributeId, Object... params) {
		super(token, new TypeReference<WrappedList<Option>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("attributeId", attributeId));
	}
	
	@Override
	public WrappedList<Option> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetAttributeOptionsCallback callback) {
	    super.execute(new Callback<WrappedList<Option>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Option> result) {
	            if (callback != null) {
	                callback.onGetAttributeOptions(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetAttributeOptions(error);
	            }
	        }
	    });
	}

}
