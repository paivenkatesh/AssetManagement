package com.hexaware.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.ams.entity.IssueType;

public interface IIssueTypeRepository extends JpaRepository<IssueType, Integer> {

	IssueType findByIssueTypeName(String issueTypeName);
	
	boolean existsByIssueTypeName(String issueTypeName);
}
