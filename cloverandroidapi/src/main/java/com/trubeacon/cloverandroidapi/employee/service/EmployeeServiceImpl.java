package com.trubeacon.cloverandroidapi.employee.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.employee.Employee;
import com.trubeacon.cloverandroidapi.employee.EmployeeSummary;
import com.trubeacon.cloverandroidapi.employee.Shift;
import com.trubeacon.cloverandroidapi.employee.service.CreateEmployee.CreateEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.CreateEmployeeShift.CreateEmployeeShiftCallback;
import com.trubeacon.cloverandroidapi.employee.service.DeleteEmployee.DeleteEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployee.GetEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeOrders.GetEmployeeOrdersCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeShift.GetEmployeeShiftCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeShifts.GetEmployeeShiftsCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployeeSummary.GetEmployeeSummaryCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetEmployees.GetEmployeesCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetShift.GetShiftCallback;
import com.trubeacon.cloverandroidapi.employee.service.GetShifts.GetShiftsCallback;
import com.trubeacon.cloverandroidapi.employee.service.UpdateEmployee.UpdateEmployeeCallback;
import com.trubeacon.cloverandroidapi.employee.service.UpdateEmployeeShift.UpdateEmployeeShiftCallback;
import com.trubeacon.cloverandroidapi.order.Order;

