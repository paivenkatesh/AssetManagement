package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.ServiceRequest;

public interface IServiceRequestService {
	
	List<ServiceRequest> getAllServiceRequests();
	ServiceRequest getServiceRequestById(int serviceRequestId);
	List<ServiceRequest> getServiceRequestByEmployeeId(int employeeId);
	List<ServiceRequest> getServiceRequestsByStatus(String status);
	int addServiceRequest(ServiceRequest serviceRequest);
	int updateServiceRequestStatus(int serviceRequestId, String status);
	int deleteServiceRequest(int serviceRequestId);
}
