package com.trubeacon.cloverandroidapi.merchant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.OrderType;

public class UpdateOrderType extends RESTMethod<OrderType> {

	private static final String URL = "/merchants/{merchantId}/order_types/{orderTypeId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateOrderTypeCallback {
	    public void onUpdateOrderType(OrderType result);
	    public void onFailUpdateOrderType(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateOrderType(String merchantId, String token, String orderTypeId, OrderType orderType) {
		super(token, new TypeReference<OrderType>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderTypeId", orderTypeId));
		super.setEntity(orderType);
	}
	
	@Override
	public OrderType execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateOrderTypeCallback callback) {
	    super.execute(new Callback<OrderType>() {
	        @Override
	        public void onReceiveResult(OrderType result) {
	            if (callback != null) {
	                callback.onUpdateOrderType(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateOrderType(error);
	            }
	        }
	    });
	}

}
