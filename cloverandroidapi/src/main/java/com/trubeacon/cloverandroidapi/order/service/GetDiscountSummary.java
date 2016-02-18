package com.trubeacon.cloverandroidapi.order.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;
import com.trubeacon.cloverandroidapi.order.DiscountSummary;

public class GetDiscountSummary extends RESTMethod<DiscountSummary> {

	// holy shit does this method make absolutely no sense.
	// Why is this a POST instead of a GET? 
	// Why are query params posted instead of placed as query params? 
	// Why are credentials placed in query params?
	// Why is it named totally different than every other aggregation service?
	// Go home Clover, you're drunk
	
	private static final String COMPLETE_URL = "https://www.clover.com/cos/v1/dashboard/merchant/report_discounts";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface GetDiscountSummaryCallback {
	    public void onGetDiscountSummary(DiscountSummary result);
	    public void onFailGetDiscountSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetDiscountSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<DiscountSummary>(){}, COMPLETE_URL, METHOD);
		super.addQueryParam(Param.query("companyType", "merchant"));
		super.addQueryParam(Param.query("companyId", mId));
		super.addQueryParam(Param.query("_csrfToken", token));
		
		if (params != null) {
			// params are mapped to an object that is posted, how insanely fucking stupid
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (Object param : params) {
				if (param instanceof QueryParam) {
					paramMap.put(((QueryParam) param).getKey(), ((QueryParam) param).getValue());
				}
			}	
			super.setEntity(paramMap);
		}
		
	}
	
	@Override
	public DiscountSummary execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetDiscountSummaryCallback callback) {
	    super.execute(new Callback<DiscountSummary>() {
	        @Override
	        public void onReceiveResult(DiscountSummary result) {
	            if (callback != null) {
	                callback.onGetDiscountSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetDiscountSummary(error);
	            }
	        }
	    });
	}

}
