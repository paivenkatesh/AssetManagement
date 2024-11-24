package com.hexaware.ams.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.exception.BadRequestException;
import com.hexaware.ams.exception.ResourceAlreadyExistsException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IEmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImp implements IEmployeeService {

	@Autowired
	IEmployeeRepository employeeRepository;
	
	Logger logger = LoggerFactory.getLogger(EmployeeServiceImp.class);

	@Override
	@Transactional
	public Employee registerEmployee(Employee employee) {

		if (employeeRepository.existsById(employee.getEmployeeId())) {

			throw new ResourceAlreadyExistsException("Id" + employee.getEmployeeId() + " already exists ");

		}

		logger.info("Employee added successfully");
		return employeeRepository.save(employee);
		

	}

	@Override
	public Employee getEmployeeById(int employeeId) {

		logger.info("Getting Employee with Id");
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
	}

	@Override
	@Transactional
	public Employee updateEmployee(int employeeId,Employee employeeDetails) {

		logger.warn("Trying to update employee Details");
		Employee existingEmployee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

		try {
			
			existingEmployee.setName(employeeDetails.getName());
			existingEmployee.setGender(employeeDetails.getGender());
			existingEmployee.setContactNumber(employeeDetails.getContactNumber());
			existingEmployee.setAddress(employeeDetails.getAddress());
			existingEmployee.setEmail(employeeDetails.getEmail());
			existingEmployee.setPassword(employeeDetails.getPassword());
			existingEmployee.setRole(employeeDetails.getRole());

			
			logger.info("Updating employee details");
			return existingEmployee;
			
		} catch (Exception e) {

			throw new BadRequestException("Failed to update Employee:" + e.getMessage());
		}

	}

	@Override
	@Transactional
	public void deleteEmployee(int employeeId) {

		try {

			logger.warn("Attempting to delete Employee. Details will Erased");
			employeeRepository.deleteById(employeeId);

		} catch (Exception e) {

			throw new BadRequestException("Failed to delete Employee: " + e.getMessage());
		}

	}

	@Override
	public List<Employee> getAllEmployee() {

		List<Employee> employee = employeeRepository.findAll();

		if (employee.isEmpty()) {

			logger.warn("Problem with getting list of employees");
			throw new ResourceNotFoundException("No Employee List exists ");
		}

		logger.info("Employee list retrieved successfully");
		return employee;
	}

	@Override
	public int findByEmailandPassword(String email, String password) {

		try {

			logger.info("Trying to find Employee Email and Password");
			return employeeRepository.findByEmailAndPassword(email, password);

		} catch (Exception e) {

			throw new ResourceNotFoundException("Entered email and password does not exist" + email + password);
		}

	}

	@Override
	public boolean existsByEmail(String email) {

		try {

			logger.info("Checking if Employee email exists");
			return employeeRepository.existsByEmail(email);

		} catch (Exception e) {

			throw new ResourceNotFoundException("Employee with email: " + email + "does not exist ");
		}

	}

}
