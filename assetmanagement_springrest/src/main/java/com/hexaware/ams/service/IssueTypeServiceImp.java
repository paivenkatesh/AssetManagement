package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	@Transactional
	public IssueType addIssueType(IssueType issueType) {
		
		if(issueTypeRepository.existsByIssueTypeName(issueType.getIssueTypeName())) {
			
			throw new ResourceAlreadyExistsException("Issue Type already exists" + issueType.getIssueTypeName());
		}
		
		return issueTypeRepository.save(issueType);
	}

	@Override
	public IssueType getIssueTypeById(int issueTypeId) {
		
		return issueTypeRepository.findById(issueTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("Issue Type not found with id " + issueTypeId));
	}

	@Override
	public IssueType getIssueTypeByName(String issueTypeName) {
		
		IssueType issueType = issueTypeRepository.findByIssueTypeName(issueTypeName);
		
		if(issueType == null) {
			
			throw new ResourceNotFoundException("Issue type not found" + issueTypeName);
		}
		
		return issueType;
	}

	@Override
	public List<IssueType> getAllIssueTypes() {
		
		return issueTypeRepository.findAll();
	}

	@Override
	@Transactional
	public IssueType updateIssueType(int issueTypeId, IssueType issueTypeDetails) {
		
		
		
		IssueType issueType = getIssueTypeById(issueTypeId);
		
		if(issueType == null){
			
			throw new ResourceNotFoundException("Issue with id " +  issueTypeId + "not found" );
			
		}
		
		issueType.setIssueTypeName(issueTypeDetails.getIssueTypeName());
		
		return issueTypeRepository.save(issueType);
		
		
	}

	@Override
	@Transactional
	public void deleteIssueType(int issueTypeId) {
		
		try {
			
		issueTypeRepository.deleteById(issueTypeId);
		
		}catch(Exception e) {
			
			throw new BadRequestException("Failed to delete issue");
		}

	}

}
