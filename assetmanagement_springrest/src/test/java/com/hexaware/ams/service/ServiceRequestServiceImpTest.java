package com.hexaware.ams.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetCategory;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.Employee.Gender;
import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.entity.Role;
import com.hexaware.ams.entity.ServiceRequest;

@SpringBootTest
class ServiceRequestServiceImpTest {

	@Autowired
	IServiceRequestService serviceRequestService;
	
	@Test
	@Disabled
	void testCreateServiceRequest() {
		
		Role r1 = new Role(2, "User");
		
		Employee e1 = new Employee(5,"Harvey", Gender.Male, "7242662675", "12 Mac Street", "harvey@example.com", "Harvey205", r1);
		
		AssetCategory ac1 = new AssetCategory(1, "Software");
		
		Asset a1 = new Asset(1, "Laptop", ac1, "Dell", LocalDate.of(2010, 12, 12), LocalDate.of(2030, 12, 12), 1000, Asset.Status.Available  );
		
		IssueType i1 = new IssueType(1, "Software Issue");
		
		ServiceRequest s1 = new ServiceRequest(1, e1, a1, "Borrow Request",i1, ServiceRequest.Status.Pending, Date.valueOf("2024-11-18"));
		
		ServiceRequest s2 = serviceRequestService.createServiceRequest(s1);
		
		assertEquals(1, s2.getServiceRequestId());
	}

	@Test
	void testGetServiceRequestById() {
		
	}

	@Test
	void testUpdateServiceRequestStatus() {
		
	}

	@Test
	void testGetServiceRequestsByEmployee() {
		
	}

	@Test
	void testGetAllServiceRequests() {
		
	}

	@Test
	void testFindByStatus() {
		
	}

}
