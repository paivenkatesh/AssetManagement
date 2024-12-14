package com.hexaware.ams.repository;
/*
 * @Author: Arghya Mandal
 * @Date: 08-11-2024
 * @Description: Repository interface for managing AssetBorrowing entities. It extends JpaRepository to provide CRUD operations and includes custom query methods to find AssetBorrowing records by Employee and by Status.
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.AssetBorrowing;
import com.hexaware.ams.entity.AssetBorrowing.Status;
import com.hexaware.ams.entity.Employee;

@Repository
public interface IAssetBorrowingRepository extends JpaRepository<AssetBorrowing, Integer>{
	List<AssetBorrowing> findByEmployee(Employee employee);
	List<AssetBorrowing> findByStatus(Status status);
	@Query("SELECT ab FROM AssetBorrowing ab JOIN ab.asset a WHERE ab.employee.employeeId = :employeeId AND a.status = 'Borrowed'")
    List<AssetBorrowing> findByEmployeeIdAndAssetStatus(int employeeId);
}
