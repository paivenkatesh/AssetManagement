package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ams/employee")
@Validated
public class EmployeeController {
	
	@Autowired
	IEmployeeService employeeService;
	
	@PostMapping("/register")
	public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody Employee employee) {
		
		Employee registeredEmployee =  employeeService.registerEmployee(employee);
		
		return new ResponseEntity<>(registeredEmployee, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getEmployeeById/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
		
		Employee employee = employeeService.getEmployeeById(employeeId);
		
		return ResponseEntity.ok(employee);
		
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody int employeeId, Employee employeeDetails) {
		
		Employee updateEmployee = employeeService.updateEmployee(employeeId, employeeDetails);
		
		return ResponseEntity.ok(updateEmployee);
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId) {
		
		 employeeService.deleteEmployee(employeeId);
		 
		 return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		
		List<Employee> employee = employeeService.getAllEmployee();
		
		return ResponseEntity.ok(employee);
	}

}
