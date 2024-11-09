package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.AssetBorrowing;

public interface IAssetBorrowingService {

	List<AssetBorrowing> getAllAssetBorrowing();
	AssetBorrowing getAssetBorrowingById(int borrowingId);
	List<AssetBorrowing> getAssetBorrowingsByEmployeeId(int employeeId);
	List<AssetBorrowing> getAssetBorrowingsByAssetId(int assetId);
	List<AssetBorrowing> getAssetBorrowingByStatus(String status);
	int addAssetBorrowing(AssetBorrowing assetBorrowing);
	int updateAssetStatus(int borrowingId, String status);
	int deleteAssetBorrowing(int borrowingId);
}
