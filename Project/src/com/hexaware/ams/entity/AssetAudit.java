package com.hexaware.ams.entity;

/*
* Author: Venkatesh Pai 
* Date: 31-10-2024
* Entity classes created
*/
import java.util.*;
import java.sql.Time;

public class AssetAudit {
	
	private int auditID;
	private int employeeID;
	private int assetID;
	private String auditStatus;
	private Time requestedAt;
	private Time updatedAt;
	
	public AssetAudit() {
		super();
	}

	public AssetAudit(int auditID, int employeeID, int assetID, String auditStatus, Time requestedAt, Time updatedAt) {
		super();
		this.auditID = auditID;
		this.employeeID = employeeID;
		this.assetID = assetID;
		this.auditStatus = auditStatus;
		this.requestedAt = requestedAt;
		this.updatedAt = updatedAt;
	}

	public int getAuditID() {
		return auditID;
	}

	public void setAuditID(int auditID) {
		this.auditID = auditID;
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

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Time getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Time requestedAt) {
		this.requestedAt = requestedAt;
	}

	public Time getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Time updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "AssetAudit [auditID=" + auditID + ", employeeID=" + employeeID + ", assetID=" + assetID
				+ ", auditStatus=" + auditStatus + ", requestedAt=" + requestedAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	

}
