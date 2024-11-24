package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.dto.IssueTypeDto;
import com.hexaware.ams.entity.IssueType;

public interface IIssueTypeService {

	IssueType addIssueType(IssueTypeDto issueType);
	IssueType getIssueTypeById(int issueTypeId);
	IssueType getIssueTypeByName(String issueTypeName);
	List<IssueType> getAllIssueTypes();
	IssueType updateIssueType(int issueTypeId, IssueTypeDto issueTypeDetails);
	void deleteIssueType(int issueTypeId);
}
