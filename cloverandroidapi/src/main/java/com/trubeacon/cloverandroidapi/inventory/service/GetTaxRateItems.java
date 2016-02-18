package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Item;

public class GetTaxRateItems extends RESTMethod<WrappedList<Item>> {

	private static final String URL = "/merchants/{merchantId}/tax_rates/{taxRateId}/items";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTaxRateItemsCallback {
	    public void onGetTaxRateItems(WrappedList<Item> result);
	    public void onFailGetTaxRateItems(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTaxRateItems(String merchantId, String token, String taxRateId, Object... params) {
		super(token, new TypeReference<WrappedList<Item>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("taxRateId", taxRateId));
	}
	
	@Override
	public WrappedList<Item> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTaxRateItemsCallback callback) {
	    super.execute(new Callback<WrappedList<Item>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Item> result) {
	            if (callback != null) {
	                callback.onGetTaxRateItems(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTaxRateItems(error);
	            }
	        }
	    });
	}

}
