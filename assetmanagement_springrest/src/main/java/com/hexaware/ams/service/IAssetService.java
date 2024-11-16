package com.hexaware.ams.service;
//Author: Arghya Mandal
import java.util.List;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetCategory;

public interface IAssetService {
	public Asset addAsset(Asset asset);
	public Asset updateAsset(Asset asset);
    public Asset getAssetById(Integer assetId);
    public List<Asset> getAllAssets();
    public List<Asset> getAssetsByCategory(AssetCategory categoryName);
    public void deleteAsset(Integer assetId);
}
