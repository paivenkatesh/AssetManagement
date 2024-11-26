package com.hexaware.ams.dto;

import java.util.Date;

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
	private Date requestedAt;
	
	
	public enum StatusDTO{
		Pending, Transit, Completed
	}

}
