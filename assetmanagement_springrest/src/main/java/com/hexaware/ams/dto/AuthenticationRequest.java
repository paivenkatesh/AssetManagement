package com.hexaware.ams.dto;

/*
 * Author: Venkatesh Pai
 * Date: 25-11-24
 * Description: Authentication Request Dto
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}