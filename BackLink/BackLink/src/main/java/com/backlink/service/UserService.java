package com.backlink.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backlink.Message.MessageException;
import com.backlink.beans.CurrentUser;
import com.backlink.entities.LogSystem;
import com.backlink.entities.LogSystem.LogAction;
import com.backlink.entities.LogSystem.Type;
import com.backlink.entities.Mailer;
import com.backlink.entities.Role;
import com.backlink.entities.Role.RoleName;
import com.backlink.entities.User;
import com.backlink.exception.AuthorizationException;
import com.backlink.exception.BadRequestException;
import com.backlink.exception.ResourceNotFoundException;
import com.backlink.payload.reponse.APIResponse;
import com.backlink.payload.reponse.JwtAuthenticationResponse;
import com.backlink.payload.request.AddUserRequest;
import com.backlink.payload.request.LoginRequest;
import com.backlink.payload.request.RecoverRequest;
import com.backlink.payload.request.RoleRequest;
import com.backlink.payload.request.SignUpRequest;
import com.backlink.payload.request.UpdateUserRequest;
import com.backlink.repository.UserRepository;
import com.backlink.security.JwtTokenProvider;
import com.backlink.util.Generate;
import com.backlink.util.Validate;

@Service
public class UserService implements IBaseService<User, String> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LogSystemService logSystemService;

	@Autowired
	private MailService mailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private CurrentUser currentUser;

	@Override
	public User getById(String id) {
		if (currentUser.userHasAuthority(RoleName.ROLE_CUSTOMER) && !currentUser.get().getId().equals(id)) {
			throw new AuthorizationException(MessageException.UNAUTHORIZATION);
		}
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
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

	// ĐĂNG KÝ THÀNH VIÊN
	public ResponseEntity<?> register(SignUpRequest signUpRequest) throws ParseException {
		// KIỂM TRA USERNAME ĐÃ TỒN TẠI HAY CHƯA
		if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
			throw new BadRequestException(String.format(MessageException.EXIST, signUpRequest.getUsername()));
		}

		// KIỂM TRA MAIL ĐÃ TỒN TẠI HAY CHƯA
		if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
			throw new BadRequestException(String.format(MessageException.EXIST, signUpRequest.getEmail()));
		}

		// KIỂM TRA SỐ ĐIỆN THOẠI ĐÃ TỒN TẠI HAY CHƯA
