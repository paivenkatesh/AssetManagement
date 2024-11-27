package com.hexaware.ams.entity;
/*
 * @Author: Arghya Mandal
 * @Date: 02-11-2024
 * @Description: Entity class representing an asset category, including attributes such as category ID and category name, with validation constraints for the category name.
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "asset_category")
public class AssetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @NotNull(message = "Category name cannot be null")
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

	public AssetCategory() {
		super();
	}

	public AssetCategory(int categoryId,
			@NotNull(message = "Category name cannot be null") @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters") String categoryName) {
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