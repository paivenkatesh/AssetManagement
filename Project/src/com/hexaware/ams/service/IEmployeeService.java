package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.Employee;

public interface IEmployeeService {
	
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int employeeId);
	int addEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(int employeeId);

}
