package com.backlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backlink.entities.User;
import com.backlink.repository.UserRepository;

@Service
public class UserService implements IBaseService<User, String>{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getById(String id) {		
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> findAll() {		
		return userRepository.findAll();
	}

	@Override
	public User saveOne(User entity) {		
		return userRepository.insert(entity);
	}

	@Override
	public User updateOne(User entity) {		
		return userRepository.save(entity);
	}

	@Override
	public List<User> updateMany(List<User> list) {		
		return null;
	}

	@Override
	public boolean deleteOne(String id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteMany(String[] ids) {		
		return false;
	}

}
