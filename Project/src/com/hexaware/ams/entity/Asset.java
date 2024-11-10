package com.hexaware.ams.entity;

/*
 * Author: Venkatesh Pai 
 * Date: 31-10-2024
 * Entity classes created
 */

import java.util.*;
public class Asset {
	
	private int assetID;
	private String assetName;
	private AssetCategory assetCategory;
	private String assetModel;
	private Date manufacturingDate;
	private Date expiryDate;
	private double assetValue;
	private String status;
	
	public Asset() {
		super();
	}

	public Asset(int assetID, String assetName, AssetCategory assetCategory, String assetModel, Date manufacturingDate,
			Date expiryDate, double assetValue, String status) {
		super();
		this.assetID = assetID;
		this.assetName = assetName;
		this.assetCategory = assetCategory;
		this.assetModel = assetModel;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.assetValue = assetValue;
		this.status = status;
	}

	public int getAssetID() {
		return assetID;
	}

	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public AssetCategory getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(AssetCategory assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getAssetModel() {
		return assetModel;
	}

	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}

	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(double assetValue) {
		this.assetValue = assetValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Asset [assetID=" + assetID + ", assetName=" + assetName + ", assetCategory=" + assetCategory
				+ ", assetModel=" + assetModel + ", manufacturingDate=" + manufacturingDate + ", expiryDate="
				+ expiryDate + ", assetValue=" + assetValue + ", status=" + status + "]";
	}
	
	
	

}
