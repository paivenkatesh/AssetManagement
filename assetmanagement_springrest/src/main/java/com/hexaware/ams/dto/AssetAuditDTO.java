package com.hexaware.ams.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetAuditDTO {
	private int auditId;
	private int employeeId;
	private int assetId;
	private String auditStatus;
	private LocalDateTime requestedAt;
	private LocalDateTime updatedAt;
}
