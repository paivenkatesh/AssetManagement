package com.hexaware.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.entity.ServiceRequest.Status;

@Repository
public interface IServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {

	List<ServiceRequest> findByEmployeeEmployeeId(int employeeId);
	
	
	@Query("select s from ServiceRequest s where s.status =: status")
	List<ServiceRequest> findByStatus(String status);
	
	
	List<ServiceRequest> findByEmployeeAndStatus(Employee employee, Status status);
	
}
