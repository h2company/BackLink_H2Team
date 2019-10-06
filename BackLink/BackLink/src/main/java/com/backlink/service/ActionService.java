package com.backlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backlink.entities.Action;
import com.backlink.exception.AppException;
import com.backlink.exception.BadRequestException;
import com.backlink.exception.ResourceNotFoundException;
import com.backlink.repository.ActionRepository;

@Service
public class ActionService  implements IBaseService<Action, String>{
	
	@Autowired
	private ActionRepository actionRepository;

	@Override
	public Action getById(String id) {
		Action action = actionRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("Action", "id", id);
		});
		return action;
	}

	@Override
	public List<Action> findAll() {		
		return actionRepository.findAll();
	}

	@Override
	public Action saveOne(Action entity) {
		Action action = null;
		try {

			action = actionRepository.save(entity);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		return action;
	}

	@Override
	public Action updateOne(Action entity) {
		Action action = null;
		try {

			action = actionRepository.save(entity);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		return action;
	}

	@Override
	public List<Action> updateMany(List<Action> list) {	
		List<Action> action = null;
		try {
			action= actionRepository.saveAll(list);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		
		return action;
	}

	@Override
	public boolean deleteOne(String id) {
		try {
			actionRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	@Override
	public boolean deleteMany(String[] ids) {
		try {
			for(String id : ids) {
				this.deleteOne(id);
			}
			return true;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

}
