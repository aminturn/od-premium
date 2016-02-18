package com.trubeacon.cloverandroidapi.employee.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.order.Order;

public class GetEmployeeOrders extends RESTMethod<WrappedList<Order>> {

	private static final String URL = "/merchants/{merchantId}/employees/{employeeId}/orders";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetEmployeeOrdersCallback {
	    public void onGetEmployeeOrders(WrappedList<Order> result);
	    public void onFailGetEmployeeOrders(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetEmployeeOrders(String merchantId, String token, String employeeId, Object... params) {
		super(token, new TypeReference<WrappedList<Order>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("employeeId", employeeId));
	}
	
	@Override
	public WrappedList<Order> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetEmployeeOrdersCallback callback) {
	    super.execute(new Callback<WrappedList<Order>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Order> result) {
	            if (callback != null) {
	                callback.onGetEmployeeOrders(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetEmployeeOrders(error);
	            }
	        }
	    });
	}

}