public class EmployeeServiceImpl implements EmployeeService,
											GetEmployeesCallback,
											CreateEmployeeCallback,
											GetEmployeeCallback,
											UpdateEmployeeCallback,
											DeleteEmployeeCallback,
											GetShiftsCallback,
											GetShiftCallback,
											GetEmployeeShiftsCallback,
											CreateEmployeeShiftCallback,
											GetEmployeeShiftCallback,
											UpdateEmployeeShiftCallback,
											GetEmployeeOrdersCallback,
											GetEmployeeSummaryCallback {

	private GetEmployeesCallback getEmployeesCallback;
	private CreateEmployeeCallback createEmployeeCallback;
	private GetEmployeeCallback getEmployeeCallback;
	private UpdateEmployeeCallback updateEmployeeCallback;
	private DeleteEmployeeCallback deleteEmployeeCallback;
	private GetShiftsCallback getShiftsCallback;
	private GetShiftCallback getShiftCallback;
	private GetEmployeeShiftsCallback getEmployeeShiftsCallback;
	private CreateEmployeeShiftCallback createEmployeeShiftCallback;
	private GetEmployeeShiftCallback getEmployeeShiftCallback;
	private UpdateEmployeeShiftCallback updateEmployeeShiftCallback;
	private GetEmployeeOrdersCallback getEmployeeOrdersCallback;
	private GetEmployeeSummaryCallback getEmployeeSummaryCallback;
	
	@Override
    public List<Employee> getEmployees(String mId, String token, Object... params) throws RESTException {
	    return new GetEmployees(mId, token, params).execute();
    }

	@Override
    public Employee createEmployee(String mId, String token, Employee employee) throws RESTException {
	    return new CreateEmployee(mId, token, employee).execute();
    }

	@Override
    public Employee getEmployee(String mId, String token, String empId) throws RESTException {
	    return new GetEmployee(mId, token, empId).execute();
    }

	@Override
    public Employee updateEmployee(String mId, String token, String empId, Employee update) throws RESTException {
	    return new UpdateEmployee(mId, token, empId, update).execute();
    }

	@Override
    public Employee deleteEmployee(String mId, String token, String empId) throws RESTException {
	    return new DeleteEmployee(mId, token, empId).execute();
    }

	@Override
    public List<Shift> getShifts(String mId, String token, Object... params) throws RESTException {
	    return new GetShifts(mId, token, params).execute();
    }

	@Override
    public Shift getShift(String mId, String token, String shiftId) throws RESTException {
	    return new GetShift(mId, token, shiftId).execute();
    }

	@Override
    public List<Shift> getEmployeeShifts(String mId, String token, String empId, Object... params) throws RESTException {
	    return new GetEmployeeShifts(mId, token, empId, params).execute();
    }

	@Override
    public Shift createEmployeeShift(String mId, String token, String empId, Shift shift) throws RESTException {
	    return new CreateEmployeeShift(mId, token, empId, shift).execute();
    }

	@Override
    public Shift getEmployeeShift(String mId, String token, String empId, String shiftId) throws RESTException {
	    return new GetEmployeeShift(mId, token, empId, shiftId).execute();
    }

	@Override
    public Shift updateEmployeeShift(String mId, String token, String empId, String shiftId, Shift update) throws RESTException {
	    return new UpdateEmployeeShift(mId, token, empId, shiftId, update).execute();
    }

	@Override
    public List<Order> getEmployeeOrders(String mId, String token, String empId, Object... params) throws RESTException {
	    return new GetEmployeeOrders(mId, token, empId, params).execute();
    }

	@Override
    public WrappedList<EmployeeSummary> getEmployeeSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetEmployeeSummary(mId, token, params).execute();
    }

	@Override
    public void getEmployees(String mId, String token,
            GetEmployeesCallback callback, Object... params) {
	    this.getEmployeesCallback = callback;
	    new GetEmployees(mId, token, params).execute(this);
    }

	@Override
    public void createEmployee(String mId, String token, Employee employee,
            CreateEmployeeCallback callback) {
	    this.createEmployeeCallback = callback;
	    new CreateEmployee(mId, token, employee).execute(this);
    }

	@Override
    public void getEmployee(String mId, String token, String empId,
            GetEmployeeCallback callback) {
	    this.getEmployeeCallback = callback;
	    new GetEmployee(mId, token, empId).execute(this);
    }

	@Override
    public void updateEmployee(String mId, String token, String empId,
            Employee update, UpdateEmployeeCallback callback) {
	    this.updateEmployeeCallback = callback;
	    new UpdateEmployee(mId, token, empId, update).execute(this);
    }

	@Override
    public void deleteEmployee(String mId, String token, String empId,
            DeleteEmployeeCallback callback) {
	    this.deleteEmployeeCallback = callback;
	    new DeleteEmployee(mId, token, empId).execute(this);
    }

	@Override
    public void getShifts(String mId, String token, GetShiftsCallback callback,
            Object... params) {
	    this.getShiftsCallback = callback;
	    new GetShifts(mId, token, params).execute(this);
    }

	@Override
    public void getShift(String mId, String token, String shiftId,
            GetShiftCallback callback) {
	    this.getShiftCallback = callback;
	    new GetShift(mId, token, shiftId).execute(this);
    }

	@Override
    public void getEmployeeShifts(String mId, String token, String empId,
            GetEmployeeShiftsCallback callback, Object... params) {
	    this.getEmployeeShiftsCallback = callback;
	    new GetEmployeeShifts(mId, token, empId, params).execute(this);
    }

	@Override
    public void createEmployeeShift(String mId, String token, String empId,
            Shift shift, CreateEmployeeShiftCallback callback) {
	    this.createEmployeeShiftCallback = callback;
	    new CreateEmployeeShift(mId, token, empId, shift).execute(this);
    }

	@Override
    public void getEmployeeShift(String mId, String token, String empId,
            String shiftId, GetEmployeeShiftCallback callback) {
	    this.getEmployeeShiftCallback = callback;
	    new GetEmployeeShift(mId, token, empId, shiftId).execute(this);
    }

	@Override
    public void updateEmployeeShift(String mId, String token, String empId,
            String shiftId, Shift update, UpdateEmployeeShiftCallback callback) {
	    this.updateEmployeeShiftCallback = callback;
	    new UpdateEmployeeShift(mId, token, empId, shiftId, update).execute(this);
    }

	@Override
    public void getEmployeeOrders(String mId, String token, String empId,
            GetEmployeeOrdersCallback callback, Object... params) {
	    this.getEmployeeOrdersCallback = callback;
	    new GetEmployeeOrders(mId, token, empId, params).execute(this);
    }

	@Override
    public void getEmployeeSummary(String mId, String token,
            GetEmployeeSummaryCallback callback, Object... params) {
	    this.getEmployeeSummaryCallback = callback;
	    new GetEmployeeSummary(mId, token, params).execute(this);
    }

	@Override
    public void onGetEmployeeSummary(WrappedList<EmployeeSummary> result) {
	    if (this.getEmployeeSummaryCallback != null) {
	    	this.getEmployeeSummaryCallback.onGetEmployeeSummary(result);
	    }
    }

	@Override
    public void onFailGetEmployeeSummary(Error error) {
	    if (this.getEmployeeSummaryCallback != null) {
	    	this.getEmployeeSummaryCallback.onFailGetEmployeeSummary(error);
	    }
    }

	@Override
    public void onGetEmployeeOrders(WrappedList<Order> result) {
	    if (this.getEmployeeOrdersCallback != null) {
	    	this.getEmployeeOrdersCallback.onGetEmployeeOrders(result);
	    }
    }

	@Override
    public void onFailGetEmployeeOrders(Error error) {
	    if (this.getEmployeeOrdersCallback != null) {
	    	this.getEmployeeOrdersCallback.onFailGetEmployeeOrders(error);
	    }
    }

	@Override
    public void onUpdateEmployeeShift(Shift result) {
	    if (this.updateEmployeeShiftCallback != null) {
	    	this.updateEmployeeShiftCallback.onUpdateEmployeeShift(result);
	    }
    }

	@Override
    public void onFailUpdateEmployeeShift(Error error) {
	    if (this.updateEmployeeShiftCallback != null) {
	    	this.updateEmployeeShiftCallback.onFailUpdateEmployeeShift(error);
	    }
    }

	@Override
    public void onGetEmployeeShift(Shift result) {
	    if (this.getEmployeeShiftCallback != null) {
	    	this.getEmployeeShiftCallback.onGetEmployeeShift(result);
	    }
    }

	@Override
    public void onFailGetEmployeeShift(Error error) {
	    if (this.getEmployeeShiftCallback != null) {
	    	this.getEmployeeShiftCallback.onFailGetEmployeeShift(error);
	    }
    }

	@Override
    public void onCreateEmployeeShift(Shift result) {
	    if (this.createEmployeeShiftCallback != null) {
	    	this.createEmployeeShiftCallback.onCreateEmployeeShift(result);
	    }
    }

	@Override
    public void onFailCreateEmployeeShift(Error error) {
	    if (this.createEmployeeShiftCallback != null) {
	    	this.createEmployeeShiftCallback.onFailCreateEmployeeShift(error);
	    }
    }

	@Override
	public void onGetEmployees(WrappedList<Employee> result) {
		if (this.getEmployeesCallback != null) {
			getEmployeesCallback.onGetEmployees(result);
		}
    }

	@Override
	public void onFailGetEmployees(Error error) {
		if (this.getEmployeesCallback != null) {
			getEmployeesCallback.onFailGetEmployees(error);
		}
    }

	@Override
	public void onCreateEmployee(Employee result) {
		if (this.createEmployeeCallback != null) {
			createEmployeeCallback.onCreateEmployee(result);
		}
    }

	@Override
	public void onFailCreateEmployee(Error error) {
		if (this.createEmployeeCallback != null) {
			createEmployeeCallback.onFailCreateEmployee(error);
		}
    }

	@Override
	public void onGetEmployee(Employee result) {
		if (this.getEmployeeCallback != null) {
			getEmployeeCallback.onGetEmployee(result);
		}
    }

	@Override
	public void onFailGetEmployee(Error error) {
		if (this.getEmployeeCallback != null) {
			getEmployeeCallback.onFailGetEmployee(error);
		}
    }

	@Override
	public void onUpdateEmployee(Employee result) {
		if (this.updateEmployeeCallback != null) {
			updateEmployeeCallback.onUpdateEmployee(result);
		}
    }

	@Override
	public void onFailUpdateEmployee(Error error) {
		if (this.updateEmployeeCallback != null) {
			updateEmployeeCallback.onFailUpdateEmployee(error);
		}
    }

	@Override
	public void onDeleteEmployee(Employee result) {
		if (this.deleteEmployeeCallback != null) {
			deleteEmployeeCallback.onDeleteEmployee(result);
		}
    }

	@Override
	public void onFailDeleteEmployee(Error error) {
		if (this.deleteEmployeeCallback != null) {
			deleteEmployeeCallback.onFailDeleteEmployee(error);
		}
    }

	@Override
	public void onGetShifts(WrappedList<Shift> result) {
		if (this.getShiftsCallback != null) {
			getShiftsCallback.onGetShifts(result);
		}
    }

	@Override
	public void onFailGetShifts(Error error) {
		if (this.getShiftsCallback != null) {
			getShiftsCallback.onFailGetShifts(error);
		}
    }

	@Override
	public void onGetShift(Shift result) {
		if (this.getShiftCallback != null) {
			getShiftCallback.onGetShift(result);
		}
    }

	@Override
	public void onFailGetShift(Error error) {
		if (this.getShiftCallback != null) {
			getShiftCallback.onFailGetShift(error);
		}
    }

	@Override
	public void onGetEmployeeShifts(WrappedList<Shift> result) {
		if (this.getEmployeeShiftsCallback != null) {
			getEmployeeShiftsCallback.onGetEmployeeShifts(result);
		}
    }

	@Override
	public void onFailGetEmployeeShifts(Error error) {
		if (this.getEmployeeShiftsCallback != null) {
			getEmployeeShiftsCallback.onFailGetEmployeeShifts(error);
		}
    }

}
