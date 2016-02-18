package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Category;

public class GetCategories extends RESTMethod<WrappedList<Category>> {

	private static final String URL = "/merchants/{merchantId}/categories";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetCategoriesCallback {
	    public void onGetCategories(WrappedList<Category> result);
	    public void onFailGetCategories(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCategories(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<Category>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<Category> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetCategoriesCallback callback) {
	    super.execute(new Callback<WrappedList<Category>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Category> result) {
	            if (callback != null) {
	                callback.onGetCategories(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCategories(error);
	            }
	        }
	    });
	}

}
