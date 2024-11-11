package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.AssetAudit;

public interface IAssetAuditService {

	List<AssetAudit> getAllAudits();
	List<AssetAudit> getAuditsById(int assetAuditId);
	List<AssetAudit> getAuditsByEmployee(int employeeId);
	int addAssetAudit(AssetAudit assetAudit);
	int updateAssetAuditStatus(AssetAudit assetAudit);
}
