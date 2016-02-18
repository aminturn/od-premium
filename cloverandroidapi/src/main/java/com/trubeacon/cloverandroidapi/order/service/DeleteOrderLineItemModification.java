package com.trubeacon.cloverandroidapi.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.order.Modification;

public class DeleteOrderLineItemModification extends RESTMethod<Modification> {

	private static final String URL = "/merchants/{merchantId}/orders/{orderId}/line_items/{lineItemId}/modifications/{modificationId}";
	private static final HttpMethod METHOD = HttpMethod.DELETE;
	
	public interface DeleteOrderLineItemModificationCallback {
	    public void onDeleteOrderLineItemModification(Modification result);
	    public void onFailDeleteOrderLineItemModification(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public DeleteOrderLineItemModification(String merchantId, String token, String orderId, String lineItemId, String modificationId) {
		super(token, new TypeReference<Modification>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("orderId", orderId));
		super.addPathParam(Param.path("lineItemId", lineItemId));
		super.addPathParam(Param.path("modificationId", modificationId));
	}
	
	@Override
	public Modification execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final DeleteOrderLineItemModificationCallback callback) {
	    super.execute(new Callback<Modification>() {
	        @Override
	        public void onReceiveResult(Modification result) {
	            if (callback != null) {
	                callback.onDeleteOrderLineItemModification(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailDeleteOrderLineItemModification(error);
	            }
	        }
	    });
	}

}
