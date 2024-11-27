package com.hexaware.ams.repository;

/*
 * Author: Venkatesh Pai
 * Date: 14-11-24
 * Description: Service Request Repository Interface
 */
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
	
	
	List<ServiceRequest> findByStatus(Status status);
	
	
	List<ServiceRequest> findByEmployeeAndStatus(Employee employee, Status status);
	
}
