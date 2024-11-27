package com.hexaware.ams.repository;

/*
 * Author: Venkatesh Pai
 * Date: 14-11-24
 * Description: Employee Repository Interface
 */
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	int findByEmailAndPassword(String email, String password);
	
	Optional<Employee> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
