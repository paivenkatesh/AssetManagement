package com.hexaware.ams.service;

/*
Author: Arghya Mandal
Date: 20-11-2024
*/
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetAudit;
import com.hexaware.ams.entity.AssetAudit.AuditStatus;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.repository.IAssetAuditRepository;
import com.hexaware.ams.repository.IAssetRepository;
import com.hexaware.ams.repository.IEmployeeRepository;

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
public class AssetAuditServiceImpTest {

    @Mock
    private IAssetAuditRepository auditRepository;

    @Mock
    private IEmployeeRepository employeeRepository;

    @Mock
    private IAssetRepository assetRepository;

    @InjectMocks
    private AssetAuditServiceImp auditService;

    private Employee employee;
    private Asset asset;
    private AssetAudit audit;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Arghya Mandal");

        asset = new Asset();
        asset.setAssetId(1);
        asset.setAssetName("Laptop");

        audit = new AssetAudit();
        audit.setAuditId(1);
        audit.setEmployee(employee);
        audit.setAsset(asset);
        audit.setAuditStatus(AuditStatus.Pending);
    }

    @Test
    public void testSendAuditRequest() {
        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));
        when(auditRepository.save(any(AssetAudit.class))).thenReturn(audit);

        AssetAudit result = auditService.sendAuditRequest(employee.getEmployeeId(), asset.getAssetId());

        assertNotNull(result);
        assertEquals(AuditStatus.Pending, result.getAuditStatus());
    }

    @Test
    public void testUpdateAuditStatus() {
    	/*
        when(auditRepository.findById(audit.getAuditId())).thenReturn(Optional.of(audit));
        when(auditRepository.save(any(AssetAudit.class))).thenReturn(audit);

        AssetAudit result = auditService.updateAuditStatus(audit.getAuditId(), AuditStatus.Verified);

        assertNotNull(result);
        assertEquals(AuditStatus.Verified, result.getAuditStatus());
        */
    }

    @Test
    public void testGetAuditsByEmployee() {
        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
        when(auditRepository.findByEmployee(employee)).thenReturn(Arrays.asList(audit));

        List<AssetAudit> audits = auditService.getAuditsByEmployee(employee.getEmployeeId());

        assertNotNull(audits);
        assertFalse(audits.isEmpty());
    }

    @Test
    public void testGetAllAudits() {
        when(auditRepository.findAll()).thenReturn(Arrays.asList(audit));

        List<AssetAudit> audits = auditService.getAllAudits();

        assertNotNull(audits);
        assertFalse(audits.isEmpty());
    }

    @Test
    public void testGetAuditById() {
        when(auditRepository.findById(audit.getAuditId())).thenReturn(Optional.of(audit));

        AssetAudit result = auditService.getAuditById(audit.getAuditId());

        assertNotNull(result);
        assertEquals(audit.getAuditId(), result.getAuditId());
    }
}