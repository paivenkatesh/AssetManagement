package com.hexaware.ams.service;

import java.util.List;

import com.hexaware.ams.entity.IssueType;

public interface IIssueTypeService {

	IssueType addIssueType(IssueType issueType);
	IssueType getIssueTypeById(int issueTypeId);
	IssueType getIssueTypeByName(String issueTypeName);
	List<IssueType> getAllIssueTypes();
	IssueType updateIssueType(int issueTypeId, IssueType issueTypeDetails);
	void deleteIssueType(int issueTypeId);
}
