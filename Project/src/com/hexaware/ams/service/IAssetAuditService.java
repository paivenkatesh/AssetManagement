package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.AssetAudit;

public interface IAssetAuditService {
	void createAuditRequest(int assetId, int employeeId);
    void updateAuditStatus(int auditId, String status);
    List<AssetAudit> getAuditRequestsByEmployee(int employeeId);
    List<AssetAudit> getAllAuditRequests();
}
