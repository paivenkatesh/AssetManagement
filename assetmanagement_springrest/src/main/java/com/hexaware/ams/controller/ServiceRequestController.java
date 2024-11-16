package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.service.IServiceRequestService;

@RestController
@RequestMapping("/ams/servicerequest")
public class ServiceRequestController {
	
	@Autowired
	IServiceRequestService serviceRequestService;
	
	@PostMapping("/createServiceRequest")
	ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
		
		return serviceRequestService.createServiceRequest(serviceRequest);
	}
	
	@GetMapping("/getServiceRequestById/{serviceRequestId}")
	ServiceRequest getServiceRequestById(int serviceRequestId) {
		
		return serviceRequestService.getServiceRequestById(serviceRequestId);
	}
	
	@PutMapping("/updateServiceRequest/{serviceRequestId}")
	ServiceRequest updateServiceRequest(int serviceRequestId, ServiceRequest.Status status) {
		
		return serviceRequestService.updateServiceRequestStatus(serviceRequestId, status);
	}
	
	@GetMapping("/serviceRequestByEmployee/{employeeId}")
	List<ServiceRequest> getServiceRequestsByEmployee(int employeeId){
		
		return serviceRequestService.getServiceRequestsByEmployee(employeeId);
	}
	
	@GetMapping("/allServiceRequests")
	List<ServiceRequest> getAllServiceRequests(){
		
		return serviceRequestService.getAllServiceRequests();
	}
	
	@GetMapping("/findByStatus/{status}")
	List<ServiceRequest> findByStatus(String status){
		
		return serviceRequestService.findByStatus(status);
	}

}
