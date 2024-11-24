package com.hexaware.ams.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetBorrowingDTO {
	private int borrowingId;
	private int employeeId;
	private int assetId;
	private LocalDateTime borrowedAt;
	private LocalDateTime returnedAt;
	private String status;
}
