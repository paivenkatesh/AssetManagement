package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.Asset;

public interface IAssetService {
	void addAsset(Asset asset);
    void updateAsset(Asset asset);
    void deleteAsset(int assetId);
    Asset getAssetById(int assetId);
    List<Asset> getAllAssets();
    List<Asset> getAssetsByCategory(int categoryId);
}
