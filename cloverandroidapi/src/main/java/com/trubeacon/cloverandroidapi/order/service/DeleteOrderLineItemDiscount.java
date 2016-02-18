package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.Discount;

public class DeleteOrderLineItemDiscount extends RESTMethod<Discount> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/line_items/{lineItemId}/discounts/{discountId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteOrderLineItemDiscountCallback {
	    public void onDeleteOrderLineItemDiscount(Discount result);
	    public void onFailDeleteOrderLineItemDiscount(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOrderLineItemDiscount(String merchantId, String token, String orderId, String lineItemId, String discountId) {
		super(token, new TypeReference<Discount>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.addPathParam(Param.path("lineItemId", lineItemId));
		super.addPathParam(Param.path("discountId", discountId));
	}
	
	@Override
	public Discount execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteOrderLineItemDiscountCallback callback) {
	    super.execute(new Callback<Discount>() {
	        @Override
	        public void onReceiveResult(Discount result) {
	            if (callback != null) {
	                callback.onDeleteOrderLineItemDiscount(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOrderLineItemDiscount(error);
	            }
	        }
	    });
	}

}
