package com.hexaware.ams.service;
//Author: Arghya Mandal
import java.util.List;

import com.hexaware.ams.entity.AssetAudit;

public interface IAssetAuditService {
	public AssetAudit sendAuditRequest(int employeeId, int assetId);
	public AssetAudit updateAuditStatus(int auditId, AssetAudit.AuditStatus auditStatus);
	public List<AssetAudit> getAuditsByEmployee(int employeeId);
	public List<AssetAudit> getAllAudits();
	public AssetAudit getAuditById(int auditId);
}
