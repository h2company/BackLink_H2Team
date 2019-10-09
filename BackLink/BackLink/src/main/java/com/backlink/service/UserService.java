package com.backlink.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backlink.Message.MessageException;
import com.backlink.entities.LogSystem;
import com.backlink.entities.LogSystem.LogAction;
import com.backlink.entities.LogSystem.Type;
import com.backlink.entities.Mailer;
import com.backlink.entities.Role;
import com.backlink.entities.Role.RoleName;
import com.backlink.entities.User;
import com.backlink.exception.AppException;
import com.backlink.exception.BadRequestException;
import com.backlink.payload.reponse.APIResponse;
import com.backlink.payload.reponse.JwtAuthenticationResponse;
import com.backlink.payload.request.LoginRequest;
import com.backlink.payload.request.RecoverRequest;
import com.backlink.payload.request.SignUpRequest;
import com.backlink.repository.RoleRepository;
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
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

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

	public ResponseEntity<?> register(SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<APIResponse>(new APIResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<APIResponse>(new APIResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getFullname(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new APIResponse(true, "User registered successfully"));
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

}
