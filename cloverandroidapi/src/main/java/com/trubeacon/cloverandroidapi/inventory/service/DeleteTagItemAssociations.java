package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.TagItemAssociation;

// FIXME: what is the actual return type???
public class DeleteTagItemAssociations extends RESTMethod<WrappedList<TagItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/tag_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface DeleteTagItemAssociationsCallback {
	    public void onDeleteTagItemAssociations(WrappedList<TagItemAssociation> result);
	    public void onFailDeleteTagItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteTagItemAssociations(String merchantId, String token, TagItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<TagItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.setEntity(new WrappedList<TagItemAssociation>(associations));
		super.addPathParam(Param.path("merchantId", merchantId));
		// why use proper HTTP verbs when you can add a retarded query param??? ugh
		super.addQueryParam(Param.query("delete", "true"));
	}
	
	@Override
	public WrappedList<TagItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteTagItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<TagItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<TagItemAssociation> result) {
	            if (callback != null) {
	                callback.onDeleteTagItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteTagItemAssociations(error);
	            }
	        }
	    });
	}

}
