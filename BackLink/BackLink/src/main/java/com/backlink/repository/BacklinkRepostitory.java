package com.backlink.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backlink.entities.Backlink;

public interface BacklinkRepostitory extends MongoRepository<Backlink, String> {
	List<Backlink> findAllByUsername(String username);
	
	Page<Backlink> findByEndTimeGreaterThanEqual(Long endTime, Pageable p);
}
