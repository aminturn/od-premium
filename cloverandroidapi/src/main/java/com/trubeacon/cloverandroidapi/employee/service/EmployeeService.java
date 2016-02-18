package com.trubeacon.cloverandroidapi.employee.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
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

public interface EmployeeService {
	public List<Employee> getEmployees(String mId, String token, Object... params) throws RESTException;
	public void getEmployees(String mId, String token, GetEmployeesCallback callback, Object... params);
	public Employee createEmployee(String mId, String token, Employee employee) throws RESTException;
	public void createEmployee(String mId, String token, Employee employee, CreateEmployeeCallback callback);
	public Employee getEmployee(String mId, String token, String empId) throws RESTException;
	public void getEmployee(String mId, String token, String empId, GetEmployeeCallback callback);
	public Employee updateEmployee(String mId, String token, String empId, Employee update) throws RESTException;
	public void updateEmployee(String mId, String token, String empId, Employee update, UpdateEmployeeCallback callback);
	public Employee deleteEmployee(String mId, String token, String empId) throws RESTException;
	public void deleteEmployee(String mId, String token, String empId, DeleteEmployeeCallback callback);
	public List<Shift> getShifts(String mId, String token, Object... params) throws RESTException;
	public void getShifts(String mId, String token, GetShiftsCallback callback, Object... params);
	public Shift getShift(String mId, String token, String shiftId) throws RESTException;
	public void getShift(String mId, String token, String shiftId, GetShiftCallback callback);
	public List<Shift> getEmployeeShifts(String mId, String token, String empId, Object... params) throws RESTException;
	public void getEmployeeShifts(String mId, String token, String empId, GetEmployeeShiftsCallback callback, Object... params);
	public Shift createEmployeeShift(String mId, String token, String empId, Shift shift) throws RESTException;
	public void createEmployeeShift(String mId, String token, String empId, Shift shift, CreateEmployeeShiftCallback callback);
	public Shift getEmployeeShift(String mId, String token, String empId, String shiftId) throws RESTException;
	public void getEmployeeShift(String mId, String token, String empId, String shiftId, GetEmployeeShiftCallback callback);
	public Shift updateEmployeeShift(String mId, String token, String empId, String shiftId, Shift update) throws RESTException;
	public void updateEmployeeShift(String mId, String token, String empId, String shiftId, Shift update, UpdateEmployeeShiftCallback callback);
	public List<Order> getEmployeeOrders(String mId, String token, String empId, Object... params) throws RESTException;
	public void getEmployeeOrders(String mId, String token, String empId, GetEmployeeOrdersCallback callback, Object... params);
	public WrappedList<EmployeeSummary> getEmployeeSummary(String mId, String token, Object... params) throws RESTException;
	public void getEmployeeSummary(String mId, String token, GetEmployeeSummaryCallback callback, Object... params);
}
