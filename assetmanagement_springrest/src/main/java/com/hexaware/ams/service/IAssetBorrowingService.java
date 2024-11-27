package com.hexaware.ams.service;
/*
 * @Author: Arghya Mandal
 * @Date: 09-11-2024
 * @Description: Interface for managing Asset Borrowing operations. This interface defines methods for borrowing and returning assets, retrieving borrowing records by employee, and fetching all active borrowings. It serves as a contract for the service implementation handling asset borrowing functionalities.
 */
import java.util.List;

import com.hexaware.ams.entity.AssetBorrowing;

public interface IAssetBorrowingService {
	public AssetBorrowing borrowAsset(int employeeId, int assetId);
	public AssetBorrowing returnAsset(int borrowingId);
	public List<AssetBorrowing> getBorrowingsByEmployee(int employeeId);
	public List<AssetBorrowing> getAllActiveBorrowings();
}
