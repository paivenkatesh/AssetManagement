package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.service.IEmployeeService;

@RestController
@RequestMapping("/ams/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeService employeeService;
	
	@PostMapping("/register")
	public Employee registerEmployee(@RequestBody Employee employee) {
		
		return employeeService.registerEmployee(employee);
	}
	
	
	@GetMapping("/getEmployeeById/{employeeId}")
	public Employee getEmployeeById(int employeeId) {
		
		return employeeService.getEmployeeById(employeeId);
	}
	
	@PutMapping("/updateEmployee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public void deleteEmployee(int employeeId) {
		
		 employeeService.deleteEmployee(employeeId);
	}
	
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee(){
		
		return employeeService.getAllEmployee();
	}

}
