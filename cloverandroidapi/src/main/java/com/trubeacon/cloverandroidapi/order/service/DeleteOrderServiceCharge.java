package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.payment.ServiceCharge;

public class DeleteOrderServiceCharge extends RESTMethod<ServiceCharge> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/service_charge/{serviceChargeId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteOrderServiceChargeCallback {
	    public void onDeleteOrderServiceCharge(ServiceCharge result);
	    public void onFailDeleteOrderServiceCharge(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOrderServiceCharge(String merchantId, String token, String orderId, String serviceChargeId) {
		super(token, new TypeReference<ServiceCharge>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.addPathParam(Param.path("serviceChargeId", serviceChargeId));
	}
	
	@Override
	public ServiceCharge execute() throws RESTException {
		return super.execute();
	}

	public void execute(final DeleteOrderServiceChargeCallback callback) {
	    super.execute(new Callback<ServiceCharge>() {
	        @Override
	        public void onReceiveResult(ServiceCharge result) {
	            if (callback != null) {
	                callback.onDeleteOrderServiceCharge(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOrderServiceCharge(error);
	            }
	        }
	    });
	}

}
