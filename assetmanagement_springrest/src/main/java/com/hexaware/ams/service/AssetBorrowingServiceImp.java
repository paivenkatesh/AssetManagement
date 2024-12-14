package com.hexaware.ams.service;
/*
 * @Author: Arghya Mandal
 * @Date: 09-11-2024
 * @Description: Service implementation for managing AssetBorrowing entities. This class provides methods for borrowing assets, returning assets, retrieving borrowings by employee, and fetching all active borrowings. It ensures proper validation and exception handling during asset borrowing operations and uses repositories for database interactions.
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
        logger.info("Attempting to return asset for borrowing ID: {}", borrowingId);
        
        // Getting borrowing record
        AssetBorrowing borrowing = borrowingRepository.findById(borrowingId)
            .orElseThrow(() -> {
                logger.error("Borrowing record not found with ID: {}", borrowingId);
                return new ResourceNotFoundException("Borrowing record not found with ID: " + borrowingId);
            });
            
        logger.info("Found borrowing record. Current status: {}", borrowing.getStatus());

        // Checking if already returned
        if (AssetBorrowing.Status.Returned.equals(borrowing.getStatus())) {
            logger.warn("Asset has already been returned for borrowing ID: {}", borrowingId);
            throw new BadRequestException("Asset has already been returned");
        }

        // Updating borrowing record
        borrowing.setReturnedAt(LocalDateTime.now());
        borrowing.setStatus(AssetBorrowing.Status.Returned);
        
        // Updating asset status
        Asset asset = borrowing.getAsset();
        logger.info("Updating asset status for asset ID: {}. Current status: {}", 
            asset.getAssetId(), asset.getStatus());
            
        asset.setStatus(Asset.Status.Available);
        assetRepository.save(asset);
        
        logger.info("Successfully updated asset status to Available");
        
        AssetBorrowing savedBorrowing = borrowingRepository.save(borrowing);
        logger.info("Successfully updated borrowing record to Returned");
        
        return savedBorrowing;
    }

    @Override
    public List<AssetBorrowing> getBorrowingsByEmployee(int employeeId) {
        // Checking if employee exists
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        // Getting borrowings for employee
        List<AssetBorrowing> borrowings = borrowingRepository.findByEmployeeIdAndAssetStatus(employeeId);
        if (borrowings.isEmpty()) {
            throw new ResourceNotFoundException("No borrowings found for employee ID: " + employeeId);
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
