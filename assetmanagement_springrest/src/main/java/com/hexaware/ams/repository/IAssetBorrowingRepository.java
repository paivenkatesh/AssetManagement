package com.hexaware.ams.repository;
/*
@Author: Arghya Mandal
Date: 8-11-2024
*/
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.AssetBorrowing;
import com.hexaware.ams.entity.AssetBorrowing.Status;
import com.hexaware.ams.entity.Employee;

@Repository
public interface IAssetBorrowingRepository extends JpaRepository<AssetBorrowing, Integer>{
	List<AssetBorrowing> findByEmployee(Employee employee);
	List<AssetBorrowing> findByStatus(Status status);
}
