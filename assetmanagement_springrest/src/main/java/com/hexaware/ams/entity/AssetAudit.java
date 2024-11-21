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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "asset_audit")
public class AssetAudit {

    public enum AuditStatus {
        Pending,
        Verified,
        Rejected
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auditId;

    @NotNull(message = "Employee cannot be null")
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull(message = "Asset cannot be null")
    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @NotNull(message = "Audit status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status", nullable = false)
    private AuditStatus auditStatus = AuditStatus.Pending;

    @NotNull(message = "Requested at cannot be null")
    @Column(name = "requested_at", nullable = false, updatable = false)
    private LocalDateTime requestedAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

	public AssetAudit() {
		super();
	}

	public AssetAudit(int auditId, @NotNull(message = "Employee cannot be null") Employee employee,
			@NotNull(message = "Asset cannot be null") Asset asset,
			@NotNull(message = "Audit status cannot be null") AuditStatus auditStatus,
			@NotNull(message = "Requested at cannot be null") LocalDateTime requestedAt, LocalDateTime updatedAt) {
		super();
		this.auditId = auditId;
		this.employee = employee;
		this.asset = asset;
		this.auditStatus = auditStatus;
		this.requestedAt = requestedAt;
		this.updatedAt = updatedAt;
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

	public LocalDateTime getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(LocalDateTime requestedAt) {
		this.requestedAt = requestedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "AssetAudit [auditId=" + auditId + ", employee=" + employee + ", asset=" + asset + ", auditStatus="
				+ auditStatus + ", requestedAt=" + requestedAt + ", updatedAt=" + updatedAt + "]";
	}

    

}