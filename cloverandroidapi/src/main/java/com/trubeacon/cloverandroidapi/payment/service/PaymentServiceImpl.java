package com.trubeacon.cloverandroidapi.payment.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.payment.CardSummary;
import com.trubeacon.cloverandroidapi.payment.Credit;
import com.trubeacon.cloverandroidapi.payment.CreditSummary;
import com.trubeacon.cloverandroidapi.payment.Payment;
import com.trubeacon.cloverandroidapi.payment.PaymentSummary;
import com.trubeacon.cloverandroidapi.payment.Refund;
import com.trubeacon.cloverandroidapi.payment.RefundSummary;
import com.trubeacon.cloverandroidapi.payment.TenderSummary;
import com.trubeacon.cloverandroidapi.payment.service.CreatePayment.CreatePaymentCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCardSummary.GetCardSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCredit.GetCreditCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCreditSummary.GetCreditSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetCredits.GetCreditsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetEmployeePayments.GetEmployeePaymentsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetOrderPayments.GetOrderPaymentsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetPayment.GetPaymentCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetPaymentSummary.GetPaymentSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetPayments.GetPaymentsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetRefund.GetRefundCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetRefundSummary.GetRefundSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetRefunds.GetRefundsCallback;
import com.trubeacon.cloverandroidapi.payment.service.GetTenderSummary.GetTenderSummaryCallback;
import com.trubeacon.cloverandroidapi.payment.service.UpdatePayment.UpdatePaymentCallback;

