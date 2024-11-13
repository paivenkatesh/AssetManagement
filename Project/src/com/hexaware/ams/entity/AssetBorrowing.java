package com.hexaware.ams.entity;

/*
 * Author: Arghya Mandal
 * Date: 31-10-2024
 * Entity classes created
 */

import java.sql.Time;
import java.util.Date;

public class AssetBorrowing {

	private int borrowingId;
	private Employee employee;
	private Asset asset;
	private Date borrowedAt;
	private Date returnedAt;
	private Status status;

	public AssetBorrowing() {
		super();
	}

	public AssetBorrowing(int borrowingId, Employee employee, Asset asset, Date borrowedAt, Date returnedAt,
			Status status) {
		super();
		this.borrowingId = borrowingId;
		this.employee = employee;
		this.asset = asset;
		this.borrowedAt = borrowedAt;
		this.returnedAt = returnedAt;
		this.status = status;
	}

	public int getBorrowingId() {
		return borrowingId;
	}

	public void setBorrowingId(int borrowingId) {
		this.borrowingId = borrowingId;
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

	public Date getBorrowedAt() {
		return borrowedAt;
	}

	public void setBorrowedAt(Date borrowedAt) {
		this.borrowedAt = borrowedAt;
	}

	public Date getReturnedAt() {
		return returnedAt;
	}

	public void setReturnedAt(Date returnedAt) {
		this.returnedAt = returnedAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AssetBorrowing [borrowingId=" + borrowingId + ", employee=" + employee + ", asset=" + asset
				+ ", borrowedAt=" + borrowedAt + ", returnedAt=" + returnedAt + ", status=" + status + "]";
	}

	public enum Status {
		Active, Returned
	}

}
