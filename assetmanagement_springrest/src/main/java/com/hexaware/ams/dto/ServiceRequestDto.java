package com.hexaware.ams.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestDto {
	
	private int serviceRequestId;
	private EmployeeDto employee;
	private AssetDto asset;
	private String description;
	private IssueTypeDto issueType;
	private StatusDTO status = StatusDTO.Pending;
	private LocalDateTime requestedAt;
	
	
	public enum StatusDTO{
		Pending, Transit, Completed
	}

}
