package com.hexaware.ams.service;
/*
Author: Arghya Mandal
Date: 20-11-2024
*/
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hexaware.ams.dto.AssetDto;
import com.hexaware.ams.dto.AssetCategoryDto;
import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetCategory;
import com.hexaware.ams.repository.IAssetRepository;

@ExtendWith(MockitoExtension.class)
public class AssetServiceImpTest {

    @Mock
    private IAssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImp assetService;

    private Asset asset;
    private AssetCategoryDto categoryDto;
    private AssetDto assetDto;

    @BeforeEach
    public void setUp() {
        // Setting up AssetCategoryDto
        categoryDto = new AssetCategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("Electronics");

        // Setting up AssetDto
        assetDto = new AssetDto();
        assetDto.setAssetId(1);
        assetDto.setAssetName("Laptop");
        assetDto.setCategory(categoryDto);
        assetDto.setAssetModel("XPS 13");
        assetDto.setManufacturingDate(LocalDate.of(2024, 1, 1));
        assetDto.setExpiryDate(LocalDate.of(2026, 1, 1));
        assetDto.setAssetValue(1000.0);
        assetDto.setStatus(AssetDto.Status.Available);

        // Setting up Asset (for mocking the repository)
        asset = new Asset();
        asset.setAssetId(1);
        asset.setAssetName("Laptop");
        asset.setCategory(new AssetCategory(1, "Electronics")); // mapping AssetCategoryDto to AssetCategory
        asset.setStatus(Asset.Status.Available);
    }

    @Test
    public void testAddAsset() {
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        Asset savedAsset = assetService.addAsset(assetDto);

        assertNotNull(savedAsset);
        assertEquals(asset.getAssetId(), savedAsset.getAssetId());
        assertEquals(asset.getAssetName(), savedAsset.getAssetName());
    }

    @Test
    public void testUpdateAsset() {
        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        Asset updatedAsset = assetService.updateAsset(assetDto);

        assertNotNull(updatedAsset);
        assertEquals(asset.getAssetId(), updatedAsset.getAssetId());
        assertEquals(asset.getAssetName(), updatedAsset.getAssetName());
    }

    @Test
    public void testGetAssetById() {
        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));

        Asset foundAsset = assetService.getAssetById(asset.getAssetId());

        assertNotNull(foundAsset);
        assertEquals(asset.getAssetId(), foundAsset.getAssetId());
    }

    @Test
    public void testGetAllAssets() {
        when(assetRepository.findAll()).thenReturn(Arrays.asList(asset));

        List<Asset> assets = assetService.getAllAssets();

        assertNotNull(assets);
        assertFalse(assets.isEmpty());
    }

    @Test
    public void testGetAssetsByCategory() {
        when(assetRepository.findAssetByCategory(any(AssetCategory.class))).thenReturn(Arrays.asList(asset));

        List<Asset> assets = assetService.getAssetsByCategory(asset.getCategory());

        assertNotNull(assets);
        assertFalse(assets.isEmpty());
    }

    @Test
    public void testDeleteAsset() {
        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));

        assetService.deleteAsset(asset.getAssetId());
    }
}
