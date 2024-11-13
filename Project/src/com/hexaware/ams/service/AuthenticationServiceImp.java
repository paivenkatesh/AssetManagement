package com.hexaware.ams.service;
/*
 * Author: Venkatesh Pai
 * Date: 09-11-2024
 * Comment: Created Interface and Implemented it.
 */
import com.hexaware.ams.entity.Employee;

public class AuthenticationServiceImp implements IAuthenticationService {

	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee getEmployeeFromToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
