package com.hexaware.ams.service;

import com.hexaware.ams.entity.Employee;

public interface IAuthenticationService {

	String login(String email, String password);
	void logout(String token);
	boolean validateToken(String token);
	Employee getEmployeeFromToken(String token);
	
}

