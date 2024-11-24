package com.hexaware.ams.service;

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

import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.Employee.Gender;
import com.hexaware.ams.entity.Role;

@SpringBootTest
class EmployeeServiceImpTest {
	
	@Autowired
	IEmployeeService employeeService;

	@Test
	@Disabled
	@DisplayName("register Employee")
	void testRegisterEmployee() {
		
		Role role = new Role(2, "User");
		
		Employee employee = new Employee(3,"Loraine", Gender.Female, "7242332675", "05 Ueno Street", "loraine@example.com", "Loraine204", role);
		
		//Employee e1 = employeeService.registerEmployee(employee);
		
		//assertNotNull(e1);
	}

	@Test
	@DisplayName("get employee by Id")
	void testGetEmployeeById() {
		
		Employee e2 = employeeService.getEmployeeById(2);
		
		assertEquals(2, e2.getEmployeeId());
	}

	@Test
	@DisplayName("update employee")
	@Disabled 
	void testUpdateEmployee() {
		
		Role role = new Role(2, "User");
		
		Employee employee = new Employee(4,"Martha", Gender.Female, "9794332675", "05 Okachimachi Street", "martha@example.com", "martha203", role);
		
		//Employee e3 = employeeService.updateEmployee(3, employee);
		
		//assertEquals("05 Okachimachi Street", e3.getAddress());
		
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
	void testFindByEmailandPassword() {
		
		int  e4 = employeeService.findByEmailandPassword("martha@example.com", "martha203");
		
		assertNotNull(e4);
		
		
	}

	@Test
	void testExistsByEmail() {
		
		boolean flag2 = employeeService.existsByEmail("martha@example.com");
		
		assertTrue(flag2);
		
	}

}
