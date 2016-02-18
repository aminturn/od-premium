package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.TaxRateItemAssociation;

public class CreateTaxRateItemAssociations extends RESTMethod<WrappedList<TaxRateItemAssociation>> {

	private static final String URL = "/merchants/{merchantId}/tax_rate_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateTaxRateItemAssociationsCallback {
	    public void onCreateTaxRateItemAssociations(WrappedList<TaxRateItemAssociation> result);
	    public void onFailCreateTaxRateItemAssociations(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public CreateTaxRateItemAssociations(String merchantId, String token, TaxRateItemAssociation... associations) {
		super(token, new TypeReference<WrappedList<TaxRateItemAssociation>>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(new WrappedList<TaxRateItemAssociation>(associations));
	}
	
	@Override
	public WrappedList<TaxRateItemAssociation> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateTaxRateItemAssociationsCallback callback) {
	    super.execute(new Callback<WrappedList<TaxRateItemAssociation>>() {
	        @Override
	        public void onReceiveResult(WrappedList<TaxRateItemAssociation> result) {
	            if (callback != null) {
	                callback.onCreateTaxRateItemAssociations(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateTaxRateItemAssociations(error);
	            }
	        }
	    });
	}

}
