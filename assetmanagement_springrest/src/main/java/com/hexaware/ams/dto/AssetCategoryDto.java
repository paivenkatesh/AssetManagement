package com.hexaware.ams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetCategoryDto {
	private int categoryId;
	private String categoryName;
}
