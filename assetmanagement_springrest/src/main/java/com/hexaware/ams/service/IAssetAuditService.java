package com.hexaware.ams.service;
/*
 * @Author: Arghya Mandal
 * @Date: 09-11-2024
 * @Description: Interface for managing AssetAudit operations. This interface defines methods for sending audit requests, updating audit statuses, retrieving audits by employee, fetching all audits, and getting audit details by ID. It serves as a contract for the service implementation handling asset auditing functionalities.
 */
import java.util.List;

import com.hexaware.ams.dto.AssetAuditDto;
import com.hexaware.ams.entity.AssetAudit;

public interface IAssetAuditService {
	public AssetAudit sendAuditRequest(int employeeId, int assetId);
	public AssetAudit updateAuditStatus(int auditId, AssetAuditDto.AuditStatus auditStatus);
	public List<AssetAudit> getAuditsByEmployee(int employeeId);
	public List<AssetAudit> getAllAudits();
	public AssetAudit getAuditById(int auditId);
}
