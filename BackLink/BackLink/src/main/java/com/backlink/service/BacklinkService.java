package com.backlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backlink.entities.Backlink;
import com.backlink.repository.BacklinkRepository;

@Service
public class BacklinkService implements IBaseService<Backlink, String> {

	@Autowired
	private BacklinkRepository backlinkRepository;

	@Override
	public Backlink getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Backlink> findAll() {
		return backlinkRepository.findAll();
	}

	@Override
	public Backlink saveOne(Backlink entity) {
		return backlinkRepository.insert(entity);
	}

	@Override
	public Backlink updateOne(Backlink entity) {
		return backlinkRepository.save(entity);
	}

	@Override
	public List<Backlink> updateMany(List<Backlink> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteOne(String id) {
		try {
			backlinkRepository.deleteById(id);;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteMany(String[] ids) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
