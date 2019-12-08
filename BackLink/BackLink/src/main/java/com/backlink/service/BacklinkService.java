package com.backlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backlink.beans.CurrentUser;
import com.backlink.entities.AccessHistory;
import com.backlink.entities.Backlink;
import com.backlink.entities.LogSystem;
import com.backlink.entities.LogSystem.LogAction;
import com.backlink.entities.LogSystem.Type;
import com.backlink.entities.User;
import com.backlink.exception.ApiException;
import com.backlink.exception.AppException;
import com.backlink.exception.ResourceNotFoundException;
import com.backlink.payload.request.BacklinkRequest;
import com.backlink.payload.request.VerifyRequest;
import com.backlink.repository.AccessHistoryRepository;
import com.backlink.repository.BacklinkRepostitory;
import com.backlink.repository.UserRepository;

@Service
public class BacklinkService implements IBaseService<Backlink, String> {

	private LogSystemService logSystemService;
	
	@Autowired
	private AccessHistoryRepository accessHistoryRepository;
	
	@Autowired
	private BacklinkRepostitory backlinkRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CurrentUser currentUser;
	
	@Override
	public Backlink getById(String id) {
		// TODO Auto-generated method stub
		Backlink backlink = backlinkRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("Backlink", "id", id);
		});
		return backlink;
	}

	@Override
	public List<Backlink> findAll() {
		return backlinkRepository.findAll();
	}

	@Override
	public Backlink saveOne(Backlink entity) {
		return backlinkRepository.save(entity);
	}

	@Override
	public Backlink updateOne(Backlink entity) {
		// TODO Auto-generated method stub
		Backlink backlink = null;
		try {
			backlink = backlinkRepository.save(entity);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Backlink> updateMany(List<Backlink> list) {
		List<Backlink> bllist = null;
		try {
			bllist = backlinkRepository.saveAll(list);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		return bllist;
	}

	@Override
	public boolean deleteOne(String id) {
		// TODO Auto-generated method stub
		try {
			backlinkRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	@Override
	public boolean deleteMany(String[] ids) {
		try {
			for(String id: ids) {
				backlinkRepository.deleteById(id);
			}
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		return false;
	}
	
	public List<Backlink> findAllByUser() {
		// TODO Auto-generated method stub
		return backlinkRepository.findAllByUsername(currentUser.get().getUsername());
	}
	
	public ResponseEntity<?> saveBacklink(BacklinkRequest backlinkRequest){
		User user = userRepository.findByUsername(currentUser.get().getUsername()).get();
		
		if(user.getPoint() < backlinkRequest.getPoint()) {
			return new ResponseEntity<Object>(new ApiException(HttpStatus.BAD_REQUEST, "Bạn không đủ điểm quy đổi."), HttpStatus.BAD_REQUEST); 
		} else {
			//Tru diem vào tai khoan
			user.setPoint(user.getPoint()-backlinkRequest.getPoint());
			userRepository.save(user);
			
			Backlink backlink = new Backlink();
			backlink.setUsername(currentUser.get().getUsername());
			backlink.setUrlBacklink(backlinkRequest.getUrlBacklink());
			backlink.setPoint(backlinkRequest.getPoint());
			backlink.setLimit(backlinkRequest.getLimit());
			backlink.setFilterVA(backlinkRequest.isFilterVA());
			backlink.setSaveVA(backlinkRequest.isSaveVA());
			backlink.setBeginTime(backlinkRequest.getBeginTime());
			backlink.setEndTime(backlinkRequest.getEndTime());
			Backlink bl = this.saveOne(backlink);
			
			// Lưu Log
			logSystemService.saveOne(new LogSystem(bl.getUsername(), Type.CUSTOMER, LogAction.CREATE,
					backlink.getUsername() + " Vừa tạo mới 1 Action: " + bl.getId()));
			return new ResponseEntity<Object>(bl, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> checkverifyACPBacklink(VerifyRequest verifyRequest){
		
		List<AccessHistory>  accLi = accessHistoryRepository.findByTheAccessHistoryUrlAgent(verifyRequest.getUrlAgent());
		
		for(AccessHistory accItem : accLi) {
			if(accItem.getEvents().get(0).getSiteId().equals(verifyRequest.getId())) {
				return new ResponseEntity<Object>(new ApiException(HttpStatus.BAD_REQUEST, "Liên kết này đã được xác thực trước đó !"), HttpStatus.BAD_REQUEST); 
			}
		}
		return new ResponseEntity<Object>(new ApiException(HttpStatus.OK, "Kiểm tra liên kết backlink thành công !"), HttpStatus.OK); 
	}
	
	public ResponseEntity<?> verifyACPBacklink(VerifyRequest verifyRequest){
		
		Backlink backlink = backlinkRepository.findById(verifyRequest.getEvents().get(0).getSiteId()).get();
		
		int point = (int)(backlink.getPoint()/backlink.getLimit());
		
		// Cap nhat so diem quy doi con lai
		backlink.setLimit(backlink.getLimit()-1);
		backlink.setPoint(backlink.getPoint()-point);
		backlinkRepository.save(backlink);
		
		//Cong diem cho user lien ket backlink
		point = (int)(backlink.getPoint()/backlink.getLimit() * 0.9);
		User user = userRepository.findByUsername(currentUser.get().getUsername()).get();
		user.setLockpoint(user.getLockpoint()+point);
		
		userRepository.save(user);
		return new ResponseEntity<Object>(new ApiException(HttpStatus.OK, "Xác thực liên kết backlink thành công !"), HttpStatus.OK); 
	}
}
