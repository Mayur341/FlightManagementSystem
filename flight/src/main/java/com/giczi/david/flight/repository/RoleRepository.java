package com.giczi.david.flight.repository;

import org.springframework.data.repository.CrudRepository;

import com.giczi.david.flight.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String roleName);
	
}

