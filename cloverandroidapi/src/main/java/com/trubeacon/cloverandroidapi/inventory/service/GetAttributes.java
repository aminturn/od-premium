package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Attribute;

public class GetAttributes extends RESTMethod<WrappedList<Attribute>> {

	private static final String URL = "/merchants/{merchantId}/attributes";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetAttributesCallback {
	    public void onGetAttributes(WrappedList<Attribute> result);
	    public void onFailGetAttributes(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetAttributes(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Attribute>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Attribute> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetAttributesCallback callback) {
	    super.execute(new Callback<WrappedList<Attribute>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Attribute> result) {
	            if (callback != null) {
	                callback.onGetAttributes(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetAttributes(error);
	            }
	        }
	    });
	}

}
