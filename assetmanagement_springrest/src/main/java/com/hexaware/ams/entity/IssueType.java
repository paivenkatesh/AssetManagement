package com.hexaware.ams.entity;

/*
 * @Author: Venkatesh Pai
 * Date: 16-11-24
 * Description: IssueType Entity with mappings
 * Issue Type Entity
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "issue_type")
public class IssueType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int issueTypeId;
	
	
	@NotNull(message = "Issue type name cannot be null")
    @Size(min = 2, max = 100, message = "Issue type name must be between 2 and 100 characters")
	@Column(name = "issue_type_name", unique = true, nullable = false)
	private String issueTypeName;

	public IssueType() {
		super();
	}

	

	public IssueType(int issueTypeId,
			@NotNull(message = "Issue type name cannot be null") @Size(min = 2, max = 100, message = "Issue type name must be between 2 and 100 characters") String issueTypeName) {
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
