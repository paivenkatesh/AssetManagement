package com.hexaware.ams.entity;

public class IssueType {

	private int issueTypeId;
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
