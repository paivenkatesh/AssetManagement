package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.AssetAudit;

public class AssetAuditServiceImp implements IAssetAuditService {

	@Override
	public int createAuditRequest(int assetId, int employeeId) {
		return 0;

	}

	@Override
	public int updateAuditStatus(int auditId, String status) {
		return 0;

	}

	@Override
	public List<AssetAudit> getAuditRequestsByEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetAudit> getAllAuditRequests() {
		// TODO Auto-generated method stub
		return null;
	}

}
