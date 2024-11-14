package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImp implements IEmployeeService {

	@Autowired
	IEmployeeRepository repo;
	
	@Override
	public Employee registerEmployee(Employee employee) {
		
		return repo.save(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee authenticate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
