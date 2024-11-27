package com.hexaware.ams.dto;

/*
 * Author: Venkatesh Pai
 * Date 24-1124
 * Description: Employee Dto
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	
	private int employeeId;
	private String name;
	private GenderDTO gender;
	private String contactNumber;
	private String address;
	private String email;
	private String password;
	private RoleDto role;
	
	public enum GenderDTO{
		Male, Female, Other;
	}

}
