package com.hexaware.ams.repository;

/*
 * Author: Venkatesh Pai
 * Date: 14-11-24
 * Description: Issue Type Repository Interface
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.IssueType;

@Repository
public interface IIssueTypeRepository extends JpaRepository<IssueType, Integer> {

	IssueType findByIssueTypeName(String issueTypeName);
	
	boolean existsByIssueTypeName(String issueTypeName);
}
