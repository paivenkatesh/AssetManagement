package com.hexaware.ams.dto;
/*
 * @Author: Arghya Mandal
 * @Date: 23-11-2024
 * @Description: Data Transfer Object for asset borrowing information, including borrowing status and timestamps.
 */
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetBorrowingDto {
	public enum Status {
        Active,
        Returned
    }
	private int borrowingId;
	private EmployeeDto employeeDto;
	private AssetDto assetDto;
	private LocalDateTime borrowedAt;
	private LocalDateTime returnedAt;
	private Status status = Status.Active;
}
