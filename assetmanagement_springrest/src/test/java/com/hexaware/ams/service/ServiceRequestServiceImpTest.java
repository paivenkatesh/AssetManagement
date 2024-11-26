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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceRequestServiceImpTest {

	@Autowired
	IServiceRequestService serviceRequestService;
	
	@Test
	@Order(1)
	@Disabled
	void testCreateServiceRequest() {
		
		RoleDto r1 = new RoleDto(1, "Admin");
		
		EmployeeDto e1 = new EmployeeDto(14,"Harvey", GenderDTO.Male, "9780606088", "12 Manhattan NY", "harvey@example.com", "Harvey214", r1);
		
		AssetCategoryDto ac1 = new AssetCategoryDto(1, "Laptop");
		
		AssetDto a1 = new AssetDto(1, "Laptop", ac1, "Dell", LocalDate.of(2020, 11, 24), LocalDate.of(2030, 11, 24), 50000, AssetDto.Status.Available);
		
		IssueTypeDto i1 = new IssueTypeDto(2, "Software Issue");
		
		ServiceRequestDto s1 = new ServiceRequestDto(10, e1, a1, "Borrow Request",i1, ServiceRequestDto.StatusDTO.Pending, Date.valueOf("2024-11-18"));
		
		
		ServiceRequest s2 = serviceRequestService.createServiceRequest(s1);
		
		assertNotNull(s2);
		
		assertEquals(1, s2.getServiceRequestId());
		
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
		
		List<ServiceRequest> s6 = serviceRequestService.findByStatus("Completed");
		
		assertNotNull(s6);
		
	}

}
