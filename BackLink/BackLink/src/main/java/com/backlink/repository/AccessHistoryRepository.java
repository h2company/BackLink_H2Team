package com.backlink.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.backlink.entities.AccessHistory;
import com.backlink.entities.Backlink;

public interface AccessHistoryRepository extends MongoRepository<AccessHistory, String> {
	
	@Query(value="{ 'urlAgent' : ?0 }")
	List<AccessHistory> findByTheAccessHistoryUrlAgent(String urlAgent);
}
