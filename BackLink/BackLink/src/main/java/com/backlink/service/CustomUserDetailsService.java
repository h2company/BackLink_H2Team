package com.backlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backlink.Message.MessageException;
import com.backlink.entities.User;
import com.backlink.entities.UserPrincipal;
import com.backlink.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Đăng nhập với Username hoặc Email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> 
                        new UsernameNotFoundException(String.format(MessageException.USERNAME_NOT_FOUND, usernameOrEmail))
        );
        return UserPrincipal.create(user);
    }

    // Method được sử dung bởi JwtAuthencationFilter
    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException(String.format(MessageException.USER_NOT_FOUND_ID, id))
        );
        return UserPrincipal.create(user);
    }

}
