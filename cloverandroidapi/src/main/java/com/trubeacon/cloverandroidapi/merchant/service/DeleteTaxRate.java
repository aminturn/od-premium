package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;

public class DeleteTaxRate extends RESTMethod<TaxRate> {

	private static final String URL = "/merchants/{merchantId}/tax_rates/{taxRateId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteTaxRateCallback {
	    public void onDeleteTaxRate(TaxRate result);
	    public void onFailDeleteTaxRate(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteTaxRate(String merchantId, String token, String taxRateId) {
		super(token, new TypeReference<TaxRate>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("taxRateId", taxRateId));
	}
	
	@Override
	public TaxRate execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteTaxRateCallback callback) {
	    super.execute(new Callback<TaxRate>() {
	        @Override
	        public void onReceiveResult(TaxRate result) {
	            if (callback != null) {
	                callback.onDeleteTaxRate(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteTaxRate(error);
	            }
	        }
	    });
	}

}
