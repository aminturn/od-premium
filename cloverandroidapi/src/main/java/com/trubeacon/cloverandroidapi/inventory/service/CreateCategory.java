package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.Category;
import com.trubeacon.cloverandroidapi.client.error.Error;

public class CreateCategory extends RESTMethod<Category> {

	private static final String URL = "/merchants/{merchantId}/categories";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateCategoryCallback {
		public void onCreateCategory(Category result);
		public void onFailCreateCategory(Error error);
	}
	
	public CreateCategory(String merchantId, String token, Category category) {
		super(token, new TypeReference<Category>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(category);
	}
	
	@Override
	public Category execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateCategoryCallback callback) {
		super.execute(new Callback<Category>() {

			@Override
            public void onReceiveResult(Category result) {
	            if (callback != null) {
	            	callback.onCreateCategory(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailCreateCategory(error);
	            }
            }
			
		});
	}
	
}
