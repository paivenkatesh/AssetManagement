package com.hexaware.ams.entity;

/*
 * Author: Arghya Mandal
 * Date: 31-10-2024
 * Entity Classes
 */

import java.sql.Time;

public class ServiceRequest {
	
	private int serviceRequestID;
	private int employeeID;
	private int assetID;
	private String description;
	private IssueType issueType;
	private String status;
	private Time requestedAt;
	
	public ServiceRequest() {
		super();
	}

	public ServiceRequest(int serviceRequestID, int employeeID, int assetID, String description, IssueType issueType,
			String status, Time requestedAt) {
		super();
		this.serviceRequestID = serviceRequestID;
		this.employeeID = employeeID;
		this.assetID = assetID;
		this.description = description;
		this.issueType = issueType;
		this.status = status;
		this.requestedAt = requestedAt;
	}

	public int getServiceRequestID() {
		return serviceRequestID;
	}

	public void setServiceRequestID(int serviceRequestID) {
		this.serviceRequestID = serviceRequestID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getAssetID() {
		return assetID;
	}

	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Time getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Time requestedAt) {
		this.requestedAt = requestedAt;
	}

	@Override
	public String toString() {
		return "ServiceRequest [serviceRequestID=" + serviceRequestID + ", employeeID=" + employeeID + ", assetID="
				+ assetID + ", description=" + description + ", issueType=" + issueType + ", status=" + status
				+ ", requestedAt=" + requestedAt + "]";
	}
	
	
	

}
