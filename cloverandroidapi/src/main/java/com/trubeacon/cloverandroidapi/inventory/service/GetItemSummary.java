package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;
import com.trubeacon.cloverandroidapi.inventory.ItemSummary;

public class GetItemSummary extends RESTMethod<ItemSummary> {

	// holy shit does this method make absolutely no sense.
	// Why is this a POST instead of a GET? 
	// Why are query params posted instead of placed as query params? 
	// Why are credentials placed in query params?
	// Why is it named totally different than every other aggregation service?
	// Go home Clover, you're drunk
	
	private static final String COMPLETE_URL = "https://www.clover.com/cos/v1/dashboard/merchant/report_items";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface GetItemSummaryCallback {
	    public void onGetItemSummary(ItemSummary result);
	    public void onFailGetItemSummary(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	public GetItemSummary(String mId, String token, Object... params) {
		super(token, new TypeReference<ItemSummary>(){}, COMPLETE_URL, METHOD);
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
	public ItemSummary execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetItemSummaryCallback callback) {
	    super.execute(new Callback<ItemSummary>() {
	        @Override
	        public void onReceiveResult(ItemSummary result) {
	            if (callback != null) {
	                callback.onGetItemSummary(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetItemSummary(error);
	            }
	        }
	    });
	}

}
