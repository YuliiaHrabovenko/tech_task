package com.tech.task.report.security;

import com.tech.task.report.exceptions.AuthException;
import com.tech.task.report.exceptions.ExceptionUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationManager(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null) {
            throw new AuthException(ExceptionUtil.USER_NOT_FOUND_EXCEPTION);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException(ExceptionUtil.INVALID_CREDENTIALS_EXCEPTION);
        }

        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
    }
}
