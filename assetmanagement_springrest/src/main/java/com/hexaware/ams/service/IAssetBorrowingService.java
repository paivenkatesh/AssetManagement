package com.hexaware.ams.service;
//Author: Arghya Mandal
import java.util.List;

import com.hexaware.ams.entity.AssetBorrowing;

public interface IAssetBorrowingService {
	public AssetBorrowing borrowAsset(int employeeId, int assetId);
	public AssetBorrowing returnAsset(int borrowingId);
	public List<AssetBorrowing> getBorrowingsByEmployee(int employeeId);
	public List<AssetBorrowing> getAllActiveBorrowings();
}
