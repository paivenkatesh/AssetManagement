package com.hexaware.ams.service;
/*
 * Author: Arghya Mandal
 * Date: 09-11-2024
 * Comment: Created Interface and Implemented it.
 */
import java.util.List;

import com.hexaware.ams.entity.AssetBorrowing;

public class AssetBorrowingServiceImp implements IAssetBorrowingService {

	@Override
	public List<AssetBorrowing> getAllAssetBorrowing() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssetBorrowing getAssetBorrowingById(int borrowingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetBorrowing> getAssetBorrowingsByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetBorrowing> getAssetBorrowingsByAssetId(int assetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetBorrowing> getAssetBorrowingByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addAssetBorrowing(AssetBorrowing assetBorrowing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAssetStatus(int borrowingId, String status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAssetBorrowing(int borrowingId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
