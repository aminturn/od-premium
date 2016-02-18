package com.trubeacon.cloverandroidapi.account.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.account.service.GetMerchants.GetMerchantsCallback;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.merchant.Merchant;

public interface AccountService {
	public List<Merchant> getMerchants(String accountId, String token) throws RESTException;
	public void getMerchants(String accountId, String token, GetMerchantsCallback callback);
}
