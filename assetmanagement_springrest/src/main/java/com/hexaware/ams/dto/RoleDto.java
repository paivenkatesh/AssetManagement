package com.hexaware.ams.dto;

/*
 * Author: Venkatesh Pai
 * Date: 24-11-24
 * Description: Role Dto
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
	
	private int roleId;
	private String roleName;

}
