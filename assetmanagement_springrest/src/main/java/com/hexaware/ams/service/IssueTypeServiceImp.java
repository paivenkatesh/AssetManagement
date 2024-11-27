package com.hexaware.ams.service;

/*
 * Author: Venkatesh Pai
 * Date: 09-11-24
 * Description: IssueType Service Implementation
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.dto.IssueTypeDto;
import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.exception.BadRequestException;
import com.hexaware.ams.exception.ResourceAlreadyExistsException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IIssueTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class IssueTypeServiceImp implements IIssueTypeService {

	@Autowired
	IIssueTypeRepository issueTypeRepository;
	
	Logger logger = LoggerFactory.getLogger(EmployeeServiceImp.class);
	
	
	//Add a new Issue Type
	@Override
	@Transactional
	public IssueType addIssueType(IssueTypeDto issueType) {
		
		if(issueTypeRepository.existsByIssueTypeName(issueType.getIssueTypeName())) {
			
			throw new ResourceAlreadyExistsException("Issue Type already exists" + issueType.getIssueTypeName());
		}
		
		IssueType i1 = mapToEntity(issueType);
		
		logger.info("Adding a new Issue Type");
		
		return issueTypeRepository.save(i1);
	}

	
	//Get an IssueType by its Id
	@Override
	public IssueType getIssueTypeById(int issueTypeId) {
		
		logger.info("Finding Issue with given Id");
		return issueTypeRepository.findById(issueTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("Issue Type not found with id " + issueTypeId));
	}

	
	//Get an IssueType by its Name
	@Override
	public IssueType getIssueTypeByName(String issueTypeName) {
		
		logger.info("Trying to find Issue Type with given name ");
		IssueType issueType = issueTypeRepository.findByIssueTypeName(issueTypeName);
		
		if(issueType == null) {
			
			logger.info("Could not find Issue Type with given Name ");
			throw new ResourceNotFoundException("Issue type not found" + issueTypeName);
		}
		
		return issueType;
	}

	
	//Get a List of all the existing IssueTypes
	@Override
	public List<IssueType> getAllIssueTypes() {
		
		return issueTypeRepository.findAll();
	}

	
	//Update an IssueType
	@Override
	@Transactional
	public IssueType updateIssueType(int issueTypeId, IssueTypeDto issueTypeDetails) {

		logger.warn("Issue Type will be updated");
		
		IssueType issueType = getIssueTypeById(issueTypeId);
		
		if(issueType == null){
			
			throw new ResourceNotFoundException("Issue with id " +  issueTypeId + "not found" );
			
		}
		
		IssueType i2 = mapToEntity(issueTypeDetails);
		
		issueType.setIssueTypeName(i2.getIssueTypeName());
		
		logger.info("Updated the issue requested");
		
		return issueTypeRepository.save(issueType);
		
		
	}

	
	//Delete an IssueType by its Id
	@Override
	@Transactional
	public void deleteIssueType(int issueTypeId) {
		
		try {
			
		logger.warn("Issue will be deleted");
		issueTypeRepository.deleteById(issueTypeId);
		
		}catch(Exception e) {
			
			throw new BadRequestException("Failed to delete issue");
		}

	}
	
	//Helper method to Map a Dto to Entity
	public IssueType mapToEntity(IssueTypeDto issueTypeDto) {
		
		IssueType issueType = new IssueType();
		if(issueTypeDto.getIssueTypeId() != 0) {
			issueType.setIssueTypeId(issueTypeDto.getIssueTypeId());
		}
		issueType.setIssueTypeName(issueTypeDto.getIssueTypeName());
		
		return issueType;
	}

}
