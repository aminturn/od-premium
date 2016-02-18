package com.trubeacon.cloverandroidapi.inventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.inventory.ItemStock;

public class UpdateItemStock extends RESTMethod<ItemStock> {

	private static final String URL = "/merchants/{merchantId}/item_stocks/{itemStockId}";
	private static final HttpMethod METHOD = HttpMethod.POST;
	
	public interface UpdateItemStockCallback {
	    public void onUpdateItemStock(ItemStock result);
	    public void onFailUpdateItemStock(com.trubeacon.cloverandroidapi.client.error.Error error);
	}

	public UpdateItemStock(String merchantId, String token, String itemStockId, ItemStock itemStock) {
		super(token, new TypeReference<ItemStock>(){}, CloverService.getBaseUrl() + URL, METHOD);
		super.addPathParam(Param.path("merchantId", merchantId));
		super.addPathParam(Param.path("itemStockId", itemStockId));
		super.setEntity(itemStock);
	}
	
	@Override
	public ItemStock execute() throws RESTException {
		return super.execute();
	}
	
	public void execute(final UpdateItemStockCallback callback) {
	    super.execute(new Callback<ItemStock>() {
	        @Override
	        public void onReceiveResult(ItemStock result) {
	            if (callback != null) {
	                callback.onUpdateItemStock(result);
	            }
	        }
	        @Override
	        public void onReceiveError(com.trubeacon.cloverandroidapi.client.error.Error error) {
	            if (callback != null) {
	                callback.onFailUpdateItemStock(error);
	            }
	        }
	    });
	}

}
