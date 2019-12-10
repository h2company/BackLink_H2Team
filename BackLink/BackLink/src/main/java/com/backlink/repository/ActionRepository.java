package com.backlink.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backlink.entities.Action;

public interface ActionRepository extends MongoRepository<Action, String> {
	
	List<Action> findByUsername(String username, Sort sort);
}
