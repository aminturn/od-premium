package com.trubeacon.cloverandroidapi.customer.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.common.Address;
import com.trubeacon.cloverandroidapi.common.WrappedList;
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

public class CustomerServiceImpl implements CustomerService, 
											GetCustomersCallback, 
											CreateCustomerCallback, 
											GetCustomerCallback, 
											UpdateCustomerCallback,
											DeleteCustomerCallback,
											CreateCustomerPhoneNumberCallback, 
											UpdateCustomerPhoneNumberCallback, 
											DeleteCustomerPhoneNumberCallback, 
											CreateCustomerEmailAddressCallback, 
											UpdateCustomerEmailAddressCallback,
											DeleteCustomerEmailAddressCallback,
											CreateCustomerAddressCallback,
											UpdateCustomerAddressCallback,
											DeleteCustomerAddressCallback {

	private GetCustomersCallback getCustomersCallback; 
	private CreateCustomerCallback createCustomerCallback; 
	private GetCustomerCallback getCustomerCallback;
	private UpdateCustomerCallback updateCustomerCallback;
	private DeleteCustomerCallback deleteCustomerCallback;
	private CreateCustomerPhoneNumberCallback createCustomerPhoneNumberCallback; 
	private UpdateCustomerPhoneNumberCallback updateCustomerPhoneNumberCallback; 
	private DeleteCustomerPhoneNumberCallback deleteCustomerPhoneNumberCallback;
	private CreateCustomerEmailAddressCallback createCustomerEmailAddressCallback; 
	private UpdateCustomerEmailAddressCallback updateCustomerEmailAddressCallback;
	private DeleteCustomerEmailAddressCallback deleteCustomerEmailAddressCallback;
	private CreateCustomerAddressCallback createCustomerAddressCallback;
	private UpdateCustomerAddressCallback updateCustomerAddressCallback;
	private DeleteCustomerAddressCallback deleteCustomerAddressCallback;
	
	@Override
    public List<Customer> getCustomers(String mId, String token, Object... params) throws RESTException {
	    return new GetCustomers(mId, token, params).execute();
    }

	@Override
    public Customer createCustomer(String mId, String token, Customer customer) throws RESTException {
	    return new CreateCustomer(mId, token, customer).execute();
    }

	@Override
    public Customer getCustomer(String mId, String token, String customerId) throws RESTException {
	    return new GetCustomer(mId, token, customerId).execute();
    }

	@Override
    public Customer updateCustomer(String mId, String token, String customerId, Customer customer) throws RESTException {
	    return new UpdateCustomer(mId, token, customerId, customer).execute();
    }

	@Override
    public Customer deleteCustomer(String mId, String token, String customerId) throws RESTException {
	    return new DeleteCustomer(mId, token, customerId).execute();
    }

	@Override
    public PhoneNumber createCustomerPhoneNumber(String mId, String token, String customerId, PhoneNumber phoneNumber) throws RESTException {
	    return new CreateCustomerPhoneNumber(mId, token, customerId, phoneNumber).execute();
    }

	@Override
    public PhoneNumber updateCustomerPhoneNumber(String mId, String token, String customerId, String phoneId, PhoneNumber update) throws RESTException {
	    return new UpdateCustomerPhoneNumber(mId, token, customerId, phoneId, update).execute();
    }

	@Override
    public PhoneNumber deleteCustomerPhoneNumber(String mId, String token, String customerId, String phoneId) throws RESTException {
	    return new DeleteCustomerPhoneNumber(mId, token, customerId, phoneId).execute();
    }

	@Override
    public EmailAddress createCustomerEmailAddress(String mId, String token, String customerId, EmailAddress emailAddress) throws RESTException {
	    return new CreateCustomerEmailAddress(mId, token, customerId, emailAddress).execute();
    }

	@Override
    public EmailAddress updateCustomerEmailAddress(String mId, String token, String customerId, String emailId, EmailAddress update) throws RESTException {
	    return new UpdateCustomerEmailAddress(mId, token, customerId, emailId, update).execute();
    }

	@Override
    public EmailAddress deleteCustomerEmailAddress(String mId, String token, String customerId, String emailId) throws RESTException {
	    return new DeleteCustomerEmailAddress(mId, token, customerId, emailId).execute();
    }

	@Override
    public Address createCustomerAddress(String mId, String token, String customerId, Address address) throws RESTException {
	    return new CreateCustomerAddress(mId, token, customerId, address).execute();
    }

	@Override
    public Address updateCustomerAddress(String mId, String token, String customerId, String addressId, Address address) throws RESTException {
	    return new UpdateCustomerAddress(mId, token, customerId, addressId, address).execute();
    }

	@Override
    public Address deleteCustomerAddress(String mId, String token, String customerId, String addressId) throws RESTException {
	    return new DeleteCustomerAddress(mId, token, customerId, addressId).execute();
    }

	@Override
    public void getCustomers(String mId, String token,
            GetCustomersCallback callback, Object... params) {
	    this.getCustomersCallback = callback;
	    new GetCustomers(mId, token, params).execute(this);
    }

	@Override
    public void createCustomer(String mId, String token, Customer customer,
            CreateCustomerCallback callback) {
	    this.createCustomerCallback = callback;
	    new CreateCustomer(mId, token, customer).execute(this);
    }

	@Override
    public void getCustomer(String mId, String token, String customerId,
            GetCustomerCallback callback) {
	    this.getCustomerCallback = callback;
	    new GetCustomer(mId, token, customerId).execute(this);
    }

	@Override
    public void updateCustomer(String mId, String token, String customerId,
            Customer customer, UpdateCustomerCallback callback) {
	    this.updateCustomerCallback = callback;
	    new UpdateCustomer(mId, token, customerId, customer).execute(this);
    }

	@Override
    public void deleteCustomer(String mId, String token, String customerId,
            DeleteCustomerCallback callback) {
	    this.deleteCustomerCallback = callback;
	    new DeleteCustomer(mId, token, customerId).execute(this);
    }

	@Override
    public void createCustomerPhoneNumber(String mId, String token,
            String customerId, PhoneNumber phoneNumber,
            CreateCustomerPhoneNumberCallback callback) {
	    this.createCustomerPhoneNumberCallback = callback;
	    new CreateCustomerPhoneNumber(mId, token, customerId, phoneNumber).execute(this);
    }

	@Override
    public void updateCustomerPhoneNumber(String mId, String token,
            String customerId, String phoneId, PhoneNumber update,
            UpdateCustomerPhoneNumberCallback callback) {
	    this.updateCustomerPhoneNumberCallback = callback;
	    new UpdateCustomerPhoneNumber(mId, token, customerId, phoneId, update).execute(this);
    }

	@Override
    public void deleteCustomerPhoneNumber(String mId, String token,
            String customerId, String phoneId,
            DeleteCustomerPhoneNumberCallback callback) {
		this.deleteCustomerPhoneNumberCallback = callback;
	    new DeleteCustomerPhoneNumber(mId, token, customerId, phoneId).execute(this);
    }

	@Override
    public void createCustomerEmailAddress(String mId, String token,
            String customerId, EmailAddress emailAddress,
            CreateCustomerEmailAddressCallback callback) {
	    this.createCustomerEmailAddressCallback = callback;
	    new CreateCustomerEmailAddress(mId, token, customerId, emailAddress).execute(this);
    }

	@Override
    public void updateCustomerEmailAddress(String mId, String token,
            String customerId, String emailId, EmailAddress update,
            UpdateCustomerEmailAddressCallback callback) {
	    this.updateCustomerEmailAddressCallback = callback;
	    new UpdateCustomerEmailAddress(mId, token, customerId, emailId, update).execute(this);
    }

	@Override
    public void deleteCustomerEmailAddress(String mId, String token,
            String customerId, String emailId,
            DeleteCustomerEmailAddressCallback callback) {
	    this.deleteCustomerEmailAddressCallback = callback;
	    new DeleteCustomerEmailAddress(mId, token, customerId, emailId).execute(this);
    }

	@Override
    public void createCustomerAddress(String mId, String token,
            String customerId, Address address,
            CreateCustomerAddressCallback callback) {
	    this.createCustomerAddressCallback = callback;
	    new CreateCustomerAddress(mId, token, customerId, address).execute(this);
    }

	@Override
    public void updateCustomerAddress(String mId, String token,
            String customerId, String addressId, Address address,
            UpdateCustomerAddressCallback callback) {
	    this.updateCustomerAddressCallback = callback;
	    new UpdateCustomerAddress(mId, token, customerId, addressId, address).execute(this);
    }

	@Override
    public void deleteCustomerAddress(String mId, String token,
            String customerId, String addressId,
            DeleteCustomerAddressCallback callback) {
	    this.deleteCustomerAddressCallback = callback;
	    new DeleteCustomerAddress(mId, token, customerId, addressId).execute(this);
    }

	@Override
    public void onDeleteCustomerAddress(Address result) {
	    if (this.deleteCustomerAddressCallback != null) {
	    	this.deleteCustomerAddressCallback.onDeleteCustomerAddress(result);
	    }
    }

	@Override
    public void onFailDeleteCustomerAddress(Error error) {
	    if (this.deleteCustomerAddressCallback != null) {
	    	this.deleteCustomerAddressCallback.onFailDeleteCustomerAddress(error);
	    }
    }

	@Override
    public void onUpdateCustomerAddress(Address result) {
	    if (this.updateCustomerAddressCallback != null) {
	    	this.updateCustomerAddressCallback.onUpdateCustomerAddress(result);
	    }
    }

	@Override
    public void onFailUpdateCustomerAddress(Error error) {
	    if (this.updateCustomerAddressCallback != null) {
	    	this.updateCustomerAddressCallback.onFailUpdateCustomerAddress(error);
	    }
    }

	@Override
    public void onCreateCustomerAddress(Address result) {
	    if (this.createCustomerAddressCallback != null) {
	    	this.createCustomerAddressCallback.onCreateCustomerAddress(result);
	    }
    }

	@Override
    public void onFailCreateCustomerAddress(Error error) {
	    if (this.createCustomerAddressCallback != null) {
	    	this.createCustomerAddressCallback.onFailCreateCustomerAddress(error);
	    }
    }

	@Override
    public void onDeleteCustomerEmailAddress(EmailAddress result) {
	    if (this.deleteCustomerEmailAddressCallback != null) {
	    	this.deleteCustomerEmailAddressCallback.onDeleteCustomerEmailAddress(result);
	    }
    }

	@Override
    public void onFailDeleteCustomerEmailAddress(Error error) {
	    if (this.deleteCustomerEmailAddressCallback != null) {
	    	this.deleteCustomerEmailAddressCallback.onFailDeleteCustomerEmailAddress(error);
	    }
    }

	@Override
    public void onUpdateCustomerEmailAddress(EmailAddress result) {
	    if (this.updateCustomerEmailAddressCallback != null) {
	    	this.updateCustomerEmailAddressCallback.onUpdateCustomerEmailAddress(result);
	    }
    }

	@Override
    public void onFailUpdateCustomerEmailAddress(Error error) {
	    if (this.updateCustomerEmailAddressCallback != null) {
	    	this.updateCustomerEmailAddressCallback.onFailUpdateCustomerEmailAddress(error);
	    }
    }

	@Override
    public void onCreateCustomerEmailAddress(EmailAddress result) {
	    if (this.createCustomerEmailAddressCallback != null) {
	    	this.createCustomerEmailAddressCallback.onCreateCustomerEmailAddress(result);
	    }
    }

	@Override
    public void onFailCreateCustomerEmailAddress(Error error) {
	    if (this.createCustomerEmailAddressCallback != null) {
	    	this.createCustomerEmailAddressCallback.onFailCreateCustomerEmailAddress(error);
	    }
    }

	@Override
    public void onDeleteCustomerPhoneNumber(PhoneNumber result) {
	    if (this.deleteCustomerPhoneNumberCallback != null) {
	    	this.deleteCustomerPhoneNumberCallback.onDeleteCustomerPhoneNumber(result);
	    }
    }

	@Override
    public void onFailDeleteCustomerPhoneNumber(Error error) {
	    if (this.deleteCustomerPhoneNumberCallback != null) {
	    	this.deleteCustomerPhoneNumberCallback.onFailDeleteCustomerPhoneNumber(error);
	    }
    }

	@Override
    public void onUpdateCustomerPhoneNumber(PhoneNumber result) {
	    if (this.updateCustomerPhoneNumberCallback != null) {
	    	this.updateCustomerPhoneNumberCallback.onUpdateCustomerPhoneNumber(result);
	    }
    }

	@Override
    public void onFailUpdateCustomerPhoneNumber(Error error) {
	    if (this.updateCustomerPhoneNumberCallback != null) {
	    	this.updateCustomerPhoneNumberCallback.onFailUpdateCustomerPhoneNumber(error);
	    }
    }

	@Override
    public void onCreateCustomerPhoneNumber(PhoneNumber result) {
	    if (this.createCustomerPhoneNumberCallback != null) {
	    	this.createCustomerPhoneNumberCallback.onCreateCustomerPhoneNumber(result);
	    }
    }

	@Override
    public void onFailCreateCustomerPhoneNumber(Error error) {
	    if (this.createCustomerPhoneNumberCallback != null) {
	    	this.createCustomerPhoneNumberCallback.onFailCreateCustomerPhoneNumber(error);
	    }
    }

	@Override
    public void onDeleteCustomer(Customer result) {
	    if (this.deleteCustomerCallback != null) {
	    	this.deleteCustomerCallback.onDeleteCustomer(result);
	    }
    }

	@Override
    public void onFailDeleteCustomer(Error error) {
	    if (this.deleteCustomerCallback != null) {
	    	this.deleteCustomerCallback.onFailDeleteCustomer(error);
	    }
    }

	@Override
    public void onUpdateCustomer(Customer result) {
	    if (this.updateCustomerCallback != null) {
	    	this.updateCustomerCallback.onUpdateCustomer(result);
	    }
    }

	@Override
    public void onFailUpdateCustomer(Error error) {
	    if (this.updateCustomerCallback != null) {
	    	this.updateCustomerCallback.onFailUpdateCustomer(error);
	    }
    }

	@Override
    public void onGetCustomer(Customer result) {
	    if (this.getCustomerCallback != null) {
	    	this.getCustomerCallback.onGetCustomer(result);
	    }
    }

	@Override
    public void onFailGetCustomer(Error error) {
	    if (this.getCustomerCallback != null) {
	    	this.getCustomerCallback.onFailGetCustomer(error);
	    }
    }

	@Override
    public void onCreateCustomer(Customer result) {
	    if (this.createCustomerCallback != null) {
	    	this.createCustomerCallback.onCreateCustomer(result);
	    }
    }

	@Override
    public void onFailCreateCustomer(Error error) {
	    if (this.createCustomerCallback != null) {
	    	this.createCustomerCallback.onFailCreateCustomer(error);
	    }
    }

	@Override
    public void onGetCustomers(WrappedList<Customer> result) {
	    if (this.getCustomersCallback != null) {
	    	this.getCustomersCallback.onGetCustomers(result);
	    }
    }

	@Override
    public void onFailGetCustomers(Error error) {
	    if (this.getCustomersCallback != null) {
	    	this.getCustomersCallback.onFailGetCustomers(error);
	    }
    }

}
