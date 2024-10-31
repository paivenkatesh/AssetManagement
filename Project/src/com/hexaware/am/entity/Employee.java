package com.hexaware.am.entity;

/*
 * Author: Arghya Mandal
 * Date: 31-10-2024
 * Entity Classes
 */

public class Employee {
	
	private int employeeID;
	private String name;
	private String gender;
	private Double contactNumber;
	private String address;
	private String email;
	private String password;
	private String role;
	
	public Employee() {
		super();
	}

	public Employee(int employeeID, String name, String gender, Double contactNumber, String address, String email,
			String password, String role) {
		super();
		this.employeeID = employeeID;
		this.name = name;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.address = address;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Double contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", name=" + name + ", gender=" + gender + ", contactNumber="
				+ contactNumber + ", address=" + address + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}
	
	
	
}
