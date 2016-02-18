package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;

public class CreateTaxRate extends RESTMethod<TaxRate> {

	private static final String URL = "/merchants/{merchantId}/tax_rates";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateTaxRateCallback {
	    public void onCreateTaxRate(TaxRate result);
	    public void onFailCreateTaxRate(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public CreateTaxRate(String merchantId, String token, TaxRate taxRate) {
		super(token, new TypeReference<TaxRate>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(taxRate);
	}
	
	@Override
	public TaxRate execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateTaxRateCallback callback) {
	    super.execute(new Callback<TaxRate>() {
	        @Override
	        public void onReceiveResult(TaxRate result) {
	            if (callback != null) {
	                callback.onCreateTaxRate(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateTaxRate(error);
	            }
	        }
	    });
	}

}
