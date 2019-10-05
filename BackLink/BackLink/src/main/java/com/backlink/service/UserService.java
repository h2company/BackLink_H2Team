package com.backlink.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backlink.entities.Role;
import com.backlink.entities.Role.RoleName;
import com.backlink.entities.User;
import com.backlink.exception.AppException;
import com.backlink.payload.reponse.APIResponse;
import com.backlink.payload.reponse.JwtAuthenticationResponse;
import com.backlink.payload.request.LoginRequest;
import com.backlink.payload.request.SignUpRequest;
import com.backlink.repository.RoleRepository;
import com.backlink.repository.UserRepository;
import com.backlink.security.JwtTokenProvider;

@Service
public class UserService implements IBaseService<User, String>{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

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
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<APIResponse>(new APIResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<APIResponse>(new APIResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getFullname(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new APIResponse(true, "User registered successfully"));
    }
    
    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

}
