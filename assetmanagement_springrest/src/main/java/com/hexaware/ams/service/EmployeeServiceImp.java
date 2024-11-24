package com.hexaware.ams.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.dto.EmployeeDto;
import com.hexaware.ams.dto.RoleDto;
import com.hexaware.ams.dto.EmployeeDto.GenderDTO;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.Role;
import com.hexaware.ams.entity.Employee.Gender;
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
	public Employee registerEmployee(EmployeeDto employeeDto) {

		if (employeeRepository.existsById(employeeDto.getEmployeeId())) {

			throw new ResourceAlreadyExistsException("Id" + employeeDto.getEmployeeId() + " already exists ");

		}

		
		try {
			
			Employee employee = mapToEntity(employeeDto);
			
			logger.info("Employee added successfully");
			
			return employeeRepository.save(employee);
			

		}catch(Exception e) {
			
			throw new BadRequestException("Failed to add Employee:" + e.getMessage());
		}
		
	}

	@Override
	public Employee getEmployeeById(int employeeId) {

		logger.info("Getting Employee with Id");
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
	}

	@Override
	@Transactional
	public Employee updateEmployee(int employeeId,EmployeeDto employeeDetails) {

		logger.warn("Trying to update employee Details");
		
		Employee existingEmployee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
		
		Employee e1 = mapToEntity(employeeDetails);

		try {
			
			existingEmployee.setName(e1.getName());
			existingEmployee.setGender(e1.getGender());
			existingEmployee.setContactNumber(e1.getContactNumber());
			existingEmployee.setAddress(e1.getAddress());
			existingEmployee.setEmail(e1.getEmail());
			existingEmployee.setPassword(e1.getPassword());
			existingEmployee.setRole(e1.getRole());

			
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
	
	
	//Helper method to convert DTO to Entity
		public Employee mapToEntity(EmployeeDto employeeDto) {
			
			Employee employee = new Employee();
			if (employeeDto.getEmployeeId() != 0) {
		        employee.setEmployeeId(employeeDto.getEmployeeId());
		    }
			employee.setName(employeeDto.getName());
			employee.setGender(Gender.valueOf(employeeDto.getGender().name()));
			employee.setContactNumber(employeeDto.getContactNumber());
			employee.setAddress(employeeDto.getAddress());
			employee.setEmail(employeeDto.getAddress());
			employee.setPassword(employeeDto.getPassword());
			employee.setRole(new Role (employeeDto.getRole().getRoleId(), employeeDto.getRole().getRoleName()));
			
			return employee;
		}
		

		//Helper method to convert Entity to DTO
		public EmployeeDto mapToDTO(Employee employee) {
			
			return new EmployeeDto(
					employee.getEmployeeId(),
					employee.getName(),
					GenderDTO.valueOf(employee.getGender().name()),
					employee.getContactNumber(),
					employee.getAddress(),
					employee.getEmail(),
					employee.getPassword(),
					new RoleDto (employee.getRole().getRoleId(), employee.getRole().getRoleName())
					);

		}
		
}
