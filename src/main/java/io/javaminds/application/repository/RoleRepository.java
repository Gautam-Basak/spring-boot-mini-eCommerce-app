package io.javaminds.application.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.javaminds.application.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Set<Role> findByName(String ADMIN);

}
