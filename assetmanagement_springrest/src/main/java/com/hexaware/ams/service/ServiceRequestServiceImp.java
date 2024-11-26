

package com.hexaware.ams.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.dto.AssetDto;
import com.hexaware.ams.dto.EmployeeDto;
import com.hexaware.ams.dto.IssueTypeDto;
import com.hexaware.ams.dto.ServiceRequestDto;
import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.AssetCategory;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.entity.ServiceRequest;
import com.hexaware.ams.entity.ServiceRequest.Status;
import com.hexaware.ams.exception.BadRequestException;
import com.hexaware.ams.exception.MethodArgumentNotValidException;
import com.hexaware.ams.exception.ResourceAlreadyExistsException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IAssetRepository;
import com.hexaware.ams.repository.IEmployeeRepository;
import com.hexaware.ams.repository.IIssueTypeRepository;
import com.hexaware.ams.repository.IServiceRequestRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceRequestServiceImp implements IServiceRequestService {

	@Autowired
	private IServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	private EmployeeServiceImp eS1;
	
	@Autowired
	private IssueTypeServiceImp iTS1;
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private IAssetRepository assetRepository;
	
	@Autowired
	private IIssueTypeRepository issueTypeRepository;
	
	Logger logger = LoggerFactory.getLogger(ServiceRequestServiceImp.class);
	
	@Override
	public ServiceRequest createServiceRequest(int employeeId, int assetId, int issueTypeId, String Description) {
		
		try {
		Employee e1 = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));
		
		Asset a1 = assetRepository.findById(assetId)
				.orElseThrow(() -> new ResourceNotFoundException("Asset Not Found with id " + assetId));
		
		IssueType i1 = issueTypeRepository.findById(issueTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("Issue Type not Found with id " + issueTypeId));
		
		ServiceRequest serviceRequest = new ServiceRequest();
		
		serviceRequest.setEmployee(e1);
		serviceRequest.setAsset(a1);
		serviceRequest.setDescription(Description);
		serviceRequest.setIssueType(i1);
		serviceRequest.setStatus(Status.Pending);
		serviceRequest.setRequestedAt(LocalDateTime.now());
		
		return serviceRequestRepository.save(serviceRequest);
		}
		catch(Exception e) {
			
			throw new BadRequestException("Failed to create a new Service Request");
		}
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
		
		return serviceRequest;
		
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
	public List<ServiceRequest> findByStatus(ServiceRequest.Status status) {
		
		
		List<ServiceRequest> s1 = serviceRequestRepository.findByStatus(status);
		
		return s1;
	}
	
	
	
	
	
	//Map ServiceRequestDto to ServiceRequest Entity
	public ServiceRequest mapToEntity(ServiceRequestDto s1) {
		
		ServiceRequest newServiceRequest = new ServiceRequest();
		
		if(s1.getServiceRequestId() != 0) {
			newServiceRequest.setServiceRequestId(s1.getServiceRequestId());
		}
		
		newServiceRequest.setEmployee(eS1.mapToEntity(s1.getEmployee()));
		
		//Need to add Asset Map to Entity function here 
		newServiceRequest.setAsset(mapToAssetEntity(s1.getAsset()));
		
		newServiceRequest.setDescription(s1.getDescription());
		
		newServiceRequest.setIssueType(iTS1.mapToEntity(s1.getIssueType()));
		
		newServiceRequest.setRequestedAt(s1.getRequestedAt());
		
		newServiceRequest.setStatus(Status.valueOf(s1.getStatus().name()));
		
		newServiceRequest.setRequestedAt(s1.getRequestedAt());
		
		return newServiceRequest;	
	
	}
	
	
	//Map AssetDto to AssetEntity
	public Asset mapToAssetEntity(AssetDto assetDto) {
		
		Asset a1 = new Asset();
		if(assetDto.getAssetId() != 0) {
			a1.setAssetId(assetDto.getAssetId());
		}
		
		a1.setAssetName(assetDto.getAssetName());
		a1.setCategory(new AssetCategory(assetDto.getCategory().getCategoryId(), assetDto.getCategory().getCategoryName()));
		a1.setAssetModel(assetDto.getAssetModel());
		a1.setManufacturingDate(assetDto.getManufacturingDate());
		a1.setExpiryDate(assetDto.getExpiryDate());
		a1.setAssetValue(assetDto.getAssetValue());
		
		return a1;
	}

	

	

	
}
