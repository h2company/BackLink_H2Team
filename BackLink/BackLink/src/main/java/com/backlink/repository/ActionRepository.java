package com.backlink.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backlink.entities.Action;

public interface ActionRepository extends MongoRepository<Action, String> {

}
