package com.hexaware.ams.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ams.dto.AssetCategoryDto;
import com.hexaware.ams.dto.AssetDto;
import com.hexaware.ams.dto.EmployeeDto;
import com.hexaware.ams.dto.EmployeeDto.GenderDTO;
import com.hexaware.ams.dto.IssueTypeDto;
import com.hexaware.ams.dto.RoleDto;
import com.hexaware.ams.dto.ServiceRequestDto;
import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.entity.ServiceRequest.Status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceRequestServiceImpTest {

	@Autowired
	IServiceRequestService serviceRequestService;
	
	@Test
	@Order(1)
	@Disabled
	void testCreateServiceRequest() {
		
		ServiceRequest s1 = serviceRequestService.createServiceRequest(1, 1, 2, "Test Request for checking");
		
		assertEquals(1, s1.getServiceRequestId());
	}

	
	@Test
	@Order(2)
	void testGetServiceRequestById() {
		
		ServiceRequest s1 = serviceRequestService.getServiceRequestById(1);
		
		assertEquals(1, s1.getServiceRequestId());
		
	}

	@Test
	@Order(3)
	void testUpdateServiceRequestStatus() {
		
		ServiceRequest s2 = serviceRequestService.updateServiceRequestStatus(4, ServiceRequest.Status.Completed);
		
		
		assertEquals(ServiceRequest.Status.Completed, s2.getStatus());
		
		
		
	}

	@Test
	@Order(4)
	void testGetServiceRequestsByEmployee() {
		
		List<ServiceRequest> s4 = serviceRequestService.getServiceRequestsByEmployee(22);
		
		assertNotNull(s4);
		
	}

	@Test
	@Order(5)
	void testGetAllServiceRequests() {
		
		List<ServiceRequest> s5 = serviceRequestService.getAllServiceRequests();
		
		assertNotNull(s5);
	}

	@Test
	@Order(6)
	@Disabled
	void testFindByStatus() {
		
		List<ServiceRequest> s6 = serviceRequestService.findByStatus(Status.Completed);
		
		assertNotNull(s6);
		
	}

}
