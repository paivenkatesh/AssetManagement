package com.hexaware.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.ServiceRequest;

@Repository
public interface IServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {

	List<ServiceRequest> findByEmployeeEmployeeId(int employeeId);
	
	List<ServiceRequest> findByStatus(String status);
}
