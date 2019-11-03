package com.backlink.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backlink.entities.LogSystem;
import com.backlink.entities.LogSystem.LogAction;
import com.backlink.entities.LogSystem.Type;

@Repository
public interface LogSystemRepository extends MongoRepository<LogSystem, String>{	
	
	List<LogSystem> findByImplementer(String implementer);
	
	List<LogSystem> findByTypeOrderByCreateAtDesc(Type type);

	List<LogSystem> findByEventAndTypeAndImplementer(LogAction log, Type type, String implementer);
}
