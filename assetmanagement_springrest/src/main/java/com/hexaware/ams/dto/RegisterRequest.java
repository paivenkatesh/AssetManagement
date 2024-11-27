package com.hexaware.ams.dto;

/*
 * Author: Venkatesh Pai
 * Date: 25-11-24
 * Description: Register Request Dto
 */
import com.hexaware.ams.entity.Employee.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private Gender gender;
    private String contactNumber;
    private String address;
    private String email;
    private String password;
    private int roleId; 
}