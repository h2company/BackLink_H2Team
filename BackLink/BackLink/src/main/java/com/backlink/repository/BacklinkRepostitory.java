package com.backlink.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backlink.entities.Action;
import com.backlink.entities.Backlink;

public interface BacklinkRepostitory extends MongoRepository<Backlink, String> {

}
