package com.hexaware.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<IssueType> addIssueType(@Valid @RequestBody IssueType issueType) {
		
		IssueType newIssueType = issueTypeService.addIssueType(issueType);
		
		return new ResponseEntity<>(newIssueType, HttpStatus.CREATED);
	}
	
	@GetMapping("/issueTypeById/{issueTypeId}")
	public ResponseEntity<IssueType> getIssueTypeById(@PathVariable int issueTypeId) {
		
		IssueType issueType = issueTypeService.getIssueTypeById(issueTypeId);
		
		return ResponseEntity.ok(issueType);
	}
	
	
	@GetMapping("/issueTypeByName/{Name}")
	public ResponseEntity<IssueType> getIssueTypeByName(@PathVariable String issueTypeName) {
		
		IssueType issueTypeByName = issueTypeService.getIssueTypeByName(issueTypeName);
		
		return ResponseEntity.ok(issueTypeByName);
	}
	
	@GetMapping("/allIssueTypes")
	public ResponseEntity<List<IssueType>> getAllIssueTypes(){
		
		List<IssueType> issueType = issueTypeService.getAllIssueTypes();
		
		return ResponseEntity.ok(issueType);
		
	}
	
	@PutMapping("/updateIssueType/{issueTypeId}/{issueTypeDetails}")
	public ResponseEntity<IssueType> updateIssueType(@PathVariable int issueTypeId, @Valid @RequestBody IssueType issueTypeDetails) {
		
		IssueType updatedIssueType = issueTypeService.updateIssueType(issueTypeId, issueTypeDetails);
		
		return ResponseEntity.ok(updatedIssueType);
	}
	
	@DeleteMapping("/delete/{issueTypeId}")
	public ResponseEntity<Void> deleteIssueType(@PathVariable int issueTypeId) {
		
		issueTypeService.deleteIssueType(issueTypeId);
		
		return ResponseEntity.noContent().build();
	}

}
