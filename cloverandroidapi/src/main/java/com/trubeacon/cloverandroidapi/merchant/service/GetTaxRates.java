package com.trubeacon.cloverandroidapi.merchant.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;

public class GetTaxRates extends RESTMethod<WrappedList<TaxRate>> {

	private static final String URL = "/merchants/{merchantId}/tax_rates";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetTaxRatesCallback {
	    public void onGetTaxRates(WrappedList<TaxRate> result);
	    public void onFailGetTaxRates(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetTaxRates(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<TaxRate>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<TaxRate> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetTaxRatesCallback callback) {
	    super.execute(new Callback<WrappedList<TaxRate>>() {
	        @Override
	        public void onReceiveResult(WrappedList<TaxRate> result) {
	            if (callback != null) {
	                callback.onGetTaxRates(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetTaxRates(error);
	            }
	        }
	    });
	}

}
