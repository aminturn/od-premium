package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Category;

public class GetCategory extends RESTMethod<Category> {
	
	private static final String URL = "/merchants/{merchantId}/categories/{categoryId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetCategoryCallback {
	    public void onGetCategory(Category result);
	    public void onFailGetCategory(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetCategory(String merchantId, String token, String categoryId) {
		super(token, new TypeReference<Category>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("categoryId", categoryId));
	}
	
	@Override
	public Category execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetCategoryCallback callback) {
	    super.execute(new Callback<Category>() {
	        @Override
	        public void onReceiveResult(Category result) {
	            if (callback != null) {
	                callback.onGetCategory(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetCategory(error);
	            }
	        }
	    });
	}

}
