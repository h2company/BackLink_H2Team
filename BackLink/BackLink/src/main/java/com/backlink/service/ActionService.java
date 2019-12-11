package com.backlink.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backlink.beans.CurrentUser;
import com.backlink.entities.Action;
import com.backlink.entities.LogSystem;
import com.backlink.entities.LogSystem.LogAction;
import com.backlink.entities.LogSystem.Type;
import com.backlink.payload.request.ActionRequest;
import com.backlink.repository.ActionRepository;

@Service
public class ActionService implements IBaseService<Action, String> {

	@Autowired
	private LogSystemService logSystemService;

	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private CurrentUser currentUser;

	@Override
	public Action getById(String id) {
//		Action action = actionRepository.findById(id).orElseThrow(() -> {
//			throw new ResourceNotFoundException("Action", "id", id);
//		});
		return null;
	}

	@Override
	public List<Action> findAll() {
		return actionRepository.findAll();
	}
	
	public List<Action> findAll(int page) {
		Pageable sortedByPriceDesc =  PageRequest.of(page, 5, Sort.by("point").descending());
		return actionRepository.findByEndTimeGreaterThanEqual(new Date().getTime(), sortedByPriceDesc).getContent();
	}
	
	public List<Action> findByUsername() {
		return actionRepository.findByUsername(currentUser.get().getUsername(), Sort.by(Direction.DESC, "createAt"));
	}

	@Override
	public Action saveOne(Action entity) {
		return actionRepository.save(entity);
	}

	@Override
	public Action updateOne(Action entity) {
		Action action = null;
//		try {
//			action = actionRepository.save(entity);
//		} catch (Exception e) {
//			throw new AppException(e.getMessage());
//		}
		return action;
	}

	@Override
	public List<Action> updateMany(List<Action> list) {
		List<Action> action = null;
//		try {
//			action = actionRepository.saveAll(list);
//		} catch (Exception e) {
//			throw new AppException(e.getMessage());
//		}

		return action;
	}

	@Override
	public boolean deleteOne(String id) {
//		try {
//			actionRepository.deleteById(id);
//			return true;
//		} catch (Exception e) {
//			throw new AppException(e.getMessage());
//		}
		return false;
	}

	@Override
	public boolean deleteMany(String[] ids) {
//		try {
//			for (String id : ids) {
//				this.deleteOne(id);
//			}
//			return true;
//		} catch (Exception e) {
//			throw new AppException(e.getMessage());
//		}
		return false;
	}

	public ResponseEntity<?> saveAction(ActionRequest actionRequest, HttpServletRequest request) {
		Action action = new Action();
		action.setUserAgent(actionRequest.getUserAgent());
		action.seturlAction(actionRequest.getUrlAction());
		action.setKeywords(actionRequest.getKeywords());
		action.setSearchEngine(actionRequest.getSearchEngine());
		action.setPoint(Integer.parseInt(actionRequest.getPoint().toString()));
		action.setUsername(currentUser.get().getUsername());
		action.setBlockPixel(actionRequest.isBlockPixel());
		action.setFilterVA(actionRequest.isFilterVA());
		action.setAccessHistory(new String[0]);
		action.setEndTime(actionRequest.getEndTime());
		action.setBeginTime(actionRequest.getBeginTime());
		
		// Lưu action vào cơ sở dữ liệu
		Action ac = this.saveOne(action);

		// Lưu Log
		logSystemService.saveOne(new LogSystem(action.getUsername(), Type.CUSTOMER, LogAction.CREATE,
				action.getUsername() + " Vừa tạo mới 1 Action: " + ac.getId()));
		return new ResponseEntity<Object>(ac, HttpStatus.OK);		
		
	}
	
	public ResponseEntity<?> findById(String id) {
		return new ResponseEntity<Object>(actionRepository.findById(id).get(), HttpStatus.OK);
	}

}
