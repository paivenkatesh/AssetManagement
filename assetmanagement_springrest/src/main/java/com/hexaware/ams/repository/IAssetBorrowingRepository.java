package com.hexaware.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.AssetBorrowing;

@Repository
public interface IAssetBorrowingRepository extends JpaRepository<AssetBorrowing, Integer>{
	List<AssetBorrowing> findByEmployeeId(Integer employeeId);
	List<AssetBorrowing> findByAssetId(Integer assetId);
	List<AssetBorrowing> findByStatus(String status);
}
