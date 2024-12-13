package com.hexaware.ams.controller;

/*
 * Author: Venkatesh Pai
 * Date: 18-11-24
 * Description: Employee Controller
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.dto.EmployeeDto;
import com.hexaware.ams.dto.EmployeeDto.GenderDTO;
import com.hexaware.ams.dto.RoleDto;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.Employee.Gender;
import com.hexaware.ams.entity.Role;
import com.hexaware.ams.service.IEmployeeService;

import jakarta.validation.Valid;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/ams/employee")
@Validated
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	// Register a New Employee
	@PostMapping("/register")
	public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

		Employee registeredEmployee = employeeService.registerEmployee(employeeDto);

		return new ResponseEntity<>(registeredEmployee, HttpStatus.CREATED);
	}

	// Get Employee By Id
	@GetMapping("/getEmployeeById/{employeeId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {

		Employee employee = employeeService.getEmployeeById(employeeId);

		return ResponseEntity.ok(employee);

	}

	// Update existing Employee details
	@PutMapping("/updateEmployee/{employeeId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable  int employeeId, @RequestBody EmployeeDto employeeDetails) {

		Employee updateEmployee = employeeService.updateEmployee(employeeId, employeeDetails);

		return ResponseEntity.ok(updateEmployee);
	}

	// Delete an Employee Record
	@DeleteMapping("/delete/{employeeId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId) {

		employeeService.deleteEmployee(employeeId);

		return ResponseEntity.noContent().build();
	}

	// List of all current Employees
	@GetMapping("/getAllEmployee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Employee>> getAllEmployee() {

		List<Employee> employee = employeeService.getAllEmployee();

		return ResponseEntity.ok(employee);
	}
	
}
