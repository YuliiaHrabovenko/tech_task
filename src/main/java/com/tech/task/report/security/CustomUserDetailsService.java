package com.tech.task.report.security;

import com.tech.task.report.entity.user.UserEntity;
import com.tech.task.report.exceptions.AuthException;
import com.tech.task.report.exceptions.ExceptionUtil;
import com.tech.task.report.service.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserEntity> userEntity = userService.findByEmail(email);

        return userEntity.map(u -> {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(u.getRole().toString()));
            return new User(u.getEmail(), u.getPassword(), authorities);
        }).orElseThrow(() -> new AuthException(ExceptionUtil.USER_NOT_FOUND_EXCEPTION));
    }
}
