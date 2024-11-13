package com.hexaware.ams.entity;
/*
 * Author: Arghya Mandal
 * Date: 9-11-2024
 * Entity classes created
 */
public class AssetCategory {

	private int categoryId;
	private String categoryName;
	public AssetCategory() {
		super();
	}
	public AssetCategory(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "AssetCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	
}
