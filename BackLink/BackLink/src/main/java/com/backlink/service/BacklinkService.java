package com.backlink.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backlink.beans.CurrentUser;
import com.backlink.entities.AccessHistory;
import com.backlink.entities.Backlink;
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
		Backlink backlink = null;
		if(backlinkRepository.findById(id).isPresent()) {
			backlink = backlinkRepository.findById(id).get();
		}
		return backlink;
	}

	@Override
	public List<Backlink> findAll() {
		return backlinkRepository.findAll();
	}

	public List<Backlink> findAll(int page) {
//	Pageable sortedByPriceDesc =  PageRequest.of(page, 2, Sort.by("point").descending());
//backlinkRepository.findAll(sortedByPriceDesc);

		Pageable sortedByPriceDesc = PageRequest.of(page, 5, Sort.by("point").descending());
		return backlinkRepository.findByEndTimeGreaterThanEqual(new Date().getTime(), sortedByPriceDesc).getContent();
	}

	@Override
	public Backlink saveOne(Backlink entity) {
		return backlinkRepository.save(entity);
	}

	@Override
	public Backlink updateOne(Backlink entity) {
//		Backlink backlink = null;
//		try {
//			backlink = backlinkRepository.save(entity);
//		} catch (Exception e) {
//			throw new AppException(e.getMessage());
//		}
		return null;
	}

	@Override
	public List<Backlink> updateMany(List<Backlink> list) {
//		List<Backlink> bllist = null;
//		try {
//			bllist = backlinkRepository.saveAll(list);
//		} catch (Exception e) {
//			throw new AppException(e.getMessage());
//		}
		return null;
	}

	@Override
	public boolean deleteOne(String id) {
		// TODO Auto-generated method stub
//		try {
//			backlinkRepository.deleteById(id);
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
//				backlinkRepository.deleteById(id);
//			}
//		} catch (Exception e) {
//			throw new AppException(e.getMessage());
//		}
		return false;
	}

	public List<Backlink> findAllByUser() {
		// TODO Auto-generated method stub
		return backlinkRepository.findAllByUsername(currentUser.get().getUsername());
	}

	public ResponseEntity<?> saveBacklink(BacklinkRequest backlinkRequest) {
		User user = userRepository.findByUsername(currentUser.get().getUsername()).get();

		if (user.getPoint() < backlinkRequest.getPoint()) {
			return new ResponseEntity<Object>(
					new ApiException(HttpStatus.BAD_REQUEST,
							"Bạn không đủ điểm quy đổi. Vui lòng quy đổi thêm điểm bằng cách nạp thêm tiền."),
					HttpStatus.BAD_REQUEST);
		} else if (backlinkRequest.getPoint() / backlinkRequest.getLimit() < 2) {
			return new ResponseEntity<Object>(
					new ApiException(HttpStatus.BAD_REQUEST, "Điểm quy đổi quá thấp so với số lần quy đổi."),
					HttpStatus.BAD_REQUEST);
		} else {
			// Tru diem vào tai khoan
			user.setPoint(user.getPoint() - backlinkRequest.getPoint());
			userRepository.save(user);

			Backlink backlink = new Backlink();
			backlink.setUsername(currentUser.get().getUsername());
			backlink.setUrlBacklink(backlinkRequest.getUrlBacklink());
			backlink.setPoint(backlinkRequest.getPoint());
			backlink.setLimit(backlinkRequest.getLimit());
			backlink.setLimit_active(backlinkRequest.getLimit());
			backlink.setFilterVA(backlinkRequest.isFilterVA());
			backlink.setSaveVA(backlinkRequest.isSaveVA());
			backlink.setBeginTime(backlinkRequest.getBeginTime());
			backlink.setEndTime(backlinkRequest.getEndTime());
			Backlink bl = this.saveOne(backlink);

			// Lưu Log
//			logSystemService.saveOne(new LogSystem(bl.getUsername(), Type.CUSTOMER, LogAction.CREATE,
//					backlink.getUsername() + " Vừa tạo mới 1 Action: " + bl.getId()));
			return new ResponseEntity<Object>(bl, HttpStatus.OK);
		}
	}

	public ResponseEntity<?> checkverifyACPBacklink(VerifyRequest verifyRequest) {

		List<AccessHistory> accLi = accessHistoryRepository
				.findByTheAccessHistoryUrlAgent(verifyRequest.getUrlAgent().trim());

//		for(AccessHistory accItem : accLi) {
//			if(accItem.getEvents().get(0).getSiteId().equals(verifyRequest.getId())) {
//				return new ResponseEntity<Object>(new ApiException(HttpStatus.BAD_REQUEST, "Liên kết này đã được xác thực trước đó !"), HttpStatus.BAD_REQUEST); 
//			}
//		}
		if (accLi != null && accLi.size() > 0)
			return new ResponseEntity<Object>(
					new ApiException(HttpStatus.BAD_REQUEST, "Liên kết này đã được xác thực trước đó !"),
					HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(new ApiException(HttpStatus.OK, "Kiểm tra liên kết backlink thành công !"),
				HttpStatus.OK);
	}

	public ResponseEntity<?> verifyACPBacklink(VerifyRequest verifyRequest) {

		if (!verifyRequest.getEvents().get(0).getSiteId().equals(verifyRequest.getBacklink_id())) {
			accessHistoryRepository.deleteById(verifyRequest.getId());
			return new ResponseEntity<Object>(new ApiException(HttpStatus.BAD_REQUEST,
					"Xác thực thất bại ! Bạn chưa nhúng mã script xác thực vào web !"), HttpStatus.BAD_REQUEST);
		}

		Backlink backlink = backlinkRepository.findById(verifyRequest.getEvents().get(0).getSiteId()).get();

		int point = (int) (backlink.getPoint() / backlink.getLimit());

		// Cap nhat so diem quy doi con lai
		backlink.setLimit_active(backlink.getLimit_active() - 1);
		backlink.setPoint(backlink.getPoint() - point);
		backlinkRepository.save(backlink);

		// Cong diem cho user lien ket backlink
		point = (int) (backlink.getPoint() / backlink.getLimit() * 0.9);
		User user = userRepository.findByUsername(currentUser.get().getUsername()).get();
		user.setLockpoint(user.getLockpoint() + point);

		userRepository.save(user);
		return new ResponseEntity<Object>(new ApiException(HttpStatus.OK, "Xác thực liên kết backlink thành công !"),
				HttpStatus.OK);
	}
}
