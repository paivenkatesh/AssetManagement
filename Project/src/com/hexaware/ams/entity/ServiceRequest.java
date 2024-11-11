package com.hexaware.ams.entity;

/*
 * Author: Arghya Mandal
 * Date: 31-10-2024
 * Entity Classes
 */

import java.sql.Time;
import java.util.Date;

public class ServiceRequest {

	private int serviceRequestId;
	private Employee employee;
	private Asset asset;
	private String description;
	private IssueType issueType;
	private Status status;
	private Date requestedAt;

	public ServiceRequest() {
		super();
	}

	public ServiceRequest(int serviceRequestId, Employee employee, Asset asset, String description, IssueType issueType,
			Status status, Date requestedAt) {
		super();
		this.serviceRequestId = serviceRequestId;
		this.employee = employee;
		this.asset = asset;
		this.description = description;
		this.issueType = issueType;
		this.status = status;
		this.requestedAt = requestedAt;
	}

	public int getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(int serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}

	@Override
	public String toString() {
		return "ServiceRequest [serviceRequestId=" + serviceRequestId + ", employee=" + employee + ", asset=" + asset
				+ ", description=" + description + ", issueType=" + issueType + ", status=" + status + ", requestedAt="
				+ requestedAt + "]";
	}

	public enum Status {
		Pending, Inprogress, Completed
	}

}