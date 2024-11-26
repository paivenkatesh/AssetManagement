package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.dto.ServiceRequestDto;
import com.hexaware.ams.dto.ServiceRequestDto;
import com.hexaware.ams.entity.ServiceRequest;

public interface IServiceRequestService {

	ServiceRequest createServiceRequest(int employeeId, int assetId, int issueTypeId, String Description);
	ServiceRequest getServiceRequestById(int serviceRequestId);
	ServiceRequest updateServiceRequestStatus(int serviceRequestId, ServiceRequest.Status status);
	List<ServiceRequest> getServiceRequestsByEmployee(int employeeId);
	List<ServiceRequest> getAllServiceRequests();
	List<ServiceRequest> findByStatus(ServiceRequest.Status status);
}
