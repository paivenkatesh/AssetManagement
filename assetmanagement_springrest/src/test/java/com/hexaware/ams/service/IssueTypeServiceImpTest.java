package com.hexaware.ams.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.repository.IIssueTypeRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class IssueTypeServiceImpTest {
	
	@Mock
	private IIssueTypeRepository issueTypeRepository;
	
	@InjectMocks
	private IssueTypeServiceImp issueTypeService;
	
	private IssueType issueType;
	
	
	@BeforeEach
	void setUp() {
		
		issueType = new IssueType(1, "Hardware Issue");
	}
	

	@Test
	@Disabled
	void testAddIssueType() {
		
		//Given
		when(issueTypeRepository.save(any(IssueType.class))).thenReturn(issueType);
		
		IssueType savedIssueType = issueTypeService.addIssueType(issueType);
		
		assertThat(savedIssueType).isNotNull();
		assertThat(savedIssueType.getIssueTypeName()).isEqualTo("Hardware Issue");
		
		verify(issueTypeRepository, times(1)).save(any(IssueType.class));
	}

	@Test
	void testGetIssueTypeById() {
		
		//Given 
		when(issueTypeRepository.findById(1)).thenReturn(Optional.of(issueType));
		
		//When 
		IssueType foundIssueType = issueTypeService.getIssueTypeById(1);
		
		assertThat(foundIssueType).isNotNull();
		assertThat(foundIssueType.getIssueTypeName()).isEqualTo("Hardware Issue");
		verify(issueTypeRepository, times(1)).findById(1);
	}

	@Test
	void testGetIssueTypeByName() {
		
		String issueTypeName = "Hardware Issue";
		
		when(issueTypeRepository.findByIssueTypeName(issueTypeName)).thenReturn(issueType);
		
		IssueType result = issueTypeService.getIssueTypeByName(issueTypeName);
		
		assertThat(result).isNotNull();
		
	}

	@Test
	void testGetAllIssueTypes() {
		
		
		
	}

	@Test
	@Disabled
	void testUpdateIssueType() {
		
		IssueType updatedIssueType = new IssueType(1, "Software Issue");
		
		when(issueTypeRepository.findById(1)).thenReturn(Optional.of(issueType));
		
		when(issueTypeRepository.save(any(IssueType.class))).thenReturn(updatedIssueType);
		
		IssueType result = issueTypeService.updateIssueType(1, updatedIssueType);
		
		assertThat(result).isNotNull();
		assertThat(result.getIssueTypeName()).isEqualTo("Software Issue");
		verify(issueTypeRepository, times(1)).findById(1);
		
	}

	@Test
	@Disabled
	void testDeleteIssueType() {
		
		 when(issueTypeRepository.findById(1)).thenReturn(Optional.of(issueType));
		 
		 issueTypeService.deleteIssueType(1);
		 
		 verify(issueTypeRepository, times(1)).deleteById(1);

		 
		 
	}

}
