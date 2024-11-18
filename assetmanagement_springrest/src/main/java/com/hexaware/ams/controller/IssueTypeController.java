package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.service.IIssueTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ams/IssueType")
@Validated
public class IssueTypeController {
	
	@Autowired
	IIssueTypeService issueTypeService;
	
	@PostMapping("/addIssueType")
	IssueType addIssueType(@Valid @RequestBody IssueType issueType) {
		
		return issueTypeService.addIssueType(issueType);
	}
	
	@GetMapping("/issueTypeById/{issueTypeId}")
	IssueType getIssueTypeById(@PathVariable int issueTypeId) {
		
		return issueTypeService.getIssueTypeById(issueTypeId);
	}
	
	
	@GetMapping("/issueTypeByName/{Name}")
	IssueType getIssueTypeByName(@PathVariable String issueTypeName) {
		
		return issueTypeService.getIssueTypeByName(issueTypeName);
	}
	
	@GetMapping("/allIssueTypes")
	List<IssueType> getAllIssueTypes(){
		
		return issueTypeService.getAllIssueTypes();
		
	}
	
	@PutMapping("/updateIssueType/{issueTypeId}/{issueTypeDetails}")
	IssueType updateIssueType(@PathVariable int issueTypeId, @Valid @RequestBody IssueType issueTypeDetails) {
		
		return issueTypeService.updateIssueType(issueTypeId, issueTypeDetails);
	}
	
	@DeleteMapping("/delete/{issueTypeId}")
	void deleteIssueType(@PathVariable int issueTypeId) {
		
		issueTypeService.deleteIssueType(issueTypeId);
	}

}
