package com.hexaware.ams.controller;
/*
 * @Author: Arghya Mandal
 * Date: 16-11-2024
 * Description: Controller for managing asset borrowings, including endpoints for borrowing, returning, 
 * and retrieving active or employee-specific borrowings.
 */
import com.hexaware.ams.entity.AssetBorrowing;
import com.hexaware.ams.service.IAssetBorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/borrowings")
public class AssetBorrowingController {

    @Autowired
    private IAssetBorrowingService assetBorrowingService;

    // Borrow an asset
    @PostMapping("/borrow/{employeeId}/{assetId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AssetBorrowing> borrowAsset(@PathVariable int employeeId, @PathVariable int assetId) {
        AssetBorrowing borrowing = assetBorrowingService.borrowAsset(employeeId, assetId);
        return ResponseEntity.ok(borrowing);
    }

    // Return an asset
    @PutMapping("/return/{borrowingId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AssetBorrowing> returnAsset(@PathVariable int borrowingId) {
        AssetBorrowing borrowing = assetBorrowingService.returnAsset(borrowingId);
        return ResponseEntity.ok(borrowing);
    }

    // Get borrowings by employee
    @GetMapping("/getbyeid/{employeeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AssetBorrowing>> getBorrowingsByEmployee(@PathVariable int employeeId) {
        List<AssetBorrowing> borrowings = assetBorrowingService.getBorrowingsByEmployee(employeeId);
        return ResponseEntity.ok(borrowings);
    }

    // Get all active borrowings
    @GetMapping("/active")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AssetBorrowing>> getAllActiveBorrowings() {
        List<AssetBorrowing> activeBorrowings = assetBorrowingService.getAllActiveBorrowings();
        return ResponseEntity.ok(activeBorrowings);
    }
}
