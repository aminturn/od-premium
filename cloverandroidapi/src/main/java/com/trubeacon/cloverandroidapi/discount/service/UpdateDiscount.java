package com.trubeacon.cloverandroidapi.discount.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.trubeacon.cloverandroidapi.CloverService;
import com.trubeacon.cloverandroidapi.Version;
import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.RESTMethod;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.discount.Discount;

// this is not a rest method since it's so weird
public class UpdateDiscount {

	public interface UpdateDiscountCallback {
		public void onUpdateDiscount(Discount discount);
		public void onFailUpdateDiscount(com.trubeacon.cloverandroidapi.client.error.Error error);
	}
	
	private final String merchantId;
	private final String token;
	private final String discountId;
	private final Discount update;
	
	public UpdateDiscount(String merchantId, String token, String discountId, Discount update) {
		this.merchantId = merchantId;
		this.token = token;
		this.discountId = discountId;
		this.update = update;
	}
	
	public Discount execute() throws RESTException {
		if (update == null) throw new RESTException("Discount cannot be null", com.trubeacon.cloverandroidapi.client.error.Error.error("Discount cannot be null"));
		if (update.getName() != null) {
			new UpdateDiscountName(merchantId, token, discountId, update.getName()).execute();
		}
		if (update.getType() != null) {
			switch(update.getType()) {
			case AMOUNT:
				new UpdateDiscountAmount(merchantId, token, discountId, update.getAmount()).execute();
				break;
			case PERCENTAGE:
				new UpdateDiscountPercentage(merchantId, token, discountId, update.getAmount()).execute();
				break;
			}
		}
		return update;
	}
	
	public void execute(final UpdateDiscountCallback callback) {
		if (update.getName() != null) {
			new UpdateDiscountName(merchantId, token, discountId, update.getName()).execute(null);
		}
		if (update.getType() != null) {
			switch(update.getType()) {
			case AMOUNT:
				new UpdateDiscountAmount(merchantId, token, discountId, update.getAmount()).execute(null);
				break;
			case PERCENTAGE:
				new UpdateDiscountPercentage(merchantId, token, discountId, update.getAmount()).execute(null);
				break;
			}
		}
		// FIXME: chaining these callbacks is annoying and error prone, so we'll just ignore them
		callback.onUpdateDiscount(update);
	}

	// those fucking morons decided to make separate web requests to update individual fields... 
	// The stupidity, I can't even...
	public static class UpdateDiscountName extends RESTMethod<NameWrapper> {

		private static final String URL = "/merchant/{merchantId}/inventory/discounts/{discountId}/name";
		private static final HttpMethod METHOD = HttpMethod.POST;
		
		public interface UpdateDiscountNameCallback {
			public void onUpdateDiscountName(NameWrapper result);
			public void onFailUpdateDiscountName(com.trubeacon.cloverandroidapi.client.error.Error error);
		}
		
		public UpdateDiscountName(String merchantId, String token, String discountId, String discountName) {
			super(token, new TypeReference<NameWrapper>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
			super.addPathParam(Param.path("merchantId", merchantId));
			super.addPathParam(Param.path("discountId", discountId));
			super.setEntity(new NameWrapper(discountName));
		}
		
		@Override
		public NameWrapper execute() throws RESTException {
			return super.execute();
		}
		
		public void execute(final UpdateDiscountNameCallback callback) {
			super.execute(new Callback<NameWrapper>() {

				@Override
                public void onReceiveResult(NameWrapper result) {
	                if (callback != null) {
	                	callback.onUpdateDiscountName(result);
	                }
                }

				@Override
                public void onReceiveError(Error error) {
	                if (callback != null) {
	                	callback.onFailUpdateDiscountName(error);
	                }
                }
				
			});
		}
		
	}
	
	public static class UpdateDiscountPercentage extends RESTMethod<PercentageWrapper> {

		private static final String URL = "/merchant/{merchantId}/inventory/discounts/{discountId}/percentage";
		private static final HttpMethod METHOD = HttpMethod.POST;
		
		public interface UpdateDiscountPercentageCallback {
			public void onUpdateDiscountPercentage(PercentageWrapper result);
			public void onFailUpdateDiscountPercentage(com.trubeacon.cloverandroidapi.client.error.Error error);
		}
		
		public UpdateDiscountPercentage(String merchantId, String token, String discountId, long discountPercentage) {
			super(token, new TypeReference<PercentageWrapper>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
			super.addPathParam(Param.path("merchantId", merchantId));
			super.addPathParam(Param.path("discountId", discountId));
			super.setEntity(new PercentageWrapper(discountPercentage));
		}
		
		@Override
		public PercentageWrapper execute() throws RESTException {
			return super.execute();
		}
		
		public void execute(final UpdateDiscountPercentageCallback callback) {
			super.execute(new Callback<PercentageWrapper>() {

				@Override
                public void onReceiveResult(PercentageWrapper result) {
	                if (callback != null) {
	                	callback.onUpdateDiscountPercentage(result);
	                }
                }

				@Override
                public void onReceiveError(Error error) {
	                if (callback != null) {
	                	callback.onFailUpdateDiscountPercentage(error);
	                }
                }
				
			});
		}
		
	}
	
	public static class UpdateDiscountAmount extends RESTMethod<AmountWrapper> {

		private static final String URL = "/merchant/{merchantId}/inventory/discounts/{discountId}/amount";
		private static final HttpMethod METHOD = HttpMethod.POST;
		
		public interface UpdateDiscountAmountCallback {
			public void onUpdateDiscountAmount(AmountWrapper result);
			public void onFailUpdateDiscountAmount(com.trubeacon.cloverandroidapi.client.error.Error error);
		}
		
		public UpdateDiscountAmount(String merchantId, String token, String discountId, long discountAmount) {
			super(token, new TypeReference<AmountWrapper>(){}, CloverService.getBaseUrl(Version.V2) + URL, METHOD);
			super.addPathParam(Param.path("merchantId", merchantId));
			super.addPathParam(Param.path("discountId", discountId));
			super.setEntity(new AmountWrapper(discountAmount));
		}
		
		@Override
		public AmountWrapper execute() throws RESTException {
			return super.execute();
		}
		
		public void execute(final UpdateDiscountAmountCallback callback) {
			super.execute(new Callback<AmountWrapper>() {

				@Override
                public void onReceiveResult(AmountWrapper result) {
	                if (callback != null) {
	                	callback.onUpdateDiscountAmount(result);
	                }
                }

				@Override
                public void onReceiveError(Error error) {
	                if (callback != null) {
	                	callback.onFailUpdateDiscountAmount(error);
	                }
                }
				
			});
		}
		
	}
	
	private static class NameWrapper {
		
		@JsonProperty
		private String name;
		
		public NameWrapper() {}
		public NameWrapper(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	private static class PercentageWrapper {
		
		@JsonProperty
		private Long percentage;
		
		public PercentageWrapper() {}
		public PercentageWrapper(long percentage) {
			this.percentage = percentage;
		}
		public Long getPercentage() {
			return percentage;
		}
		public void setPercentage(Long percentage) {
			this.percentage = percentage;
		}
		
	}
	
	private static class AmountWrapper {
		
		@JsonProperty
		private Long amount;
		
		public AmountWrapper() {}
		public AmountWrapper(long amount) {
			this.amount = amount;
		}
		
		public Long getAmount() {
			return amount;
		}
		public void setAmount(Long amount) {
			this.amount = amount;
		}
		
	}
	
}
