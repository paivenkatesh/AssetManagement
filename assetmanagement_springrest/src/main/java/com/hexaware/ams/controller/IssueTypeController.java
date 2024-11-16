package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.service.IIssueTypeService;

@RestController
@RequestMapping("/ams/IssueType")
public class IssueTypeController {
	
	@Autowired
	IIssueTypeService issueTypeService;
	
	@PostMapping("/addIssueType")
	IssueType addIssueType(IssueType issueType) {
		
		return issueTypeService.addIssueType(issueType);
	}
	
	@GetMapping("/issueTypeById/{issueTypeId}")
	IssueType getIssueTypeById(int issueTypeId) {
		
		return issueTypeService.getIssueTypeById(issueTypeId);
	}
	
	
	@GetMapping("/issueTypeByName/{Name}")
	IssueType getIssueTypeByName(String issueTypeName) {
		
		return issueTypeService.getIssueTypeByName(issueTypeName);
	}
	
	@GetMapping("/allIssueTypes")
	List<IssueType> getAllIssueTypes(){
		
		return issueTypeService.getAllIssueTypes();
		
	}
	
	@PutMapping("/updateIssueType/{issueTypeId}/{issueTypeDetails}")
	IssueType updateIssueType(int issueTypeId, IssueType issueTypeDetails) {
		
		return issueTypeService.updateIssueType(issueTypeId, issueTypeDetails);
	}
	
	@DeleteMapping("/delete/{issueTypeId}")
	void deleteIssueType(int issueTypeId) {
		
		issueTypeService.deleteIssueType(issueTypeId);
	}

}
