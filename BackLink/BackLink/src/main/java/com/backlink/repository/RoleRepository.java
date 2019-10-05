package com.backlink.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backlink.entities.Role;
import com.backlink.entities.Role.RoleName;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long>{
	
	Optional<Role> findByName(RoleName roleName);
	
}
