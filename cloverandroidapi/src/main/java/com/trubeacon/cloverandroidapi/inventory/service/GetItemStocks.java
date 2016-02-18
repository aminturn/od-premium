package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.ItemStock;

public class GetItemStocks extends RESTMethod<WrappedList<ItemStock>> {

	private static final String URL = "/merchants/{merchantId}/item_stocks";
	private static final HttpMethod METHOD = HttpMethod.GET;
	
	public interface GetItemStocksCallback {
	    public void onGetItemStocks(WrappedList<ItemStock> result);
	    public void onFailGetItemStocks(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public GetItemStocks(String merchantId, String token, Object... params) {
		super(token, new TypeReference<WrappedList<ItemStock>>(){}, CloverService.getBaseUrl() + URL, METHOD, params);
		super.addPathParam(Param.path("merchantId", merchantId));
	}
	
	@Override
	public WrappedList<ItemStock> execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final GetItemStocksCallback callback) {
	    super.execute(new Callback<WrappedList<ItemStock>>() {
	        @Override
	        public void onReceiveResult(WrappedList<ItemStock> result) {
	            if (callback != null) {
	                callback.onGetItemStocks(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailGetItemStocks(error);
	            }
	        }
	    });
	}

}
