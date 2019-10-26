package com.backlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backlink.entities.AccessHistory;
import com.backlink.repository.AccessHistoryRepository;

@Service
public class AccessHistoryService implements IBaseService<AccessHistory, String> {

	@Autowired
	private AccessHistoryRepository AHRepository;
	
	@Override
	public AccessHistory getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccessHistory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessHistory saveOne(AccessHistory entity) {
		return AHRepository.save(entity);
	}

	@Override
	public AccessHistory updateOne(AccessHistory entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccessHistory> updateMany(List<AccessHistory> list) {
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
