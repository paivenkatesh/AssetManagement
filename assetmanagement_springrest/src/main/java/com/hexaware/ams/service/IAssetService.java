package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.Asset;

public interface IAssetService {
	public Asset addAsset(Asset asset);
	public Asset updateAsset(Asset asset);
    public Asset getAssetById(Integer assetId);
    public List<Asset> getAllAssets();
    public List<Asset> getAssetsByCategory(String categoryName);
    public void deleteAsset(Integer assetId);
}