public class PaymentServiceImpl implements PaymentService, 
											CreatePaymentCallback,
											GetCardSummaryCallback,
											GetCreditCallback,
											GetCreditsCallback,
											GetCreditSummaryCallback,
											GetEmployeePaymentsCallback,
											GetOrderPaymentsCallback,
											GetPaymentCallback,
											GetPaymentsCallback,
											GetPaymentSummaryCallback,
											GetRefundCallback,
											GetRefundsCallback,
											GetRefundSummaryCallback,
											GetTenderSummaryCallback,
											UpdatePaymentCallback {

	private CreatePaymentCallback createPaymentCallback;
	private GetCardSummaryCallback getCardSummaryCallback;
	private GetCreditCallback getCreditCallback;
	private GetCreditsCallback getCreditsCallback;
	private GetCreditSummaryCallback getCreditSummaryCallback;
	private GetEmployeePaymentsCallback getEmployeePaymentsCallback;
	private GetOrderPaymentsCallback getOrderPaymentsCallback;
	private GetPaymentCallback getPaymentCallback;
	private GetPaymentsCallback getPaymentsCallback;
	private GetPaymentSummaryCallback getPaymentSummaryCallback;
	private GetRefundCallback getRefundCallback;
	private GetRefundsCallback getRefundsCallback;
	private GetRefundSummaryCallback getRefundSummaryCallback;
	private GetTenderSummaryCallback getTenderSummaryCallback;
	private UpdatePaymentCallback updatePaymentCallback;
	
	@Override
    public List<Payment> getPayments(String mId, String token, Object... params)
            throws RESTException {
	    return new GetPayments(mId, token, params).execute();
    }

	@Override
    public Payment createPayment(String mId, String token, Payment payment)
            throws RESTException {
	    return new CreatePayment(mId, token, payment).execute();
    }

	@Override
    public Payment getPayment(String mId, String token, String paymentId)
            throws RESTException {
	    return new GetPayment(mId, token, paymentId).execute();
    }

	@Override
    public Payment updatePayment(String mId, String token, String paymentId,
            Payment update) throws RESTException {
	    return new UpdatePayment(mId, token, paymentId, update).execute();
    }

	@Override
    public List<Payment> getOrderPayments(String mId, String token,
            String orderId, Object... params) throws RESTException {
	    return new GetOrderPayments(mId, token, orderId, params).execute();
    }

	@Override
    public List<Payment> getEmployeePayments(String mId, String token,
            String empId, Object... params) throws RESTException {
	    return new GetEmployeePayments(mId, token, empId, params).execute();
    }

	@Override
    public List<Refund> getRefunds(String mId, String token, Object... params)
            throws RESTException {
	    return new GetRefunds(mId, token, params).execute();
    }

	@Override
    public Refund getRefund(String mId, String token, String refundId)
            throws RESTException {
	    return new GetRefund(mId, token, refundId).execute();
    }

	@Override
    public List<Credit> getCredits(String mId, String token, Object... params)
            throws RESTException {
	    return new GetCredits(mId, token, params).execute();
    }

	@Override
    public Credit getCredit(String mId, String token, String cId)
            throws RESTException {
	    return new GetCredit(mId, token, cId).execute();
    }

	@Override
    public PaymentSummary getPaymentSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetPaymentSummary(mId, token, params).execute();
    }

	@Override
    public CreditSummary getCreditSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetCreditSummary(mId, token, params).execute();
    }

	@Override
    public RefundSummary getRefundSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetRefundSummary(mId, token, params).execute();
    }

	@Override
    public WrappedList<TenderSummary> getTenderSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetTenderSummary(mId, token, params).execute();
    }

	@Override
    public WrappedList<CardSummary> getCardSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetCardSummary(mId, token, params).execute();
    }

	@Override
    public void getPayments(String mId, String token,
            GetPaymentsCallback callback, Object... params) {
	    this.getPaymentsCallback = callback;
	    new GetPayments(mId, token, params).execute(this);
    }

	@Override
    public void createPayment(String mId, String token, Payment payment,
            CreatePaymentCallback callback) {
	    this.createPaymentCallback = callback;
	    new CreatePayment(mId, token, payment).execute(this);
    }

	@Override
    public void getPayment(String mId, String token, String paymentId,
            GetPaymentCallback callback) {
	    this.getPaymentCallback = callback;
	    new GetPayment(mId, token, paymentId).execute(this);
    }

	@Override
    public void updatePayment(String mId, String token, String paymentId,
            Payment update, UpdatePaymentCallback callback) {
	    this.updatePaymentCallback = callback;
	    new UpdatePayment(mId, token, paymentId, update).execute(this);
    }

	@Override
    public void getOrderPayments(String mId, String token, String orderId,
            GetOrderPaymentsCallback callback, Object... params) {
	    this.getOrderPaymentsCallback = callback;
	    new GetOrderPayments(mId, token, orderId, params).execute(this);
    }

	@Override
    public void getEmployeePayments(String mId, String token, String empId,
            GetEmployeePaymentsCallback callback, Object... params) {
	    this.getEmployeePaymentsCallback = callback;
	    new GetEmployeePayments(mId, token, empId, params).execute(this);
    }

	@Override
    public void getRefunds(String mId, String token,
            GetRefundsCallback callback, Object... params) {
	    this.getRefundsCallback = callback;
	    new GetRefunds(mId, token, params).execute(this);
    }

	@Override
    public void getRefund(String mId, String token, String refundId,
            GetRefundCallback callback) {
	    this.getRefundCallback = callback;
	    new GetRefund(mId, token, refundId).execute(this);
    }

	@Override
    public void getCredits(String mId, String token,
            GetCreditsCallback callback, Object... params) {
	    this.getCreditsCallback = callback;
	    new GetCredits(mId, token, params).execute(this);
    }

	@Override
    public void getCredit(String mId, String token, String cId,
            GetCreditCallback callback) {
	    this.getCreditCallback = callback;
	    new GetCredit(mId, token, cId).execute(this);
    }

	@Override
    public void getPaymentSummary(String mId, String token,
            GetPaymentSummaryCallback callback, Object... params) {
	    this.getPaymentSummaryCallback = callback;
	    new GetPaymentSummary(mId, token, params).execute(this);
    }

	@Override
    public void getTenderSummary(String mId, String token,
            GetTenderSummaryCallback callback, Object... params) {
	    this.getTenderSummaryCallback = callback;
	    new GetTenderSummary(mId, token, params).execute(this);
    }

	@Override
    public void getCardSummary(String mId, String token,
            GetCardSummaryCallback callback, Object... params) {
	    this.getCardSummaryCallback = callback;
	    new GetCardSummary(mId, token, params).execute(this);
    }

	@Override
    public void getCreditSummary(String mId, String token,
            GetCreditSummaryCallback callback, Object... params) {
	    this.getCreditSummaryCallback = callback;
	    new GetCreditSummary(mId, token, params).execute(this);
    }

	@Override
    public void getRefundSummary(String mId, String token,
            GetRefundSummaryCallback callback, Object... params) {
	    this.getRefundSummaryCallback = callback;
	    new GetRefundSummary(mId, token, params).execute(this);
    }

	@Override
	public void onCreatePayment(Payment result) {
		if (createPaymentCallback != null) {
			createPaymentCallback.onCreatePayment(result);
		}
    }

	@Override
	public void onFailCreatePayment(Error error) {
		if (createPaymentCallback != null) {
			createPaymentCallback.onFailCreatePayment(error);
		}
    }

	@Override
	public void onGetCardSummary(WrappedList<CardSummary> result) {
		if (getCardSummaryCallback != null) {
			getCardSummaryCallback.onGetCardSummary(result);
		}
    }

	@Override
	public void onFailGetCardSummary(Error error) {
		if (getCardSummaryCallback != null) {
			getCardSummaryCallback.onFailGetCardSummary(error);
		}
    }

	@Override
	public void onGetCredit(Credit result) {
		if (getCreditCallback != null) {
			getCreditCallback.onGetCredit(result);
		}
    }

	@Override
	public void onFailGetCredit(Error error) {
		if (getCreditCallback != null) {
			getCreditCallback.onFailGetCredit(error);
		}
    }

	@Override
	public void onGetCredits(WrappedList<Credit> result) {
		if (getCreditsCallback != null) {
			getCreditsCallback.onGetCredits(result);
		}
    }

	@Override
	public void onFailGetCredits(Error error) {
		if (getCreditsCallback != null) {
			getCreditsCallback.onFailGetCredits(error);
		}
    }

	@Override
	public void onGetCreditSummary(CreditSummary result) {
		if (getCreditSummaryCallback != null) {
			getCreditSummaryCallback.onGetCreditSummary(result);
		}
    }

	@Override
	public void onFailGetCreditSummary(Error error) {
		if (getCreditSummaryCallback != null) {
			getCreditSummaryCallback.onFailGetCreditSummary(error);
		}
    }

	@Override
	public void onGetEmployeePayments(WrappedList<Payment> result) {
		if (getEmployeePaymentsCallback != null) {
			getEmployeePaymentsCallback.onGetEmployeePayments(result);
		}
    }

	@Override
	public void onFailGetEmployeePayments(Error error) {
		if (getEmployeePaymentsCallback != null) {
			getEmployeePaymentsCallback.onFailGetEmployeePayments(error);
		}
    }

	@Override
	public void onGetOrderPayments(WrappedList<Payment> result) {
		if (getOrderPaymentsCallback != null) {
			getOrderPaymentsCallback.onGetOrderPayments(result);
		}
    }

	@Override
	public void onFailGetOrderPayments(Error error) {
		if (getOrderPaymentsCallback != null) {
			getOrderPaymentsCallback.onFailGetOrderPayments(error);
		}
    }

	@Override
	public void onGetPayment(Payment result) {
		if (getPaymentCallback != null) {
			getPaymentCallback.onGetPayment(result);
		}
    }

	@Override
	public void onFailGetPayment(Error error) {
		if (getPaymentCallback != null) {
			getPaymentCallback.onFailGetPayment(error);
		}
    }

	@Override
	public void onGetPayments(WrappedList<Payment> result) {
		if (getPaymentsCallback != null) {
			getPaymentsCallback.onGetPayments(result);
		}
    }

	@Override
	public void onFailGetPayments(Error error) {
		if (getPaymentsCallback != null) {
			getPaymentsCallback.onFailGetPayments(error);
		}
    }

	@Override
	public void onGetPaymentSummary(PaymentSummary result) {
		if (getPaymentSummaryCallback != null) {
			getPaymentSummaryCallback.onGetPaymentSummary(result);
		}
    }

	@Override
	public void onFailGetPaymentSummary(Error error) {
		if (getPaymentSummaryCallback != null) {
			getPaymentSummaryCallback.onFailGetPaymentSummary(error);
		}
    }

	@Override
	public void onGetRefund(Refund result) {
		if (getRefundCallback != null) {
			getRefundCallback.onGetRefund(result);
		}
    }

	@Override
	public void onFailGetRefund(Error error) {
		if (getRefundCallback != null) {
			getRefundCallback.onFailGetRefund(error);
		}
    }

	@Override
	public void onGetRefunds(WrappedList<Refund> result) {
		if (getRefundsCallback != null) {
			getRefundsCallback.onGetRefunds(result);
		}
    }

	@Override
	public void onFailGetRefunds(Error error) {
		if (getRefundsCallback != null) {
			getRefundsCallback.onFailGetRefunds(error);
		}
    }

	@Override
	public void onGetRefundSummary(RefundSummary result) {
		if (getRefundSummaryCallback != null) {
			getRefundSummaryCallback.onGetRefundSummary(result);
		}
    }

	@Override
	public void onFailGetRefundSummary(Error error) {
		if (getRefundSummaryCallback != null) {
			getRefundSummaryCallback.onFailGetRefundSummary(error);
		}
    }

	@Override
	public void onGetTenderSummary(WrappedList<TenderSummary> result) {
		if (getTenderSummaryCallback != null) {
			getTenderSummaryCallback.onGetTenderSummary(result);
		}
    }

	@Override
	public void onFailGetTenderSummary(Error error) {
		if (getTenderSummaryCallback != null) {
			getTenderSummaryCallback.onFailGetTenderSummary(error);
		}
    }

	@Override
	public void onUpdatePayment(Payment result) {
		if (updatePaymentCallback != null) {
			updatePaymentCallback.onUpdatePayment(result);
		}
    }

	@Override
	public void onFailUpdatePayment(Error error) {
		if (updatePaymentCallback != null) {
			updatePaymentCallback.onFailUpdatePayment(error);
		}
    }

}
