package com.hexaware.ams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "issue_type")
public class IssueType {
	
	@Id
	private int issueTypeId;
	
	@Column(unique = true, nullable = false)
	private String issueTypeName;

	public IssueType() {
		super();
	}

	public IssueType(int issueTypeId, String issueTypeName) {
		super();
		this.issueTypeId = issueTypeId;
		this.issueTypeName = issueTypeName;
	}

	public int getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(int issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public String getIssueTypeName() {
		return issueTypeName;
	}

	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
	}

	@Override
	public String toString() {
		return "IssueType [issueTypeId=" + issueTypeId + ", issueTypeName=" + issueTypeName + "]";
	}
	
	

}
