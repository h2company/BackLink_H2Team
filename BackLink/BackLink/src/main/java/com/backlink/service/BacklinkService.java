package com.backlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backlink.beans.CurrentUser;
import com.backlink.entities.Backlink;
import com.backlink.entities.LogSystem;
import com.backlink.entities.LogSystem.LogAction;
import com.backlink.entities.LogSystem.Type;
import com.backlink.exception.AppException;
import com.backlink.exception.ResourceNotFoundException;
import com.backlink.payload.request.BacklinkRequest;
import com.backlink.repository.BacklinkRepostitory;

@Service
public class BacklinkService implements IBaseService<Backlink, String> {

	private LogSystemService logSystemService;
	
	@Autowired
	private BacklinkRepostitory backlinkRepository;

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
	
	public ResponseEntity<?> saveBacklink(BacklinkRequest backlinkRequest){
		Backlink backlink = new Backlink();
		backlink.setUsername(currentUser.get().getUsername());
		backlink.setUrlBacklink(backlinkRequest.getUrlBacklink());
		backlink.setPoint(backlinkRequest.getPoint());
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
