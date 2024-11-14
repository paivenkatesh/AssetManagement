package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.repository.IAssetRepository;
@Service
public class AssetServiceImp implements IAssetService {
	@Autowired
    IAssetRepository assetRepository;
	@Override
	public Asset addAsset(Asset asset) {
		return assetRepository.save(asset);
	}

	@Override
	public Asset getAssetById(Integer assetId) {
		return assetRepository.findById(assetId).orElse(null);
	}

	@Override
	public List<Asset> getAllAssets() {
		return assetRepository.findAll();
	}

	@Override
	public List<Asset> getAssetsByCategory(String categoryName) {
		return assetRepository.findAssetByCategory(categoryName);
	}

	@Override
	public void deleteAsset(Integer assetId) {
		assetRepository.deleteById(assetId);

	}

	@Override
	public Asset updateAsset(Asset asset) {
		return assetRepository.save(asset);
	}

}
