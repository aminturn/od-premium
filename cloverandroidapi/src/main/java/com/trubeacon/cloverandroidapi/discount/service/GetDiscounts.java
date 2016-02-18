package com.trubeacon.cloverandroidapi.discount.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.Version;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.discount.Discounts;

public class GetDiscounts extends RESTMethod<Discounts> {

	private static final String URL = "/merchant/{merchantId}/inventory/discounts";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetDiscountsCallback {
		public void onGetDiscounts(Discounts discounts);
		public void onFailGetDiscounts(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetDiscounts(String merchantId, String token, Object... params) {
		super(token, new TypeReference<Discounts>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public Discounts execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetDiscountsCallback callback) {
		super.execute(new Callback<Discounts>() {

			@Override
            public void onReceiveResult(Discounts result) {
	            if (callback != null) {
	            	callback.onGetDiscounts(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailGetDiscounts(error);
	            }
            }
			
		});
	}
	
}
