package com.hexaware.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.AssetAudit;

import java.util.List;
@Repository
public interface IAssetAuditRepository extends JpaRepository<AssetAudit, Integer> {
    List<AssetAudit> findByEmployeeId(Integer employeeId);
    List<AssetAudit> findByAuditStatus(String auditStatus);
}