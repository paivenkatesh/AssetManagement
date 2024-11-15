package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.AssetBorrowing;

public interface IAssetBorrowingService {
	public List<AssetBorrowing> getAllAssetBorrowing();
	public AssetBorrowing getAssetBorrowingById(int borrowingId);
	public List<AssetBorrowing> getAssetBorrowingsByEmployeeId(int employeeId);
	public List<AssetBorrowing> getAssetBorrowingsByAssetId(int assetId);
	public List<AssetBorrowing> getAssetBorrowingByStatus(String status);
	public AssetBorrowing addAssetBorrowing(AssetBorrowing assetBorrowing);
	public AssetBorrowing updateAssetBorrowing(AssetBorrowing assetBorrowing);
	public void deleteAssetBorrowing(int borrowingId);
}
