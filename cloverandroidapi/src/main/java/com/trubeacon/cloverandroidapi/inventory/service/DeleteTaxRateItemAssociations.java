package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.TaxRateItemAssociation;

public class DeleteTaxRateItemAssociations extends RESTMethod<WrappedList<TaxRateItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/tax_rate_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface DeleteTaxRateItemAssociationsCallback {
	    public void onDeleteTaxRateItemAssociations(WrappedList<TaxRateItemAssociation> result);
	    public void onFailDeleteTaxRateItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteTaxRateItemAssociations(String merchantId, String token, TaxRateItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<TaxRateItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.setEntity(new WrappedList<TaxRateItemAssociation>(associations));
		super.addPathParam(Param.path("merchantId", merchantId));
		// why use proper HTTP verbs when you can add a retarded query param??? ugh
		super.addQueryParam(Param.query("delete", "true"));
	}
	
	@Override
	public WrappedList<TaxRateItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteTaxRateItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<TaxRateItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<TaxRateItemAssociation> result) {
	            if (callback != null) {
	                callback.onDeleteTaxRateItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteTaxRateItemAssociations(error);
	            }
	        }
	    });
	}

}
