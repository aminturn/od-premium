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

public class GetOptions extends RESTMethod<WrappedList<Option>> {

	private static final String URL = "/merchants/{merchantId}/options";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetOptionsCallback {
	    public void onGetOptions(WrappedList<Option> result);
	    public void onFailGetOptions(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetOptions(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Option>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Option> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetOptionsCallback callback) {
	    super.execute(new Callback<WrappedList<Option>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Option> result) {
	            if (callback != null) {
	                callback.onGetOptions(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetOptions(error);
	            }
	        }
	    });
	}

}
