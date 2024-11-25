package com.hexaware.ams.service;
/*
Author: Arghya Mandal
Date: 20-11-2024
*/
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.Asset.Status;
import com.hexaware.ams.entity.AssetCategory;
import com.hexaware.ams.repository.IAssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AssetServiceImpTest {

    @Mock
    private IAssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImp assetService;

    private Asset asset;
    private AssetCategory category;

    @BeforeEach
    public void setUp() {
        category = new AssetCategory();
        category.setCategoryId(1);
        category.setCategoryName("Electronics");

        asset = new Asset();
        asset.setAssetId(1);
        asset.setAssetName("Laptop");
        asset.setCategory(category);
        asset.setStatus(Status.Available);
    }

    @Test
    public void testAddAsset() {
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        /*
        Asset savedAsset = assetService.addAsset(asset);

        assertNotNull(savedAsset);
        assertEquals(asset.getAssetId(), savedAsset.getAssetId());
        */
    }

    @Test
    public void testUpdateAsset() {
        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        /*
        Asset updatedAsset = assetService.updateAsset(asset);

        assertNotNull(updatedAsset);
        assertEquals(asset.getAssetId(), updatedAsset.getAssetId());
        */
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
        when(assetRepository.findAssetByCategory(category)).thenReturn(Arrays.asList(asset));

        List<Asset> assets = assetService.getAssetsByCategory(category);

        assertNotNull(assets);
        assertFalse(assets.isEmpty());
    }

    @Test
    public void testDeleteAsset() {
        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));

        assetService.deleteAsset(asset.getAssetId());
    }
}