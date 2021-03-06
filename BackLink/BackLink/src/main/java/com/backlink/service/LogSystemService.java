package com.backlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backlink.beans.CurrentUser;
import com.backlink.entities.LogSystem;
import com.backlink.entities.LogSystem.LogAction;
import com.backlink.entities.LogSystem.Type;
import com.backlink.repository.LogSystemRepository;

@Service
public class LogSystemService implements IBaseService<LogSystem, String>{
	
	@Autowired
	private LogSystemRepository logSystemRepository;

	@Override
	public LogSystem getById(String id) {
		return null;
	}
	
	@Autowired
	private CurrentUser currentUser;

	@Override
	public List<LogSystem> findAll() {
		return logSystemRepository.findAll(new Sort(Sort.Direction.DESC, "createAt"));
	}	
	
	public List<LogSystem> findByEventAndTypeAndImplementer() {
		 List<LogSystem> list = logSystemRepository.findByEventAndTypeAndImplementer(LogAction.ADD_POINT, Type.CUSTOMER, currentUser.get().getEmail());
		 list.forEach(e -> {
			if(e.getImplementer().equals(currentUser.get().getEmail())) {
				e.setContent(e.getContent().replace(currentUser.get().getEmail(), "Bạn"));
			}
		});		
		 return list;
	}
	
	public List<LogSystem> findAll(Type type) {
		return logSystemRepository.findByTypeOrderByCreateAtDesc(type);
	}

	@Override
	public LogSystem saveOne(LogSystem entity) {
		return logSystemRepository.save(entity);
	}

	@Override
	public LogSystem updateOne(LogSystem entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogSystem> updateMany(List<LogSystem> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteOne(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMany(String[] ids) {
		// TODO Auto-generated method stub
		return false;
	}

}
