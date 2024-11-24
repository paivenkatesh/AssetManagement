package com.hexaware.ams.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssetDto {
	public enum Status {
        Available,
        Borrowed
    }
	private int assetId;
	private String assetName;
	private AssetCategoryDto category;
	private String assetModel;
	private LocalDate manufacturingDate;
	private LocalDate expiryDate;
	private double assetValue;
	private Status status = Status.Available;
}