//		if (userRepository.findByPhone(signUpRequest.getPhone()).isPresent()) {
//			throw new BadRequestException(String.format(MessageException.EXIST, signUpRequest.getPhone()));
//		}

		// SET GIÁ TRỊ
		User user = new User();
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(signUpRequest.getPassword());
		user.setEmail(signUpRequest.getEmail());
		//user.setPhone(signUpRequest.getPhone());
		user.setFullname(signUpRequest.getFullname());
		user.setAddress(signUpRequest.getAddress());
		user.setGender(signUpRequest.isGender());
		//user.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(signUpRequest.getBirthday()));
		Set<Role> role = new HashSet<Role>();
		role.add(new Role(RoleName.ROLE_CUSTOMER));
		user.setRoles(role);

		this.saveOne(user);

		// Lưu Log
		logSystemService.saveOne(new LogSystem(user.getUsername(), Type.CUSTOMER, LogAction.CREATE,
				user.getFullname() + " vừa đăng kí thành viên"));

		return new ResponseEntity<Object>(new APIResponse(true, "Tạo tài khoản thành công"), HttpStatus.OK);
	}

	// THÊM THÀNH VIÊN
	public ResponseEntity<?> addUser(AddUserRequest addUserRequest) throws ParseException {
		// KIỂM TRA USERNAME ĐÃ TỒN TẠI HAY CHƯA
		if (userRepository.findByUsername(addUserRequest.getUsername()).isPresent()) {
			throw new BadRequestException(String.format(MessageException.EXIST, addUserRequest.getUsername()));
		}

		// KIỂM TRA MAIL ĐÃ TỒN TẠI HAY CHƯA
		if (userRepository.findByEmail(addUserRequest.getEmail()).isPresent()) {
			throw new BadRequestException(String.format(MessageException.EXIST, addUserRequest.getEmail()));
		}

		// KIỂM TRA SỐ ĐIỆN THOẠI ĐÃ TỒN TẠI HAY CHƯA
		if (userRepository.findByPhone(addUserRequest.getPhone()).isPresent()) {
			throw new BadRequestException(String.format(MessageException.EXIST, addUserRequest.getPhone()));
		}

		// SET GIÁ TRỊ
		User user = new User();
		user.setUsername(addUserRequest.getUsername());
		user.setPassword(addUserRequest.getPassword());
		user.setEmail(addUserRequest.getEmail());
		user.setPhone(addUserRequest.getPhone());
		user.setFullname(addUserRequest.getFullname());
		user.setAddress(addUserRequest.getAddress());
		user.setGender(addUserRequest.isGender());
		user.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(addUserRequest.getBirthday()));
		Set<Role> role = new HashSet<Role>();
		for (RoleRequest rq : addUserRequest.getRoles()) {
			role.add(new Role(rq.getName()));
		}
		user.setRoles(role);

		this.saveOne(user);

		// Lưu Log
		logSystemService.saveOne(new LogSystem(currentUser.get().getUsername(), Type.ADMIN, LogAction.CREATE,
				currentUser.get().getUsername() + " vừa tạo tài khoản " + user.getUsername() + " thành công"));

		return new ResponseEntity<Object>(new APIResponse(true, "Tạo tài khoản thành công"), HttpStatus.OK);
	}

	public ResponseEntity<?> updateUser(UpdateUserRequest updateUserRequest) throws ParseException {
		Optional<User> userOpt = userRepository.findById(updateUserRequest.getId());
		// KIỂM TRA ID Có tồn tại hay không
		if (!userRepository.findById(updateUserRequest.getId()).isPresent()) {
			throw new BadRequestException(String.format(MessageException.USER_NOT_FOUND_ID, updateUserRequest.getId()));
		}		

		// SET GIÁ TRỊ
		User user = userOpt.get();
		user.setFullname(updateUserRequest.getFullname());
		user.setAddress(updateUserRequest.getAddress());
		user.setGender(updateUserRequest.isGender());
		user.setBirthday(updateUserRequest.getBirthday());
		Set<Role> role = new HashSet<Role>();
		for (RoleRequest rq : updateUserRequest.getRoles()) {
			role.add(new Role(rq.getName()));
		}
		user.setRoles(role);

		this.updateOne(user);

		// Lưu Log
		logSystemService.saveOne(new LogSystem(currentUser.get().getUsername(), Type.ADMIN, LogAction.UPDATE,
				currentUser.get().getUsername() + " vừa cập nhật tài khoản " + user.getUsername() + " thành công"));

		return new ResponseEntity<Object>(new APIResponse(true, "Cập nhật tài khoản thành công"), HttpStatus.OK);
	}

	public ResponseEntity<?> authenticate(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		// Lưu Log
		logSystemService.saveOne(new LogSystem(loginRequest.getUsernameOrEmail(), Type.CUSTOMER, LogAction.LOGIN,
				loginRequest.getUsernameOrEmail() + " vừa đăng nhập"));

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	public ResponseEntity<?> recover(RecoverRequest recoverRequest) {
		String sendTo = recoverRequest.getEmail();
		if (sendTo == null || !Validate.checkEmail(sendTo)) {
			throw new BadRequestException(MessageException.INCORRECT_SYNTAX);
		}
		// Kiểm tra email
		User user = userRepository.findByEmail(sendTo).orElseThrow(() -> new UsernameNotFoundException(
				String.format(MessageException.CUSTOM_NOT_FOUND, "Email", "", sendTo)));

		// Tạo và cập nhật mật khẩu mới
		String newPass = Generate.getAlphaNumericString(8);
		user.setPassword(newPass);
		this.updateOne(user);

		// Lưu Log
		logSystemService.saveOne(
				new LogSystem(sendTo, Type.CUSTOMER, LogAction.RECOVER, sendTo + " yêu cầu khôi phục mật khẩu"));

		// Gửi Mail
		mailService.sendEmailRecover(new Mailer(new String[] { sendTo }, "Mật Khẩu Khôi Phục Tại Backlink", null),
				newPass);

		return ResponseEntity.ok(new APIResponse(true, sendTo));
	}

	public User me() {
		return this.getById(currentUser.get().getId());
	}
}
