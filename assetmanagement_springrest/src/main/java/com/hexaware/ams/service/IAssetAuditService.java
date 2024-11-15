package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.AssetAudit;

public interface IAssetAuditService {
	public List<AssetAudit> getAllAudits();
	public List<AssetAudit> getAuditsById(int assetAuditId);
	public List<AssetAudit> getAuditsByEmployeeId(int employeeId);
	public AssetAudit addAssetAudit(AssetAudit assetAudit);
	public AssetAudit updateAssetAuditStatus(AssetAudit assetAudit);
}
