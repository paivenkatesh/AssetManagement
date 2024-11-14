package com.hexaware.ams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private int employeeId;

	private String name;
	private Gender gender;

	private String contactNumber;
	private String address;

	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "roleId", nullable = false)
	private Role role;

	public Employee() {
		super();
	}

	public Employee(int employeeId, String name, Gender gender, String contactNumber, String address, String email,
			String password, Role role) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.address = address;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", gender=" + gender + ", contactNumber="
				+ contactNumber + ", address=" + address + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}

	public enum Gender {

		Male, Female, Other
	}

}
