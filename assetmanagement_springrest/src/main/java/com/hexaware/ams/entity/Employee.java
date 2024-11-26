package com.hexaware.ams.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * @Author: Venkatesh Pai
 * Employee Entity 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@Column(nullable = false)
	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
	@NotNull(message = "Choose from Male, Female or Other")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull(message = "Contact number cannot be null")
	@Size(min = 7, max = 15, message = "Contact number must be between 7 and 15 characters")
	@Column(name = "contact_number", nullable = false)
	private String contactNumber;
	
	@NotNull(message = "Address cannot be null")
	@Size(min = 3, max = 255, message = "Address must be between 3 and 255 characters")
	@Column(nullable = false)
	private String address;

	@NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
	private String email;
	
	
	@NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters")
	@Column(nullable = false)
	private String password;

	
    @NotNull(message = "Role cannot be null")
	@ManyToOne
	@JoinColumn(name = "role_Id", nullable = false)
	private Role role;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }

	public Employee() {
		super();
	}

	

	public Employee(int employeeId,
			@NotNull(message = "Name cannot be null") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name,
			@NotNull(message = "Choose from Male, Female or Other") Gender gender,
			@NotNull(message = "Contact number cannot be null") @Size(min = 7, max = 15, message = "Contact number must be between 7 and 15 characters") String contactNumber,
			@NotNull(message = "Address cannot be null") @Size(min = 3, max = 255, message = "Address must be between 3 and 255 characters") String address,
			@NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String email,
			@NotNull(message = "Password cannot be null") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@NotNull(message = "Role cannot be null") Role role) {
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
