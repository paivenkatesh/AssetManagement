package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.repository.IServiceRequestRepository;

@Service
public class ServiceRequestImp implements IServiceRequestService {

	@Autowired
	private IServiceRequestRepository serviceRequestRepository;
	
	@Override
	public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
		
		return serviceRequestRepository.save(serviceRequest);
	}

	@Override
	public ServiceRequest getServiceRequestById(int serviceRequestId) {
		
		return serviceRequestRepository.findById(serviceRequestId).orElse(new ServiceRequest());
	}

	@Override
	public ServiceRequest updateServiceRequest(ServiceRequest serviceRequest) {
		
		return serviceRequestRepository.save(serviceRequest);
	}

	@Override
	public List<ServiceRequest> getServiceRequestsByEmployee(int employeeId) {
		
		return serviceRequestRepository.findByEmployeeEmployeeId(employeeId);
	}

	@Override
	public List<ServiceRequest> getAllServiceRequests() {
		
		return serviceRequestRepository.findAll();
	}

	@Override
	public List<ServiceRequest> findByStatus(String status) {
		
		return serviceRequestRepository.findByStatus(status);
	}

}
