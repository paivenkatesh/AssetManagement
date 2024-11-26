package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.dto.ServiceRequestDto;
import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.service.IServiceRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ams/servicerequest")
public class ServiceRequestController {
	
	@Autowired
	IServiceRequestService serviceRequestService;
	
	
	//Create a new Service Request
	@PostMapping("/createServiceRequest/{employeeId}/{assetId}/{issueTypeId}")
	public ResponseEntity<ServiceRequest> createServiceRequest(@PathVariable int employeeId, @PathVariable int assetId, @PathVariable int issueTypeId, @RequestBody String Description) {
		
		ServiceRequest newServiceRequest = serviceRequestService.createServiceRequest(employeeId, assetId, issueTypeId, Description);
		return new ResponseEntity<>(newServiceRequest,HttpStatus.CREATED);
	}
	
	//Get Service Request By its Id
	@GetMapping("/getServiceRequestById/{serviceRequestId}")
	public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable int serviceRequestId) {
		
		ServiceRequest serviceRequest = serviceRequestService.getServiceRequestById(serviceRequestId);
		
		return ResponseEntity.ok(serviceRequest);
	}
	
	
	// Update a Service Request By id
	@PutMapping("/updateServiceRequest/{serviceRequestId}/{Status}")
	public ResponseEntity<ServiceRequest> updateServiceRequest(@PathVariable int serviceRequestId, @RequestBody ServiceRequest.Status status) {
		
		ServiceRequest updatedServiceRequest = serviceRequestService.updateServiceRequestStatus(serviceRequestId, status);
		
		return ResponseEntity.ok(updatedServiceRequest);
	}
	
	
	//Get service Requests By Employee Id 
	@GetMapping("/serviceRequestByEmployee/{employeeId}")
	public ResponseEntity<List<ServiceRequest>> getServiceRequestsByEmployee(@PathVariable int employeeId){
		
		List<ServiceRequest> employeeserviceRequest = serviceRequestService.getServiceRequestsByEmployee(employeeId);
		
		return ResponseEntity.ok(employeeserviceRequest);
	}
	
	
	
	// Get all service Request 
	@GetMapping("/allServiceRequests")
	public ResponseEntity<List<ServiceRequest>> getAllServiceRequests(){
		
		List<ServiceRequest> serviceRequest = serviceRequestService.getAllServiceRequests();
		
		return ResponseEntity.ok(serviceRequest);
	}
	
	
	
	// Find Service Requests by providing a Status
	@GetMapping("/findByStatus/{status}")
	public ResponseEntity<List<ServiceRequest>> findByStatus(@PathVariable String status){
		
		List<ServiceRequest> serviceRequestByStatus = serviceRequestService.findByStatus(status);
		
		return ResponseEntity.ok(serviceRequestByStatus);
	}
	

}
