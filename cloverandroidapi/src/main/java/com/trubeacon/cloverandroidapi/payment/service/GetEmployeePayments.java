package com.trubeacon.cloverandroidapi.payment.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.Payment;

public class GetEmployeePayments extends RESTMethod<WrappedList<Payment>> {

	private static final String URL = "/merchants/{merchantId}/employees/{employeeId}/payments";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetEmployeePaymentsCallback {
	    public void onGetEmployeePayments(WrappedList<Payment> result);
	    public void onFailGetEmployeePayments(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetEmployeePayments(String merchantId, String token, String employeeId, Object... params) {
		super(token, new TypeReference<WrappedList<Payment>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("employeeId", employeeId));
	}
	
	@Override
	public WrappedList<Payment> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetEmployeePaymentsCallback callback) {
	    super.execute(new Callback<WrappedList<Payment>>() {
	        @Override
	        public void onReceiveResult(WrappedList<Payment> result) {
	            if (callback != null) {
	                callback.onGetEmployeePayments(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetEmployeePayments(error);
	            }
	        }
	    });
	}

}
