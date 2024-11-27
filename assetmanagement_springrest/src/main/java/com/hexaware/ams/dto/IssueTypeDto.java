package com.hexaware.ams.dto;

/*
 * Author: Venkatesh Pai
 * Date: 24-11-24
 * Description: Issue Type Dto
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueTypeDto {
	
	private int issueTypeId;
	private String issueTypeName;

}
