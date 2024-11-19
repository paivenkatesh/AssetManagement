package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.exception.BadRequestException;
import com.hexaware.ams.exception.MethodArgumentNotValidException;
import com.hexaware.ams.exception.ResourceAlreadyExistsException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IEmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImp implements IEmployeeService {

	@Autowired
	IEmployeeRepository employeeRepository;

	@Override
	@Transactional
	public Employee registerEmployee(Employee employee) {

		if (employeeRepository.existsById(employee.getEmployeeId())) {

			throw new ResourceAlreadyExistsException("Id" + employee.getEmployeeId() + " already exists ");

		}

		return employeeRepository.save(employee);

	}

	@Override
	public Employee getEmployeeById(int employeeId) {

		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
	}

	@Override
	@Transactional
	public Employee updateEmployee(int employeeId,Employee employeeDetails) {

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

			return existingEmployee;
			
		} catch (Exception e) {

			throw new BadRequestException("Failed to update Employee:" + e.getMessage());
		}

	}

	@Override
	@Transactional
	public void deleteEmployee(int employeeId) {

		try {

			employeeRepository.deleteById(employeeId);

		} catch (Exception e) {

			throw new BadRequestException("Failed to delete Employee: " + e.getMessage());
		}

	}

	@Override
	public List<Employee> getAllEmployee() {

		List<Employee> employee = employeeRepository.findAll();

		if (employee.isEmpty()) {

			throw new ResourceNotFoundException("No Employee List exists ");
		}

		return employee;
	}

	@Override
	public int findByEmailandPassword(String email, String password) {

		try {

			return employeeRepository.findByEmailAndPassword(email, password);

		} catch (Exception e) {

			throw new ResourceNotFoundException("Entered email and password does not exist" + email + password);
		}

	}

	@Override
	public boolean existsByEmail(String email) {

		try {

			return employeeRepository.existsByEmail(email);

		} catch (Exception e) {

			throw new ResourceNotFoundException("Employee with email: " + email + "does not exist ");
		}

	}

}
