package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImp implements IEmployeeService {

	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Override
	public Employee registerEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		
		return employeeRepository.findById(employeeId).orElse(new Employee());
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(int employeeId) {
		
		employeeRepository.deleteById(employeeId);

	}

	@Override
	public List<Employee> getAllEmployee() {
		
		return employeeRepository.findAll();
	}

	@Override
	public int findByEmailandPassword(String email, String password) {
		
		return employeeRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public boolean existsByEmail(String email) {
		
		return employeeRepository.existsByEmail(email);
	}

}
