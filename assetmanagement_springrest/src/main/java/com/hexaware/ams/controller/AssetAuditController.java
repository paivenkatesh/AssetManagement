package com.hexaware.ams.controller;
//Author: Arghya Mandal
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.entity.AssetAudit;
import com.hexaware.ams.service.IAssetAuditService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/audits")
public class AssetAuditController {

    @Autowired
    private IAssetAuditService assetAuditService;

    // Create a new audit request
    @PostMapping("/send/{employeeId}/{assetId}")
    public ResponseEntity<AssetAudit> sendAuditRequest(@PathVariable int employeeId, @PathVariable int assetId, @Valid @RequestBody AssetAudit assetAudit) {
        AssetAudit audit = assetAuditService.sendAuditRequest(employeeId, assetId);
        return ResponseEntity.ok(audit);
    }

    // Update audit status
    @PutMapping("/update/{auditId}/{auditStatus}")
    public ResponseEntity<AssetAudit> updateAuditStatus(@PathVariable int auditId, @PathVariable AssetAudit.AuditStatus auditStatus) {
        AssetAudit updatedAudit = assetAuditService.updateAuditStatus(auditId, auditStatus);
        return ResponseEntity.ok(updatedAudit);
    }

    // Get audits by employee
    @GetMapping("/getbyeid/{employeeId}")
    public ResponseEntity<List<AssetAudit>> getAuditsByEmployee(@PathVariable int employeeId) {
        List<AssetAudit> audits = assetAuditService.getAuditsByEmployee(employeeId);
        return ResponseEntity.ok(audits);
    }

    // Get all audits
    @GetMapping("/getall")
    public ResponseEntity<List<AssetAudit>> getAllAudits() {
        List<AssetAudit> audits = assetAuditService.getAllAudits();
        return ResponseEntity.ok(audits);
    }

    // Get audit by ID
    @GetMapping("/getbyid/{auditId}")
    public ResponseEntity<AssetAudit> getAuditById(@PathVariable int auditId) {
        AssetAudit audit = assetAuditService.getAuditById(auditId);
        return ResponseEntity.ok(audit);
    }
}
