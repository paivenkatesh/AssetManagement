package com.hexaware.ams.service;
/*
@Author: Arghya Mandal
Date: 9-11-2024
*/
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetBorrowing;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.exception.BadRequestException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IAssetBorrowingRepository;
import com.hexaware.ams.repository.IAssetRepository;
import com.hexaware.ams.repository.IEmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional 
public class AssetBorrowingServiceImp implements IAssetBorrowingService {

    @Autowired
    private IAssetBorrowingRepository borrowingRepository;

    @Autowired
    private IAssetRepository assetRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(AssetBorrowingServiceImp.class);
    @Override
    public AssetBorrowing borrowAsset(int employeeId, int assetId) {
        // Getting employee
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        // Getting asset
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found with ID: " + assetId));

        // Checking if asset is available
        if (Asset.Status.Borrowed.equals(asset.getStatus())) {
            throw new BadRequestException("Asset is already borrowed");
        }

        // Creating new borrowing record
        AssetBorrowing borrowing = new AssetBorrowing();
        borrowing.setEmployee(employee);
        borrowing.setAsset(asset);
        borrowing.setBorrowedAt(LocalDateTime.now());
        borrowing.setStatus(AssetBorrowing.Status.Active);

        // Updating asset status
        asset.setStatus(Asset.Status.Borrowed);
        assetRepository.save(asset);
        logger.info(asset.getAssetName() + " with id: " + asset.getAssetId() +" is borrowed by employee with id: " + employee.getEmployeeId());
        return borrowingRepository.save(borrowing);
    }

    @Override
    public AssetBorrowing returnAsset(int borrowingId) {
        // Getting borrowing record
        AssetBorrowing borrowing = borrowingRepository.findById(borrowingId)
            .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found with ID: " + borrowingId));

        // Checking if already returned
        if (AssetBorrowing.Status.Returned.equals(borrowing.getStatus())) {
            throw new BadRequestException("Asset has already been returned");
        }

        // Updating borrowing record
        borrowing.setReturnedAt(LocalDateTime.now());
        borrowing.setStatus(AssetBorrowing.Status.Returned);
        logger.info("Asset is returned.");
        // Updating asset status
        Asset asset = borrowing.getAsset();
        asset.setStatus(Asset.Status.Available);
        assetRepository.save(asset);
        logger.info("Asset with id: " + asset.getAssetId() + " is available to borrow.");
        return borrowingRepository.save(borrowing);
    }

    @Override
    public List<AssetBorrowing> getBorrowingsByEmployee(int employeeId) {
        // Checking if employee exists
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        // Getting borrowings for employee
        List<AssetBorrowing> borrowings = borrowingRepository.findByEmployee(employee);
        if (borrowings.isEmpty()) {
            throw new ResourceNotFoundException("No borrowing records found for employee with ID: " + employeeId);
        }
        logger.info("Returning borrowings of employee with id: " + employeeId);
        return borrowings;
    }

    @Override
    public List<AssetBorrowing> getAllActiveBorrowings() {
        // Getting all active borrowings
        List<AssetBorrowing> activeBorrowings = borrowingRepository.findByStatus(AssetBorrowing.Status.Active);
        if (activeBorrowings.isEmpty()) {
            throw new ResourceNotFoundException("No active borrowings found");
        }
        logger.info("Returning all active borrowings.");
        return activeBorrowings;
    }
}
