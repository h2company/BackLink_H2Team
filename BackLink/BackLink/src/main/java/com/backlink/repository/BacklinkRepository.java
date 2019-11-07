package com.backlink.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backlink.entities.Backlink;
import com.backlink.entities.User;

@Repository
public interface BacklinkRepository extends MongoRepository<Backlink, String>{	
	

	List<Backlink> findByIdIn(List<Long> userIds);

	Optional<Backlink> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	
}
