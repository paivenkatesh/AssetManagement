package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.AssetBorrowing;
import com.hexaware.ams.repository.IAssetBorrowingRepository;
@Service
public class AssetBorrowingServiceImp implements IAssetBorrowingService {
	@Autowired
	IAssetBorrowingRepository assetBorrowingRepository;
	@Override
	public List<AssetBorrowing> getAllAssetBorrowing() {
		return assetBorrowingRepository.findAll();
	}

	@Override
	public AssetBorrowing getAssetBorrowingById(int borrowingId) {
		return assetBorrowingRepository.findById(borrowingId).orElse(null);
	}

	@Override
	public List<AssetBorrowing> getAssetBorrowingsByEmployeeId(int employeeId) {
		return assetBorrowingRepository.findByEmployeeId(employeeId);
	}

	@Override
	public List<AssetBorrowing> getAssetBorrowingsByAssetId(int assetId) {
		return assetBorrowingRepository.findByAssetId(assetId);
	}

	@Override
	public List<AssetBorrowing> getAssetBorrowingByStatus(String status) {
		return assetBorrowingRepository.findByStatus(status);
	}

	@Override
	public AssetBorrowing addAssetBorrowing(AssetBorrowing assetBorrowing) {
		return assetBorrowingRepository.save(assetBorrowing);
	}

	@Override
	public void deleteAssetBorrowing(int borrowingId) {
		assetBorrowingRepository.deleteById(borrowingId);
		
	}

	@Override
	public AssetBorrowing updateAssetBorrowing(AssetBorrowing assetBorrowing) {
		return assetBorrowingRepository.save(assetBorrowing);
	}

}
