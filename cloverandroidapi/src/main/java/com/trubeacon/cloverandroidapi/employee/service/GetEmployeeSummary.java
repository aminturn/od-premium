package com.trubeacon.cloverandroidapi.employee.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.EmployeeSummary;

public class GetEmployeeSummary extends RESTMethod<WrappedList<EmployeeSummary>> {
	
	private static final String URL = "/merchants/{merchantId}/summaries/employees";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetEmployeeSummaryCallback {
	    public void onGetEmployeeSummary(WrappedList<EmployeeSummary> result);
	    public void onFailGetEmployeeSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetEmployeeSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<EmployeeSummary>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", mId));
	}
	
	@Override
	public WrappedList<EmployeeSummary> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetEmployeeSummaryCallback callback) {
	    super.execute(new Callback<WrappedList<EmployeeSummary>>() {
	        @Override
	        public void onReceiveResult(WrappedList<EmployeeSummary> result) {
	            if (callback != null) {
	                callback.onGetEmployeeSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetEmployeeSummary(error);
	            }
	        }
	    });
	}

}
