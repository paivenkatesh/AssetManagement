package com.hexaware.ams.service;

/*
 * Author: Venkatesh Pai
 * Date: 19-11-24
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ams.dto.IssueTypeDto;
import com.hexaware.ams.entity.IssueType;
import com.hexaware.ams.repository.IIssueTypeRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IssueTypeServiceImpTest {

	@Mock
	private IIssueTypeRepository issueTypeRepository;

	@InjectMocks
	private IssueTypeServiceImp issueTypeService;

	private IssueType issueType;
	private IssueTypeDto issueTypeDto;

	@BeforeEach
	void setUp() {
		issueTypeDto = new IssueTypeDto(4, "Hardware Issue");

		issueType = new IssueType(5, "Hardware Issue");
	}

	@Test
	@Order(1)
	void testAddIssueType() {

		// Given
		when(issueTypeRepository.existsByIssueTypeName(issueTypeDto.getIssueTypeName())).thenReturn(false);
		when(issueTypeRepository.save(any(IssueType.class))).thenReturn(issueType);

		// When
		IssueType savedIssueType = issueTypeService.addIssueType(issueTypeDto);

		// Then
		assertThat(savedIssueType).isNotNull();
		assertThat(savedIssueType.getIssueTypeName()).isEqualTo("Hardware Issue");
		verify(issueTypeRepository, times(1)).existsByIssueTypeName(issueTypeDto.getIssueTypeName());
		verify(issueTypeRepository, times(1)).save(any(IssueType.class));

	}

	@Test
	@Order(2)
	void testGetIssueTypeById() {

		// Given
		when(issueTypeRepository.findById(1)).thenReturn(Optional.of(issueType));

		// When
		IssueType foundIssueType = issueTypeService.getIssueTypeById(1);

		// Then
		assertThat(foundIssueType).isNotNull();
		assertThat(foundIssueType.getIssueTypeName()).isEqualTo("Hardware Issue");
		verify(issueTypeRepository, times(1)).findById(1);
	}

	@Test
	@Order(3)
	void testGetIssueTypeByName() {

		// Given
		when(issueTypeRepository.findByIssueTypeName("Hardware Issue")).thenReturn(issueType);

		// When
		IssueType foundIssueType = issueTypeService.getIssueTypeByName("Hardware Issue");

		// Then
		assertThat(foundIssueType).isNotNull();
		assertThat(foundIssueType.getIssueTypeName()).isEqualTo("Hardware Issue");
		verify(issueTypeRepository, times(1)).findByIssueTypeName("Hardware Issue");

	}

	@Test
	@Order(4)
	void testGetAllIssueTypes() {

		// Given
		IssueType issueType2 = new IssueType(2, "Software Issue");
		when(issueTypeRepository.findAll()).thenReturn(List.of(issueType, issueType2));

		// When
		List<IssueType> issueTypes = issueTypeService.getAllIssueTypes();

		// Then
		assertThat(issueTypes).hasSize(2);
		assertThat(issueTypes.get(0).getIssueTypeName()).isEqualTo("Hardware Issue");
		verify(issueTypeRepository, times(1)).findAll();

	}

	@Test
	@Order(5)
	void testDeleteIssueType() {
		// When
		issueTypeService.deleteIssueType(1);

		// Then
		verify(issueTypeRepository, times(1)).deleteById(1);
	}

}
