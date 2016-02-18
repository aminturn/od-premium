package com.trubeacon.cloverandroidapi.payment.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
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

public interface PaymentService {
		
	public List<Payment> getPayments(String mId, String token, Object... params) throws RESTException;
	public void getPayments(String mId, String token, GetPaymentsCallback callback, Object... params);
	public Payment createPayment(String mId, String token, Payment payment) throws RESTException;
	public void createPayment(String mId, String token, Payment payment, CreatePaymentCallback callback);
	public Payment getPayment(String mId, String token, String paymentId) throws RESTException;
	public void getPayment(String mId, String token, String paymentId, GetPaymentCallback callback);
	public Payment updatePayment(String mId, String token, String paymentId, Payment update) throws RESTException;
	public void updatePayment(String mId, String token, String paymentId, Payment update, UpdatePaymentCallback callback);
	
	public List<Payment> getOrderPayments(String mId, String token, String orderId, Object... params) throws RESTException;
	public void getOrderPayments(String mId, String token, String orderId, GetOrderPaymentsCallback callback, Object... params);
	public List<Payment> getEmployeePayments(String mId, String token, String empId, Object... params) throws RESTException;
	public void getEmployeePayments(String mId, String token, String empId, GetEmployeePaymentsCallback callback, Object... params);
	
	public List<Refund> getRefunds(String mId, String token, Object... params) throws RESTException;
	public void getRefunds(String mId, String token, GetRefundsCallback callback, Object... params);
	public Refund getRefund(String mId, String token, String refundId) throws RESTException;
	public void getRefund(String mId, String token, String refundId, GetRefundCallback callback);
	
	public List<Credit> getCredits(String mId, String token, Object... params) throws RESTException;
	public void getCredits(String mId, String token, GetCreditsCallback callback, Object... params);
	public Credit getCredit(String mId, String token, String cId) throws RESTException;
	public void getCredit(String mId, String token, String cId, GetCreditCallback callback);
	
	public PaymentSummary getPaymentSummary(String mId, String token, Object... params) throws RESTException;
	public void getPaymentSummary(String mId, String token, GetPaymentSummaryCallback callback, Object... params);
	public WrappedList<TenderSummary> getTenderSummary(String mId, String token, Object... params) throws RESTException;
	public void getTenderSummary(String mId, String token, GetTenderSummaryCallback callback, Object... params);
	public WrappedList<CardSummary> getCardSummary(String mId, String token, Object... params) throws RESTException;
	public void getCardSummary(String mId, String token, GetCardSummaryCallback callback, Object... params);
	public CreditSummary getCreditSummary(String mId, String token, Object... params) throws RESTException;
	public void getCreditSummary(String mId, String token, GetCreditSummaryCallback callback, Object... params);
	public RefundSummary getRefundSummary(String mId, String token, Object... params) throws RESTException;
	public void getRefundSummary(String mId, String token, GetRefundSummaryCallback callback, Object... params);
	
}
