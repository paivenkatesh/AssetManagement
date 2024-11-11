package com.hexaware.ams.entity;

/*
 * Author: Arghya Mandal
 * Date: 31-10-2024
 * Entity Classes
 */

public class Employee {
	
	private int employeeID;
	private String name;
	private Gender gender;
	private String contactNumber;
	private String address;
	private String email;
	private String password;
	private Role role;
	
	public Employee() {
		super();
	}

	public Employee(int employeeID, String name, Gender gender, String contactNumber, String address, String email,
			String password, Role role) {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", name=" + name + ", gender=" + gender + ", contactNumber="
				+ contactNumber + ", address=" + address + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}
	
	public enum Gender{
		Male, Female, Other
	}
	
	
}
