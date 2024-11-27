package com.hexaware.ams.repository;

/*
 * Author: Venkatesh Pai
 * Date: 14-11-24
 * Description: Role Repository Interface
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
