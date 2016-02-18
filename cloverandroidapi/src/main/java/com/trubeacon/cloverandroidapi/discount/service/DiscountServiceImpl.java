package com.trubeacon.cloverandroidapi.discount.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.discount.Discount;
import com.trubeacon.cloverandroidapi.discount.Discounts;
import com.trubeacon.cloverandroidapi.discount.service.CreateDiscount.CreateDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.DeleteDiscount.DeleteDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.GetDiscount.GetDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.GetDiscounts.GetDiscountsCallback;
import com.trubeacon.cloverandroidapi.discount.service.UpdateDiscount.UpdateDiscountCallback;

public class DiscountServiceImpl implements DiscountService, GetDiscountsCallback, GetDiscountCallback, CreateDiscountCallback, UpdateDiscountCallback, DeleteDiscountCallback {

	private GetDiscountsCallback getDiscountsCallback;
	private GetDiscountCallback getDiscountCallback;
	private CreateDiscountCallback createDiscountCallback;
	private UpdateDiscountCallback updateDiscountCallback;
	private DeleteDiscountCallback deleteDiscountCallback;
	
	@Override
    public List<Discount> getDiscounts(String merchantId, String token, Object... params) throws RESTException {
	    return new GetDiscounts(merchantId, token, params).execute();
    }
	
	@Override
    public void getDiscounts(String merchantId, String token, GetDiscountsCallback callback, Object... params) {
		this.getDiscountsCallback = callback;
	    new GetDiscounts(merchantId, token, params).execute(this);
	}

	@Override
    public Discount getDiscount(String merchantId, String token, String id) throws RESTException {
	    return new GetDiscount(merchantId, token, id).execute();
    }
	
	@Override
    public void getDiscount(String merchantId, String token, String id, GetDiscountCallback callback) {
		this.getDiscountCallback = callback;
	    new GetDiscount(merchantId, token, id).execute(this);
    }

	@Override
    public Discount createDiscount(String merchantId, String token, Discount discount) throws RESTException {
	    return new CreateDiscount(merchantId, token, discount).execute();
    }
	
	@Override
    public void createDiscount(String merchantId, String token, Discount discount, CreateDiscountCallback callback) {
		this.createDiscountCallback = callback;
	    new CreateDiscount(merchantId, token, discount).execute(this);
    }

	@Override
    public Discount updateDiscount(String merchantId, String token, String id, Discount discount)  throws RESTException {
	    return new UpdateDiscount(merchantId, token, id, discount).execute();
    }
	
	@Override
    public void updateDiscount(String merchantId, String token, String id, Discount discount, UpdateDiscountCallback callback) {
		this.updateDiscountCallback = callback;
	    new UpdateDiscount(merchantId, token, id, discount).execute(this);
    }
	
	@Override
    public Discount deleteDiscount(String merchantId, String token, String id) throws RESTException {
	    return new DeleteDiscount(merchantId, token, id).execute();
    }
	
	@Override
    public void deleteDiscount(String merchantId, String token, String id, DeleteDiscountCallback callback) {
		this.deleteDiscountCallback = callback;
	    new DeleteDiscount(merchantId, token, id).execute(this);
    }
	
	@Override
    public void onDeleteDiscount(Discount discount) {
	    if (this.deleteDiscountCallback != null) {
	    	this.deleteDiscountCallback.onDeleteDiscount(discount);
	    }
    }

	@Override
    public void onFailDeleteDiscount(Error error) {
	    if (this.deleteDiscountCallback != null) {
	    	this.deleteDiscountCallback.onFailDeleteDiscount(error);
	    }
    }

	@Override
    public void onUpdateDiscount(Discount discount) {
	    if (this.updateDiscountCallback != null) {
	    	this.updateDiscountCallback.onUpdateDiscount(discount);
	    }
    }

	@Override
    public void onFailUpdateDiscount(Error error) {
	    if (this.updateDiscountCallback != null) {
	    	this.updateDiscountCallback.onFailUpdateDiscount(error);
	    }
    }

	@Override
    public void onCreateDiscount(Discount discount) {
		if (this.createDiscountCallback != null) {
			this.createDiscountCallback.onCreateDiscount(discount);
	    }
    }

	@Override
    public void onFailCreateDiscount(Error error) {
	    if (this.createDiscountCallback != null) {
	    	this.createDiscountCallback.onFailCreateDiscount(error);
	    }
    }

	@Override
    public void onGetDiscount(Discount discounts) {
	    if (this.getDiscountCallback != null) {
	    	this.getDiscountCallback.onGetDiscount(discounts);
	    }
    }

	@Override
    public void onFailGetDiscount(Error error) {
		if (this.getDiscountCallback != null) {
			this.getDiscountCallback.onFailGetDiscount(error);
		}
    }

	@Override
    public void onGetDiscounts(Discounts discounts) {
	    if (this.getDiscountsCallback != null) {
	    	this.getDiscountsCallback.onGetDiscounts(discounts);
	    }
    }

	@Override
    public void onFailGetDiscounts(Error error) {
		if (this.getDiscountsCallback != null) {
			this.getDiscountsCallback.onFailGetDiscounts(error);
		}
	}

}
