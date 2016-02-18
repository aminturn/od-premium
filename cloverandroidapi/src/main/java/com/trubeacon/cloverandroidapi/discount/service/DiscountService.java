package com.trubeacon.cloverandroidapi.discount.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.discount.Discount;
import com.trubeacon.cloverandroidapi.discount.service.CreateDiscount.CreateDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.DeleteDiscount.DeleteDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.GetDiscount.GetDiscountCallback;
import com.trubeacon.cloverandroidapi.discount.service.GetDiscounts.GetDiscountsCallback;
import com.trubeacon.cloverandroidapi.discount.service.UpdateDiscount.UpdateDiscountCallback;

public interface DiscountService {
	public List<Discount> getDiscounts(String merchantId, String token, Object... params) throws RESTException;
	public void getDiscounts(String merchantId, String token, GetDiscountsCallback callback, Object... params);
	public Discount getDiscount(String merchantId, String token, String id) throws RESTException;
	public void getDiscount(String merchantId, String token, String id, GetDiscountCallback callback);
	public Discount createDiscount(String merchantId, String token, Discount discount) throws RESTException;
	public void createDiscount(String merchantId, String token, Discount discount, CreateDiscountCallback callback);
	public Discount updateDiscount(String merchantId, String token, String id, Discount discount) throws RESTException;
	public void updateDiscount(String merchantId, String token, String id, Discount discount, UpdateDiscountCallback callback);
	public Discount deleteDiscount(String merchantId, String token, String id) throws RESTException;
	public void deleteDiscount(String merchantId, String token, String id, DeleteDiscountCallback callback);
}
