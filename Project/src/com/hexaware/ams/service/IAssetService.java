package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.Asset;

public interface IAssetService {

	List<Asset> getAllAsset();
	List<Asset> getAssetByCategory(int categoryId);
	Asset getAssetById(int assetId);
	int addAsset(Asset asset);
	int updateAsset(Asset asset);
	int deleteAsset(int assetId);
	
}
