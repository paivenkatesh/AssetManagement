package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.Employee;

public interface IEmployeeService {
	
	Employee registerEmployee(Employee employee);
	Employee getEmployeeById(int employeeId);
	Employee updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	List<Employee> getAllEmployee();
	int findByEmailandPassword(String email, String password);
	boolean existsByEmail(String email);

}
