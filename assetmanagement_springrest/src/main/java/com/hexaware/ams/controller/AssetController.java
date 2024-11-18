package com.hexaware.ams.controller;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetCategory;
import com.hexaware.ams.service.IAssetService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private IAssetService assetService;

    // Add a new asset
    @PostMapping("/add")
    public ResponseEntity<Asset> addAsset(@Valid @RequestBody Asset asset) {
        Asset newAsset = assetService.addAsset(asset);
        return ResponseEntity.ok(newAsset);
    }

    // Update an existing asset
    @PutMapping("/update/{assetId}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Integer assetId, @Valid @RequestBody Asset asset) {
        asset.setAssetId(assetId); // Ensure the assetId is set for the update
        Asset updatedAsset = assetService.updateAsset(asset);
        return ResponseEntity.ok(updatedAsset);
    }

    // Get asset by ID
    @GetMapping("/{assetId}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Integer assetId) {
        Asset asset = assetService.getAssetById(assetId);
        return ResponseEntity.ok(asset);
    }

    // Get all assets
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    // Get assets by category
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Asset>> getAssetsByCategory(@PathVariable AssetCategory categoryName) {
        List<Asset> assets = assetService.getAssetsByCategory(categoryName);
        return ResponseEntity.ok(assets);
    }

    // Delete an asset
    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Integer assetId) {
        assetService.deleteAsset(assetId);
        return ResponseEntity.noContent().build();
    }
}
