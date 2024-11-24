package com.hexaware.ams.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.exception.MethodArgumentNotValidException;
import com.hexaware.ams.exception.ResourceAlreadyExistsException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IServiceRequestRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceRequestServiceImp implements IServiceRequestService {

	@Autowired
	private IServiceRequestRepository serviceRequestRepository;
	
	Logger logger = LoggerFactory.getLogger(ServiceRequestServiceImp.class);
	
	@Override
	@Transactional
	public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
		
		if(serviceRequestRepository.existsById(serviceRequest.getServiceRequestId())) {
			
			logger.warn("Service Request has duplicate values ");
			throw new ResourceAlreadyExistsException("Service Request already exists " + serviceRequest.getServiceRequestId());
		}
		
		logger.info("Service Request Successfully created");
		return serviceRequestRepository.save(serviceRequest);
	}

	@Override
	public ServiceRequest getServiceRequestById(int serviceRequestId) {
		
		logger.info("Trying to find service request by Id");
		return serviceRequestRepository.findById(serviceRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Request not found with id " + serviceRequestId) );
	}

	@Override
	@Transactional
	public ServiceRequest updateServiceRequestStatus(int serviceRequestId, ServiceRequest.Status status) {
		
		logger.warn("Service Request Status will be updated");
		ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Request Not Found"));
		
		serviceRequest.setStatus(status);
		
		logger.info("Service Request status updated successfully");
		return serviceRequestRepository.save(serviceRequest);
	}

	@Override
	public List<ServiceRequest> getServiceRequestsByEmployee(int employeeId) {
		
		try {
			
			logger.info("Getting Service Requests by Employee Id");
		return serviceRequestRepository.findByEmployeeEmployeeId(employeeId);
		
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("Service Request for Employee does not exist" + employeeId);
		}
		
	}

	@Override
	public List<ServiceRequest> getAllServiceRequests() {
		
		logger.info("Getting List of all Service Requests");
		return serviceRequestRepository.findAll();
	}

	@Override
	public List<ServiceRequest> findByStatus(String status) {
		
		try {
			
			logger.info("Getting Service Requests by Status");
		return serviceRequestRepository.findByStatus(status);
		
		}catch(Exception e) {
			
			throw new MethodArgumentNotValidException("Enter a valid Status");
		}
	
	}

}
