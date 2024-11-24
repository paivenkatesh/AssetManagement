package com.hexaware.ams.service;
import java.time.LocalDateTime;
/*
@Author: Arghya Mandal
Date: 9-11-2024
*/
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.dto.AssetAuditDto;
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
    Logger logger = LoggerFactory.getLogger(AssetAuditServiceImp.class);
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
        logger.info("Asset audit request sent.");
        return auditRepository.save(audit);
    }

    @Override
    public AssetAudit updateAuditStatus(int auditId, AssetAuditDto.AuditStatus auditStatus) {
        // getting audit record
    	AssetAudit audit = auditRepository.findById(auditId)
                .orElseThrow(() -> new ResourceNotFoundException("Audit not found with id: " + auditId));
    	// updating audit status
    	audit.setAuditStatus(AssetAudit.AuditStatus.valueOf(auditStatus.name()));
        audit.setUpdatedAt(LocalDateTime.now());
        logger.info("Audit request updated at: "+audit.getUpdatedAt());
        return auditRepository.save(audit);
    }

    @Override
    public List<AssetAudit> getAuditsByEmployee(int employeeId) {
        // getting employee
    	Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        logger.info("Audits of employee with id: " + employeeId + " returned.");
    	return auditRepository.findByEmployee(employee);
    }

    @Override
    public List<AssetAudit> getAllAudits() {
        logger.info("All audits returned.");
    	return auditRepository.findAll();
    }

    @Override
    public AssetAudit getAuditById(int auditId) {
        logger.info("Returning audit request with id: " + auditId);
    	return auditRepository.findById(auditId)
                .orElseThrow(() -> new ResourceNotFoundException("Audit not found with id: " + auditId));
    }
}