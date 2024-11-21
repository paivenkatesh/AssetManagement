package com.hexaware.ams.service;
/*
@Author: Arghya Mandal
Date: 9-11-2024
*/
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetCategory;
import com.hexaware.ams.exception.BadRequestException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IAssetRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AssetServiceImp implements IAssetService {

    @Autowired
    private IAssetRepository assetRepository;
    Logger logger = LoggerFactory.getLogger(AssetServiceImp.class);
    @Override
    public Asset addAsset(Asset asset) {
        try {
        	logger.info("Asset added with id: "+ asset.getAssetId());
        	return assetRepository.save(asset);
        } catch(Exception e) {
            throw new BadRequestException("Failed to add asset: " + e.getMessage());
        }
    }

    @Override
    public Asset updateAsset(Asset asset) {
    	// checking if asset exists or not
    	Asset existingAsset = assetRepository.findById(asset.getAssetId())
    			.orElseThrow(() -> new ResourceNotFoundException("Asset not found with ID: " + asset.getAssetId()));
    	try {
    		logger.info("Asset updated with id: " + existingAsset.getAssetId());
    		return assetRepository.save(existingAsset);
    	} catch (Exception e) {
    		throw new BadRequestException("Failed to update asset: " + e.getMessage());
    	}
    }

    @Override
    public Asset getAssetById(Integer assetId) {
        logger.info("Trying to get asset with id: " + assetId);
    	return assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found with ID: " + assetId));
    }

    @Override
    public List<Asset> getAllAssets() {
        logger.info("Returning all the asset details.");
    	return assetRepository.findAll();
    }

    @Override
    public List<Asset> getAssetsByCategory(AssetCategory categoryName) {
        // getting assets with the given category
    	List<Asset> assets = assetRepository.findAssetByCategory(categoryName);
        if (assets.isEmpty()) {
            throw new ResourceNotFoundException("No assets found for category: " + categoryName.getCategoryName());
        }
        logger.info("Returning list of assets with category: " + categoryName);
        return assets;
    }

    @Override
    public void deleteAsset(Integer assetId) {
        // checking whether asset exists or not
    	Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found with ID: " + assetId));
    	// checking if asset already exists or not
        if (Asset.Status.Borrowed.equals(asset.getStatus())) {
            throw new BadRequestException("Cannot delete asset as it is currently borrowed");
        }
        logger.warn("Asset with id: " + assetId + " is deleted.");
        assetRepository.deleteById(assetId);
    }
}
