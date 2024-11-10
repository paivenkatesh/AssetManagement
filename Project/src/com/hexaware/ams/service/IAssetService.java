package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.Asset;

public interface IAssetService {
	int addAsset(Asset asset);
    int updateAsset(Asset asset);
    int deleteAsset(int assetId);
    Asset getAssetById(int assetId);
    List<Asset> getAllAssets();
    List<Asset> getAssetsByCategory(int categoryId);
}
