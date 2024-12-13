package com.hexaware.ams.controller;
/*
 * @Author: Arghya Mandal
 * @Date: 16-11-2024
 * @Description: Controller for managing asset operations including adding, updating, retrieving, and deleting assets.
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.dto.AssetDto;
import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetBorrowing;
import com.hexaware.ams.entity.AssetCategory;
import com.hexaware.ams.service.IAssetBorrowingService;
import com.hexaware.ams.service.IAssetService;

import jakarta.validation.Valid;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private IAssetService assetService;
    @Autowired
    private IAssetBorrowingService assetBorrowingService;

    // Add a new asset
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Asset> addAsset(@Valid @RequestBody AssetDto assetDto) {
        Asset newAsset = assetService.addAsset(assetDto);
        return new ResponseEntity<>(newAsset, HttpStatus.CREATED);
    }

    // Update an existing asset
    @PutMapping("/update/{assetId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Asset> updateAsset(@PathVariable Integer assetId, @Valid @RequestBody AssetDto assetDto) {
        assetDto.setAssetId(assetId);
        Asset updatedAsset = assetService.updateAsset(assetDto);
        return ResponseEntity.ok(updatedAsset);
    }

    // Get asset by ID
    @GetMapping("/getbyid/{assetId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Asset> getAssetById(@PathVariable Integer assetId) {
        Asset asset = assetService.getAssetById(assetId);
        return ResponseEntity.ok(asset);
    }

    // Get all assets
    @GetMapping("/getall")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    // Get assets by category
    @GetMapping("/category")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Asset>> getAssetsByCategory(@RequestBody AssetCategory categoryName) {
        List<Asset> assets = assetService.getAssetsByCategory(categoryName);
        return ResponseEntity.ok(assets);
    }

    // Delete an asset
    @DeleteMapping("/delete/{assetId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAsset(@PathVariable Integer assetId) {
        assetService.deleteAsset(assetId);
        return ResponseEntity.noContent().build();
    }
    // Get assets assigned to an employee
    @GetMapping("/assigned/{employeeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AssetBorrowing>> getAssignedAssets(@PathVariable Integer employeeId) {
        List<AssetBorrowing> assignedAssets = assetBorrowingService.getBorrowingsByEmployee(employeeId);
        return ResponseEntity.ok(assignedAssets);
    }
}
