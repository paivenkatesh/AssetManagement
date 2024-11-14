/* 
 * Need to implement the asset attribute once 
 * merged with master
 */

package com.hexaware.ams.entity;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "service_request")
public class ServiceRequest {

	@Id
    private int serviceRequestId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    /*
    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;
    */

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "issue_type_id", nullable = false)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    private Status status;

    
    private Date requestedAt;

	public ServiceRequest() {
		super();
	}

	public ServiceRequest(int serviceRequestId, Employee employee, String description, IssueType issueType,
			Status status, Date requestedAt) {
		super();
		this.serviceRequestId = serviceRequestId;
		this.employee = employee;
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
		return "ServiceRequest [serviceRequestId=" + serviceRequestId + ", employee=" + employee + ", description="
				+ description + ", issueType=" + issueType + ", status=" + status + ", requestedAt=" + requestedAt
				+ "]";
	}
    
    public enum Status {
    	Pending, Inprogress, Completed
    }
    
}
