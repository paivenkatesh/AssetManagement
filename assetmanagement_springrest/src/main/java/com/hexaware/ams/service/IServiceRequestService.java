package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.ServiceRequest;

public interface IServiceRequestService {

	ServiceRequest createServiceRequest(ServiceRequest serviceRequest);
	ServiceRequest getServiceRequestById(int serviceRequestId);
	ServiceRequest updateServiceRequest(ServiceRequest serviceRequest);
	List<ServiceRequest> getServiceRequestsByEmployee(int employeeId);
	List<ServiceRequest> getAllServiceRequests();
	List<ServiceRequest> findByStatus(String status);
}
