package com.hexaware.ams.service;
/*
 * @Author: Arghya Mandal
 * @Date: 09-11-2024
 * @Description: Service implementation for managing Asset entities. This class provides methods to add, update, retrieve, delete assets, and fetch assets by category. It handles validation and exception scenarios, ensuring proper error handling during asset operations and uses the repository for database interactions.
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.dto.AssetCategoryDto;
import com.hexaware.ams.dto.AssetDto;
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
    public Asset addAsset(AssetDto assetDto) {
        try {
        	Asset asset = new Asset();
        	asset.setAssetName(assetDto.getAssetName());
            asset.setAssetModel(assetDto.getAssetModel());
            asset.setCategory(mapToCategory(assetDto.getCategory()));
            asset.setManufacturingDate(assetDto.getManufacturingDate());
            asset.setExpiryDate(assetDto.getExpiryDate());
            asset.setAssetValue(assetDto.getAssetValue());
            asset.setStatus(Asset.Status.Available);

            Asset savedAsset = assetRepository.save(asset);
            logger.info("Asset added with ID: {}", savedAsset.getAssetId());
            return savedAsset;
        } catch(Exception e) {
            throw new BadRequestException("Failed to add asset: " + e.getMessage());
        }
    }

    @Override
    public Asset updateAsset(AssetDto assetDto) {
    	// checking if asset exists or not
    	Asset existingAsset = assetRepository.findById(assetDto.getAssetId())
    			.orElseThrow(() -> new ResourceNotFoundException("Asset not found with ID: " + assetDto.getAssetId()));
    	try {
    		logger.info("Asset updated with id: " + existingAsset.getAssetId());
    		existingAsset.setAssetName(assetDto.getAssetName());
            existingAsset.setAssetModel(assetDto.getAssetModel());
            existingAsset.setCategory(mapToCategory(assetDto.getCategory()));
            existingAsset.setManufacturingDate(assetDto.getManufacturingDate());
            existingAsset.setExpiryDate(assetDto.getExpiryDate());
            existingAsset.setAssetValue(assetDto.getAssetValue());
            logger.info("Asset updated with ID: {}", existingAsset.getAssetId());
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
    
    // mapping AssetCategoryDto object to AssetCategory object
    private AssetCategory mapToCategory(AssetCategoryDto categoryDto) {
        AssetCategory category = new AssetCategory();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
