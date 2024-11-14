package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.AssetAudit;
import com.hexaware.ams.repository.IAssetAuditRepository;
@Service
public class AssetAuditServiceImp implements IAssetAuditService {
	@Autowired
	IAssetAuditRepository assetAuditRepository;
	@Override
	public List<AssetAudit> getAllAudits() {
		return assetAuditRepository.findAll();
	}

	@Override
	public List<AssetAudit> getAuditsById(int assetAuditId) {
		return (List<AssetAudit>) assetAuditRepository.findById(assetAuditId).orElse(null);
	}

	@Override
	public List<AssetAudit> getAuditsByEmployeeId(int employeeId) {
		return assetAuditRepository.findByEmployeeId(employeeId);
	}

	@Override
	public AssetAudit addAssetAudit(AssetAudit assetAudit) {
		return assetAuditRepository.save(assetAudit);
	}

	@Override
	public AssetAudit updateAssetAuditStatus(AssetAudit assetAudit) {
		return assetAuditRepository.save(assetAudit);
	}

}
