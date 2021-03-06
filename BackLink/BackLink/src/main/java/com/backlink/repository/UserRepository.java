package com.backlink.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.backlink.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	Optional<User> findByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

	List<User> findByIdIn(List<Long> userIds);	
	
	List<User> findByEnabledTrue();

	Optional<User> findByUsername(String username);
	
	Optional<User> findByPhone(String phone);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value="{}", fields="{email : 0}")
	List<User> findPoint();
	
}
