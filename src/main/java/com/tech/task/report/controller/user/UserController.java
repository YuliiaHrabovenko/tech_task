package com.tech.task.report.controller.user;

import com.tech.task.report.entity.user.JwtToken;
import com.tech.task.report.entity.user.UserDto;
import com.tech.task.report.security.CustomAuthenticationManager;
import com.tech.task.report.security.JwtService;
import com.tech.task.report.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomAuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public JwtToken register(@RequestBody UserDto userDto) {
        userService.save(userDto);
        String token = loginAndGetToken(userDto);
        return new JwtToken(token);
    }

    @PostMapping("/login")
    public JwtToken loginUser(@RequestBody UserDto userLoginDto) {
        String token = loginAndGetToken(userLoginDto);
        return new JwtToken(token);
    }

    private String loginAndGetToken(UserDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
        );
        return jwtService.generateToken(authentication);
    }
}
