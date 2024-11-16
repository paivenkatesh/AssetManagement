package com.hexaware.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.ams.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
