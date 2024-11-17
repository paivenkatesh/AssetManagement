package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.exception.MethodArgumentNotValidException;
import com.hexaware.ams.exception.ResourceAlreadyExistsException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IServiceRequestRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceRequestServiceImp implements IServiceRequestService {

	@Autowired
	private IServiceRequestRepository serviceRequestRepository;
	
	
	@Override
	@Transactional
	public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
		
		if(serviceRequestRepository.existsById(serviceRequest.getServiceRequestId())) {
			
			throw new ResourceAlreadyExistsException("Service Request already exists " + serviceRequest.getServiceRequestId());
		}
		
		return serviceRequestRepository.save(serviceRequest);
	}

	@Override
	public ServiceRequest getServiceRequestById(int serviceRequestId) {
		
		return serviceRequestRepository.findById(serviceRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Request not found with id " + serviceRequestId) );
	}

	@Override
	@Transactional
	public ServiceRequest updateServiceRequestStatus(int serviceRequestId, ServiceRequest.Status status) {
		
		ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Request Not Found"));
		
		serviceRequest.setStatus(status);
		
		return serviceRequestRepository.save(serviceRequest);
	}

	@Override
	public List<ServiceRequest> getServiceRequestsByEmployee(int employeeId) {
		
		try {
			
		return serviceRequestRepository.findByEmployeeEmployeeId(employeeId);
		
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("Service Request for Employee does not exist" + employeeId);
		}
		
	}

	@Override
	public List<ServiceRequest> getAllServiceRequests() {
		
		return serviceRequestRepository.findAll();
	}

	@Override
	public List<ServiceRequest> findByStatus(String status) {
		
		try {
			
		return serviceRequestRepository.findByStatus(status);
		
		}catch(Exception e) {
			
			throw new MethodArgumentNotValidException("Enter a valid Status");
		}
	
	}

}
