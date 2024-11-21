package com.hexaware.ams.service;
/*
@Author: Arghya Mandal
Date: 9-11-2024
*/
import java.util.List;

import com.hexaware.ams.entity.AssetBorrowing;

public interface IAssetBorrowingService {
	public AssetBorrowing borrowAsset(int employeeId, int assetId);
	public AssetBorrowing returnAsset(int borrowingId);
	public List<AssetBorrowing> getBorrowingsByEmployee(int employeeId);
	public List<AssetBorrowing> getAllActiveBorrowings();
}
