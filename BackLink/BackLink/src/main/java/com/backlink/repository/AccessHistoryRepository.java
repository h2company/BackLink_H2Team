package com.backlink.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backlink.entities.AccessHistory;

public interface AccessHistoryRepository extends MongoRepository<AccessHistory, String> {

}
