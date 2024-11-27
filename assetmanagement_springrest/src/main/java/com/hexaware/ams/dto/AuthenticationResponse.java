package com.hexaware.ams.dto;

/*
 * Author: Venkatesh Pai
 * Date: 25-11-24
 * Description: Authentication Response Dto
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}