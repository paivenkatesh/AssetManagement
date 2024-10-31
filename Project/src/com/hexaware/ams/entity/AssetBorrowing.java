package com.hexaware.ams.entity;

/*
 * Author: Venkatesh Pai 
 * Date: 31-10-2024
 * Entity classes created
 */

import java.sql.Time;

public class AssetBorrowing {
	
	private int borrowingID;
	private int employeeID;
	private int assetID;
	private Time borrowedAt;
	private Time returnedAt;
	private String status;
	
	public AssetBorrowing() {
		super();
	}

	public AssetBorrowing(int borrowingID, int employeeID, int assetID, Time borrowedAt, Time returnedAt,
			String status) {
		super();
		this.borrowingID = borrowingID;
		this.employeeID = employeeID;
		this.assetID = assetID;
		this.borrowedAt = borrowedAt;
		this.returnedAt = returnedAt;
		this.status = status;
	}

	public int getBorrowingID() {
		return borrowingID;
	}

	public void setBorrowingID(int borrowingID) {
		this.borrowingID = borrowingID;
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

	public Time getBorrowedAt() {
		return borrowedAt;
	}

	public void setBorrowedAt(Time borrowedAt) {
		this.borrowedAt = borrowedAt;
	}

	public Time getReturnedAt() {
		return returnedAt;
	}

	public void setReturnedAt(Time returnedAt) {
		this.returnedAt = returnedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AssetBorrowing [borrowingID=" + borrowingID + ", employeeID=" + employeeID + ", assetID=" + assetID
				+ ", borrowedAt=" + borrowedAt + ", returnedAt=" + returnedAt + ", status=" + status + "]";
	}
	
	

}
