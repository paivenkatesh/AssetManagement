package com.hexaware.ams.entity;

/*
* Author: Venkatesh Pai 
* Date: 31-10-2024
* Entity classes created
*/
import java.util.*;
import java.sql.Time;

public class AssetAudit {
	
	private int auditId;
	private Employee employee;
	private Asset asset;
	private AuditStatus auditStatus;
	private Date requestedAt;
	private Date updaedAt;
	
	
	
	public AssetAudit() {
		super();
	}


	

	public AssetAudit(int auditId, Employee employee, Asset asset, AuditStatus auditStatus, Date requestedAt,
			Date updaedAt) {
		super();
		this.auditId = auditId;
		this.employee = employee;
		this.asset = asset;
		this.auditStatus = auditStatus;
		this.requestedAt = requestedAt;
		this.updaedAt = updaedAt;
	}




	public int getAuditId() {
		return auditId;
	}




	public void setAuditId(int auditId) {
		this.auditId = auditId;
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




	public AuditStatus getAuditStatus() {
		return auditStatus;
	}




	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}




	public Date getRequestedAt() {
		return requestedAt;
	}




	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}




	public Date getUpdaedAt() {
		return updaedAt;
	}




	public void setUpdaedAt(Date updaedAt) {
		this.updaedAt = updaedAt;
	}




	@Override
	public String toString() {
		return "AssetAudit [auditId=" + auditId + ", employee=" + employee + ", asset=" + asset + ", auditStatus="
				+ auditStatus + ", requestedAt=" + requestedAt + ", updaedAt=" + updaedAt + "]";
	}




	public enum AuditStatus{
		Pending, Verified, Rejected
	}
}