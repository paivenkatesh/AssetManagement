package com.hexaware.ams.service;
/*
 * @Author: Arghya Mandal
 * @Date: 09-11-2024
 * @Description: Interface for managing Asset operations. This interface defines methods for adding, updating, retrieving, and deleting assets, as well as fetching all assets and retrieving assets by category. It serves as a contract for the service implementation handling asset management functionalities.
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
