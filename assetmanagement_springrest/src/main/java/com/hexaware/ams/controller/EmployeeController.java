package com.hexaware.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.service.IEmployeeService;

@RestController
@RequestMapping("/ams/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeService service;
	
	@PostMapping("/register")
	public Employee registerEmployee(@RequestBody Employee employee) {
		
		return service.registerEmployee(employee);
	}
	

}
