package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.Employee;

public interface IEmployeeService {
	
	Employee registerEmployee(Employee employee);
	Employee getEmployeeById(int employeeId);
	Employee updateEmployee(int employeeId);
	void deleteEmployee(int employeeId);
	List<Employee> getAllEmployee();
	Employee authenticate(String email, String password);

}
