package com.hexaware.ams.repository;
//Author: Arghya Mandal
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.AssetAudit;
import com.hexaware.ams.entity.AssetAudit.AuditStatus;
import com.hexaware.ams.entity.Employee;
@Repository
public interface IAssetAuditRepository extends JpaRepository<AssetAudit, Integer> {
	List<AssetAudit> findByEmployee(Employee employee);
	List<AssetAudit> findByAuditStatus(AuditStatus auditStatus);
}