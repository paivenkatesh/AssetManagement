package com.hexaware.ams.repository;
/*
 * @Author: Arghya Mandal
 * @Date: 08-11-2024
 * @Description: Repository interface for managing AssetAudit entities. It extends JpaRepository to provide CRUD operations and includes custom query methods to find AssetAudit records by Employee and by AuditStatus.
 */
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