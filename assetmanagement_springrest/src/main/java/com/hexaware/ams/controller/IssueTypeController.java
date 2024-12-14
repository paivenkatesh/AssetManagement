package com.hexaware.ams.controller;

/*
 * Author: Venkatesh Pai
 * Date: 18-11-24
 * Description: Issue Type Controller
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.dto.IssueTypeDto;
import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.service.IIssueTypeService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/ams/IssueType")
@Validated
public class IssueTypeController {
	
	@Autowired
	IIssueTypeService issueTypeService;
	
	
	//Create a new IssueType
	@PostMapping("/addIssueType")
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<IssueType> addIssueType(@Valid @RequestBody IssueTypeDto issueType) {
		
		IssueType newIssueType = issueTypeService.addIssueType(issueType);
		
		return new ResponseEntity<>(newIssueType, HttpStatus.CREATED);
	}
	
	
	//Get an Issue Type by Id
	@GetMapping("/issueTypeById/{issueTypeId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<IssueType> getIssueTypeById(@PathVariable int issueTypeId) {
		
		IssueType issueType = issueTypeService.getIssueTypeById(issueTypeId);
		
		return ResponseEntity.ok(issueType);
	}
	
	
	//Get an Issue Type by Name
	@GetMapping("/issueTypeByName/{issueTypeName}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<IssueType> getIssueTypeByName(@PathVariable String issueTypeName) {
		
		IssueType issueTypeByName = issueTypeService.getIssueTypeByName(issueTypeName);
		
		return ResponseEntity.ok(issueTypeByName);
	}
	
	
	//All issue Types 
	@GetMapping("/allIssueTypes")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<List<IssueType>> getAllIssueTypes(){
		
		List<IssueType> issueType = issueTypeService.getAllIssueTypes();
		
		return ResponseEntity.ok(issueType);
		
	}
	
	
	// Update an Issue Type 
	@PutMapping("/updateIssueType/{issueTypeId}/{issueTypeDetails}")
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<IssueType> updateIssueType(@PathVariable int issueTypeId, @Valid @RequestBody IssueTypeDto issueTypeDetails) {
		
		IssueType updatedIssueType = issueTypeService.updateIssueType(issueTypeId, issueTypeDetails);
		
		return ResponseEntity.ok(updatedIssueType);
	}
	
	// Delete an Issue Type using Id
	@DeleteMapping("/delete/{issueTypeId}")
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteIssueType(@PathVariable int issueTypeId) {
		
		issueTypeService.deleteIssueType(issueTypeId);
		
		return ResponseEntity.noContent().build();
	}

}