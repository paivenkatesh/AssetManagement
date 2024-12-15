package com.hexaware.ams.service;

/*
 * Author: Venkatesh Pai
 * Date: 19-11-24
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ams.dto.EmployeeDto;
import com.hexaware.ams.dto.EmployeeDto.GenderDTO;
import com.hexaware.ams.dto.RoleDto;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.Employee.Gender;
import com.hexaware.ams.entity.Role;

@SpringBootTest
class EmployeeServiceImpTest {
	
	@Autowired
	IEmployeeService employeeService;

	@Test
	@DisplayName("register Employee")
	@Disabled
	void testRegisterEmployee() {
		
		RoleDto role = new RoleDto(2, "User");
		
		EmployeeDto employee = new EmployeeDto(20,"Denise", GenderDTO.Female, "7242332643", "05 Tokyo Street", "denise@example.com", "Denise224", role);
		
		Employee e1 = employeeService.registerEmployee(employee);
		
		assertNotNull(e1);
	}

	@Test
	@DisplayName("get employee by Id")
	@Disabled
	void testGetEmployeeById() {
		
		Employee e2 = employeeService.getEmployeeById(2);
		
		assertEquals(2, e2.getEmployeeId());
	}

	@Test
	@DisplayName("update employee")
	@Disabled
	void testUpdateEmployee() {
		
		RoleDto role = new RoleDto(2, "User");
		
		EmployeeDto employee = new EmployeeDto(5,"Loraine", GenderDTO.Female, "7242332675", "05 Okachimachi Street", "loraine@example.com", "martha204", role);
		
		Employee e3 = employeeService.updateEmployee(5, employee);
		
		assertEquals("05 Okachimachi Street", e3.getAddress());
		
	}

	@Test
	@Disabled
	void testDeleteEmployee() {
		
		employeeService.deleteEmployee(4);
	
	}

	@Test
	void testGetAllEmployee() {
		
		List<Employee> employee = employeeService.getAllEmployee();
		
		boolean flag = employee.isEmpty();
		
		assertFalse(flag);
	}

	@Test
	@Disabled
	void testFindByEmailandPassword() {
		
		int  e4 = employeeService.findByEmailandPassword("trevor@example.com", "Trevor213");
		
		assertNotNull(e4);
		
		
	}

	@Test
	@Disabled
	void testExistsByEmail() {
		
		boolean flag2 = employeeService.existsByEmail("martha@example.com");
		
		assertTrue(flag2);
		
	}

}
