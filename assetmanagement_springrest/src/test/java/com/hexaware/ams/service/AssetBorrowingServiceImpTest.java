package com.hexaware.ams.service;
/*
Author: Arghya Mandal
Date: 20-11-2024
*/
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.Asset.Status;
import com.hexaware.ams.entity.AssetBorrowing;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.repository.IAssetBorrowingRepository;
import com.hexaware.ams.repository.IAssetRepository;
import com.hexaware.ams.repository.IEmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AssetBorrowingServiceImpTest {

    @Mock
    private IAssetBorrowingRepository borrowingRepository;

    @Mock
    private IAssetRepository assetRepository;

    @Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private AssetBorrowingServiceImp borrowingService;

    private Employee employee;
    private Asset asset;
    private AssetBorrowing borrowing;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Arghya Mandal");

        asset = new Asset();
        asset.setAssetId(1);
        asset.setAssetName("Laptop");
        asset.setStatus(Status.Available);

        borrowing = new AssetBorrowing();
        borrowing.setBorrowingId(1);
        borrowing.setEmployee(employee);
        borrowing.setAsset(asset);
        borrowing.setStatus(AssetBorrowing.Status.Active);
        borrowing.setBorrowedAt(LocalDateTime.now());
    }

    @Test
    public void testBorrowAsset() {
        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));
        when(borrowingRepository.save(any(AssetBorrowing.class))).thenReturn(borrowing);

        AssetBorrowing result = borrowingService.borrowAsset(employee.getEmployeeId(), asset.getAssetId());

        assertNotNull(result);
        assertEquals(AssetBorrowing.Status.Active, result.getStatus());
    }

    @Test
    public void testReturnAsset() {
        when(borrowingRepository.findById(borrowing.getBorrowingId())).thenReturn(Optional.of(borrowing));
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);
        when(borrowingRepository.save(any(AssetBorrowing.class))).thenReturn(borrowing);

        AssetBorrowing result = borrowingService.returnAsset(borrowing.getBorrowingId());

        assertNotNull(result);
        assertEquals(AssetBorrowing.Status.Returned, result.getStatus());
    }

    @Test
    public void testGetBorrowingsByEmployee() {
        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
        when(borrowingRepository.findByEmployee(employee)).thenReturn(Arrays.asList(borrowing));

        List<AssetBorrowing> borrowings = borrowingService.getBorrowingsByEmployee(employee.getEmployeeId());

        assertNotNull(borrowings);
        assertFalse(borrowings.isEmpty());
    }

    @Test
    public void testGetAllActiveBorrowings() {
        when(borrowingRepository.findByStatus(AssetBorrowing.Status.Active)).thenReturn(Arrays.asList(borrowing));

        List<AssetBorrowing> activeBorrowings = borrowingService.getAllActiveBorrowings();

        assertNotNull(activeBorrowings);
        assertFalse(activeBorrowings.isEmpty());
    }
}