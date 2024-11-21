package com.hexaware.ams.entity;
/*
@Author: Arghya Mandal
Date: 2-11-2024
*/
import java.time.LocalDateTime;

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

@Entity
@Table(name = "asset_borrowing")
public class AssetBorrowing {

    public enum Status {
        Active,
        Returned
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingId;

    @NotNull(message = "Employee cannot be null")
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull(message = "Asset cannot be null")
    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @NotNull
    @Column(name = "borrowed_at", nullable = false)
    private LocalDateTime borrowedAt = LocalDateTime.now();

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.Active;

	public AssetBorrowing() {
		super();
	}

	public AssetBorrowing(int borrowingId, @NotNull(message = "Employee cannot be null") Employee employee,
			@NotNull(message = "Asset cannot be null") Asset asset, @NotNull LocalDateTime borrowedAt,
			LocalDateTime returnedAt, @NotNull Status status) {
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

	public LocalDateTime getBorrowedAt() {
		return borrowedAt;
	}

	public void setBorrowedAt(LocalDateTime borrowedAt) {
		this.borrowedAt = borrowedAt;
	}

	public LocalDateTime getReturnedAt() {
		return returnedAt;
	}

	public void setReturnedAt(LocalDateTime returnedAt) {
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

    
}