package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ServiceRequest> createServiceRequest(ServiceRequest serviceRequest) {
		
		ServiceRequest newServiceRequest = serviceRequestService.createServiceRequest(serviceRequest);
		
		return new ResponseEntity<>(newServiceRequest, HttpStatus.CREATED);
	}
	
	@GetMapping("/getServiceRequestById/{serviceRequestId}")
	public ResponseEntity<ServiceRequest> getServiceRequestById(int serviceRequestId) {
		
		ServiceRequest serviceRequest = serviceRequestService.getServiceRequestById(serviceRequestId);
		
		return ResponseEntity.ok(serviceRequest);
	}
	
	@PutMapping("/updateServiceRequest/{serviceRequestId}")
	public ResponseEntity<ServiceRequest> updateServiceRequest(int serviceRequestId, ServiceRequest.Status status) {
		
		ServiceRequest updatedServiceRequest = serviceRequestService.updateServiceRequestStatus(serviceRequestId, status);
		
		return ResponseEntity.ok(updatedServiceRequest);
	}
	
	@GetMapping("/serviceRequestByEmployee/{employeeId}")
	public ResponseEntity<List<ServiceRequest>> getServiceRequestsByEmployee(int employeeId){
		
		List<ServiceRequest> employeeserviceRequest = serviceRequestService.getServiceRequestsByEmployee(employeeId);
		
		return ResponseEntity.ok(employeeserviceRequest);
	}
	
	@GetMapping("/allServiceRequests")
	public ResponseEntity<List<ServiceRequest>> getAllServiceRequests(){
		
		List<ServiceRequest> serviceRequest = serviceRequestService.getAllServiceRequests();
		
		return ResponseEntity.ok(serviceRequest);
	}
	
	@GetMapping("/findByStatus/{status}")
	public ResponseEntity<List<ServiceRequest>> findByStatus(String status){
		
		List<ServiceRequest> serviceRequestByStatus = serviceRequestService.findByStatus(status);
		
		return ResponseEntity.ok(serviceRequestByStatus);
	}

}
