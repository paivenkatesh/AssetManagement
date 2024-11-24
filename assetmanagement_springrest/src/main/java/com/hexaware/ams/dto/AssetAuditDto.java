package com.hexaware.ams.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetAuditDto {
	public enum AuditStatus {
        Pending,
        Verified,
        Rejected
    }
	private int auditId;
	private EmployeeDto employeeDto;
	private AssetDto assetDto;
	private AuditStatus auditStatus = AuditStatus.Pending;
	private LocalDateTime requestedAt;
	private LocalDateTime updatedAt;
}
