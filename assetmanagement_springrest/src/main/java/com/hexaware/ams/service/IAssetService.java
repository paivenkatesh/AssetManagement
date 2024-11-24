package com.hexaware.ams.service;
/*
@Author: Arghya Mandal
Date: 9-11-2024
*/
import java.util.List;

import com.hexaware.ams.dto.AssetDto;
import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetCategory;

public interface IAssetService {
	public Asset addAsset(AssetDto assetDto);
	public Asset updateAsset(AssetDto assetDto);
    public Asset getAssetById(Integer assetId);
    public List<Asset> getAllAssets();
    public List<Asset> getAssetsByCategory(AssetCategory categoryName);
    public void deleteAsset(Integer assetId);
}
