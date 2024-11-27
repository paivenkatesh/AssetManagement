package com.hexaware.ams.dto;
/*
 * @Author: Arghya Mandal
 * @Date: 23-11-2024
 * @Description: Data Transfer Object for asset category information, including category ID and name.
 */
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
