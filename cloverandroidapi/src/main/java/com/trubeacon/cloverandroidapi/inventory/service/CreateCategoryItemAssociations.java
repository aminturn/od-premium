package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.CategoryItemAssociation;
import com.trubeacon.cloverandroidapi.client.error.Error;

public class CreateCategoryItemAssociations extends RESTMethod<WrappedList<CategoryItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/category_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateCategoryItemAssociationsCallback {
		public void onCreateCategoryItemAssociations(WrappedList<CategoryItemAssociation> result);
		public void onFailCreateCategoryItemAssociations(Error error);
	}
	
	public CreateCategoryItemAssociations(String merchantId, String token, CategoryItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<CategoryItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(new WrappedList<CategoryItemAssociation>(associations));
	}
	
	@Override
	public WrappedList<CategoryItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateCategoryItemAssociationsCallback callback) {
		super.execute(new Callback<WrappedList<CategoryItemAssociation>>() {

			@Override
            public void onReceiveResult(WrappedList<CategoryItemAssociation> result) {
	            if (callback != null) {
	            	callback.onCreateCategoryItemAssociations(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailCreateCategoryItemAssociations(error);
	            }
            }
			
		});
	}
	
}
