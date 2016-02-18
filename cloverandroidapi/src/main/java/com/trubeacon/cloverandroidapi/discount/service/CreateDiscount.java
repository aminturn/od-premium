package com.trubeacon.cloverandroidapi.discount.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.Version;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.discount.Discount;
import com.trubeacon.cloverandroidapi.discount.DiscountWrapper;

public class CreateDiscount extends RESTMethod<DiscountWrapper> {

	private static final String URL = "/merchant/{merchantId}/inventory/discounts";
	private static final HttpMethod METHOD = HttpMethod.POST;

	public interface CreateDiscountCallback {
		public void onCreateDiscount(Discount discount);
		public void onFailCreateDiscount(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateDiscount(String merchantId, String token, Discount discount) {
		super(token, new TypeReference<DiscountWrapper>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.setEntity(new DiscountWrapper(discount));
	}
	
	@Override
	public DiscountWrapper execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateDiscountCallback callback) {
		super.execute(new Callback<DiscountWrapper>() {

			@Override
            public void onReceiveResult(DiscountWrapper result) {
	            if (callback != null) {
	            	callback.onCreateDiscount(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailCreateDiscount(error);
	            }
            }
			
		});
	}
	
}
