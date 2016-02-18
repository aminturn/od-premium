package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.CategoryItemAssociation;

public class DeleteCategoryItemAssociations extends RESTMethod<WrappedList<CategoryItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/category_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface DeleteCategoryItemAssociationsCallback {
	    public void onDeleteCategoryItemAssociations(WrappedList<CategoryItemAssociation> result);
	    public void onFailDeleteCategoryItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteCategoryItemAssociations(String merchantId, String token, CategoryItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<CategoryItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.setEntity(new WrappedList<CategoryItemAssociation>(associations));
		super.addPathParam(Param.path("merchantId", merchantId));
		// why use proper HTTP verbs when you can add a retarded query param??? ugh
		super.addQueryParam(Param.query("delete", "true"));
	}
	
	@Override
	public WrappedList<CategoryItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteCategoryItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<CategoryItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<CategoryItemAssociation> result) {
	            if (callback != null) {
	                callback.onDeleteCategoryItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteCategoryItemAssociations(error);
	            }
	        }
	    });
	}

}
