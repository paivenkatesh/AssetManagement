package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.dto.EmployeeDto;
import com.hexaware.ams.entity.Employee;

public interface IEmployeeService {
	
	Employee registerEmployee(EmployeeDto employeeDto);
	Employee getEmployeeById(int employeeId);
	Employee updateEmployee(int employeeId, EmployeeDto employeeDetails);
	void deleteEmployee(int employeeId);
	List<Employee> getAllEmployee();
	int findByEmailandPassword(String email, String password);
	boolean existsByEmail(String email);

}
