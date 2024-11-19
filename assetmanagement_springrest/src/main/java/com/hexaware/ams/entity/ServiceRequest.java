package com.hexaware.ams.entity;

/*
 * @Author: Venkatesh Pai
 * Service Request Entity
 */
import java.util.Date;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "service_request")
public class ServiceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceRequestId;

	@NotNull(message = "Employee cannot be null")
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

	
	
	@NotNull(message = "Asset cannot be null")
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    

	@NotNull(message = "Description cannot be null")
    @Size(min = 5, message = "Description must be at least 5 characters")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "issue_type_id", nullable = false)
    private IssueType issueType;

    
    @NotNull(message = "Status cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.Pending;

    @NotNull(message = "Date cannot be null")
    @Column(name = "requested_at", nullable = false)
    private Date requestedAt;
    
    public enum Status {
    	Pending, Inprogress, Completed
    }

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
    
   
    
}
