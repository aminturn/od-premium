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

public class GetDiscount extends RESTMethod<DiscountWrapper> {
	
	private static final String URL = "/merchant/{merchantId}/inventory/discounts/{discountId}";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetDiscountCallback {
		public void onGetDiscount(Discount discount);
		public void onFailGetDiscount(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetDiscount(String merchantId, String token, String discountId) {
		super(token, new TypeReference<DiscountWrapper>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("discountId", discountId));
	}
	
	@Override
	public DiscountWrapper execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetDiscountCallback callback) {
		super.execute(new Callback<DiscountWrapper>() {

			@Override
            public void onReceiveResult(DiscountWrapper result) {
	            if (callback != null) {
	            	callback.onGetDiscount(result);
	            }
            }

			@Override
            public void onReceiveError(Error error) {
	            if (callback != null) {
	            	callback.onFailGetDiscount(error);
	            }
            }
			
		});
	}
	
}
