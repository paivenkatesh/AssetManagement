package com.hexaware.ams.service;
//Author: Arghya Mandal
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetAudit;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IAssetAuditRepository;
import com.hexaware.ams.repository.IAssetRepository;
import com.hexaware.ams.repository.IEmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AssetAuditServiceImp implements IAssetAuditService {

    @Autowired
    private IAssetAuditRepository auditRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IAssetRepository assetRepository;

    @Override
    public AssetAudit sendAuditRequest(int employeeId, int assetId) {
        // getting employee
    	Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        // getting asset
    	Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    	// creating new audit record
        AssetAudit audit = new AssetAudit();
        audit.setEmployee(employee);
        audit.setAsset(asset);
        audit.setAuditStatus(AssetAudit.AuditStatus.Pending);

        return auditRepository.save(audit);
    }

    @Override
    public AssetAudit updateAuditStatus(int auditId, AssetAudit.AuditStatus auditStatus) {
        // getting audit record
    	AssetAudit audit = auditRepository.findById(auditId)
                .orElseThrow(() -> new ResourceNotFoundException("Audit not found with id: " + auditId));
    	// updating audit status
        audit.setAuditStatus(auditStatus);
        return auditRepository.save(audit);
    }

    @Override
    public List<AssetAudit> getAuditsByEmployee(int employeeId) {
        // getting employee
    	Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return auditRepository.findByEmployee(employee);
    }

    @Override
    public List<AssetAudit> getAllAudits() {
        return auditRepository.findAll();
    }

    @Override
    public AssetAudit getAuditById(int auditId) {
        return auditRepository.findById(auditId)
                .orElseThrow(() -> new ResourceNotFoundException("Audit not found with id: " + auditId));
    }
}