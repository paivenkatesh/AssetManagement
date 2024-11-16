package com.hexaware.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.repository.IIssueTypeRepository;

@Service
public class IssueTypeServiceImp implements IIssueTypeService {

	@Autowired
	IIssueTypeRepository issueTypeRepository;
	
	@Override
	public IssueType addIssueType(IssueType issueType) {
		
		return issueTypeRepository.save(issueType);
	}

	@Override
	public IssueType getIssueTypeById(int issueTypeId) {
		
		return issueTypeRepository.findById(issueTypeId).orElse(null);
	}

	@Override
	public IssueType getIssueTypeByName(String issueTypeName) {
		
		return issueTypeRepository.findByIssueTypeName(issueTypeName);
	}

	@Override
	public List<IssueType> getAllIssueTypes() {
		
		return issueTypeRepository.findAll();
	}

	@Override
	public IssueType updateIssueType(int issueTypeId, IssueType issueTypeDetails) {
		
		IssueType issueType = getIssueTypeById(issueTypeId);
		
		issueType.setIssueTypeName(issueTypeDetails.getIssueTypeName());
		
		return issueTypeRepository.save(issueType);
		
		
	}

	@Override
	public void deleteIssueType(int issueTypeId) {
		
		issueTypeRepository.deleteById(issueTypeId);

	}

}
