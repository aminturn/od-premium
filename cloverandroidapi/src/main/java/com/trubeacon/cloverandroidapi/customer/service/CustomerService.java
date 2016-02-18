package com.trubeacon.cloverandroidapi.customer.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.common.Address;
import com.trubeacon.cloverandroidapi.customer.Customer;
import com.trubeacon.cloverandroidapi.customer.EmailAddress;
import com.trubeacon.cloverandroidapi.customer.PhoneNumber;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomer.CreateCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomerAddress.CreateCustomerAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomerEmailAddress.CreateCustomerEmailAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.CreateCustomerPhoneNumber.CreateCustomerPhoneNumberCallback;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomer.DeleteCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomerAddress.DeleteCustomerAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomerEmailAddress.DeleteCustomerEmailAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.DeleteCustomerPhoneNumber.DeleteCustomerPhoneNumberCallback;
import com.trubeacon.cloverandroidapi.customer.service.GetCustomer.GetCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.GetCustomers.GetCustomersCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomer.UpdateCustomerCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomerAddress.UpdateCustomerAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomerEmailAddress.UpdateCustomerEmailAddressCallback;
import com.trubeacon.cloverandroidapi.customer.service.UpdateCustomerPhoneNumber.UpdateCustomerPhoneNumberCallback;

public interface CustomerService {
	public List<Customer> getCustomers(String mId, String token, Object... params) throws RESTException;
	public void getCustomers(String mId, String token, GetCustomersCallback callback, Object... params);
	public Customer createCustomer(String mId, String token, Customer customer) throws RESTException;
	public void createCustomer(String mId, String token, Customer customer, CreateCustomerCallback callback);
	public Customer getCustomer(String mId, String token, String customerId) throws RESTException;
	public void getCustomer(String mId, String token, String customerId, GetCustomerCallback callback);
	public Customer updateCustomer(String mId, String token, String customerId, Customer customer) throws RESTException;
	public void updateCustomer(String mId, String token, String customerId, Customer customer, UpdateCustomerCallback callback);
	public Customer deleteCustomer(String mId, String token, String customerId) throws RESTException;
	public void deleteCustomer(String mId, String token, String customerId, DeleteCustomerCallback callback);
	public PhoneNumber createCustomerPhoneNumber(String mId, String token, String customerId, PhoneNumber phoneNumber) throws RESTException;
	public void createCustomerPhoneNumber(String mId, String token, String customerId, PhoneNumber phoneNumber, CreateCustomerPhoneNumberCallback callback);
	public PhoneNumber updateCustomerPhoneNumber(String mId, String token, String customerId, String phoneId, PhoneNumber update) throws RESTException;
	public void updateCustomerPhoneNumber(String mId, String token, String customerId, String phoneId, PhoneNumber update, UpdateCustomerPhoneNumberCallback callback);
	public PhoneNumber deleteCustomerPhoneNumber(String mId, String token, String customerId, String phoneId) throws RESTException;
	public void deleteCustomerPhoneNumber(String mId, String token, String customerId, String phoneId, DeleteCustomerPhoneNumberCallback callback);
	public EmailAddress createCustomerEmailAddress(String mId, String token, String customerId, EmailAddress emailAddress) throws RESTException;
	public void createCustomerEmailAddress(String mId, String token, String customerId, EmailAddress emailAddress, CreateCustomerEmailAddressCallback callback);
	public EmailAddress updateCustomerEmailAddress(String mId, String token, String customerId, String emailId, EmailAddress update) throws RESTException;
	public void updateCustomerEmailAddress(String mId, String token, String customerId, String emailId, EmailAddress update, UpdateCustomerEmailAddressCallback callback);
	public EmailAddress deleteCustomerEmailAddress(String mId, String token, String customerId, String emailId) throws RESTException;
	public void deleteCustomerEmailAddress(String mId, String token, String customerId, String emailId, DeleteCustomerEmailAddressCallback callback);
	public Address createCustomerAddress(String mId, String token, String customerId, Address address) throws RESTException;
	public void createCustomerAddress(String mId, String token, String customerId, Address address, CreateCustomerAddressCallback callback);
	public Address updateCustomerAddress(String mId, String token, String customerId, String addressId, Address address) throws RESTException;
	public void updateCustomerAddress(String mId, String token, String customerId, String addressId, Address address, UpdateCustomerAddressCallback callback);
	public Address deleteCustomerAddress(String mId, String token, String customerId, String addressId) throws RESTException;
	public void deleteCustomerAddress(String mId, String token, String customerId, String addressId, DeleteCustomerAddressCallback callback);
}
