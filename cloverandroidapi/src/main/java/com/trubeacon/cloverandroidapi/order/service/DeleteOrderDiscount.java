package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.Discount;

public class DeleteOrderDiscount extends RESTMethod<Discount> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/discounts/{discountId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteOrderDiscountCallback {
	    public void onDeleteOrderDiscount(Discount result);
	    public void onFailDeleteOrderDiscount(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOrderDiscount(String merchantId, String token, String orderId, String discountId) {
		super(token, new TypeReference<Discount>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.addPathParam(Param.path("discountId", discountId));
	}
	
	@Override
	public Discount execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteOrderDiscountCallback callback) {
	    super.execute(new Callback<Discount>() {
	        @Override
	        public void onReceiveResult(Discount result) {
	            if (callback != null) {
	                callback.onDeleteOrderDiscount(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOrderDiscount(error);
	            }
	        }
	    });
	}

}
