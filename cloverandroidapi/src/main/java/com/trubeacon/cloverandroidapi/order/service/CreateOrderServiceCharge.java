package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.ServiceCharge;

public class CreateOrderServiceCharge extends RESTMethod<ServiceCharge> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/service_charge";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface CreateOrderServiceChargeCallback {
	    public void onCreateOrderServiceCharge(ServiceCharge result);
	    public void onFailCreateOrderServiceCharge(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public CreateOrderServiceCharge(String merchantId, String token, String orderId, ServiceCharge serviceCharge) {
		super(token, new TypeReference<ServiceCharge>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.setEntity(serviceCharge);
	}
	
	@Override
	public ServiceCharge execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final CreateOrderServiceChargeCallback callback) {
	    super.execute(new Callback<ServiceCharge>() {
	        @Override
	        public void onReceiveResult(ServiceCharge result) {
	            if (callback != null) {
	                callback.onCreateOrderServiceCharge(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailCreateOrderServiceCharge(error);
	            }
	        }
	    });
	}
	
}
