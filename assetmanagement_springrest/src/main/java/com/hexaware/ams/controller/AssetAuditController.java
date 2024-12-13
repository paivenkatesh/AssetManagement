package com.hexaware.ams.controller;
/*
 * @Author: Arghya Mandal
 * Date: 16-11-2024
 * Description: Controller for handling asset audit requests. It includes endpoints to create, update, 
 * and retrieve asset audits by employee, audit ID, and all audits.
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.dto.AssetAuditDto;
import com.hexaware.ams.entity.AssetAudit;
import com.hexaware.ams.service.IAssetAuditService;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/audits")
public class AssetAuditController {

    @Autowired
    private IAssetAuditService assetAuditService;

    // Create a new audit request
    @PostMapping("/send/{employeeId}/{assetId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssetAudit> sendAuditRequest(@PathVariable int employeeId, @PathVariable int assetId) {
        AssetAudit audit = assetAuditService.sendAuditRequest(employeeId, assetId);
        return new ResponseEntity<>(audit, HttpStatus.CREATED);
    }

    // Update audit status
    @PutMapping("/update/{auditId}/{auditStatus}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssetAudit> updateAuditStatus(@PathVariable int auditId, @PathVariable AssetAuditDto.AuditStatus auditStatus) {
        AssetAudit updatedAudit = assetAuditService.updateAuditStatus(auditId, auditStatus);
        return ResponseEntity.ok(updatedAudit);
    }

    // Get audits by employee
    @GetMapping("/getbyeid/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AssetAudit>> getAuditsByEmployee(@PathVariable int employeeId) {
        List<AssetAudit> audits = assetAuditService.getAuditsByEmployee(employeeId);
        return ResponseEntity.ok(audits);
    }

    // Get all audits
    @GetMapping("/getall")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AssetAudit>> getAllAudits() {
        List<AssetAudit> audits = assetAuditService.getAllAudits();
        return ResponseEntity.ok(audits);
    }

    // Get audit by ID
    @GetMapping("/getbyid/{auditId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssetAudit> getAuditById(@PathVariable int auditId) {
        AssetAudit audit = assetAuditService.getAuditById(auditId);
        return ResponseEntity.ok(audit);
    }
}
