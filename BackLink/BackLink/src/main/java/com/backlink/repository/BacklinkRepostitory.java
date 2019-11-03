package com.backlink.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backlink.entities.Action;
import com.backlink.entities.Backlink;
import com.backlink.entities.User;

public interface BacklinkRepostitory extends MongoRepository<Backlink, String> {
	List<Backlink> findAllByUsername(String username);
	
	
}
