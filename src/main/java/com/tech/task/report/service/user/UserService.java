package com.tech.task.report.service.user;

import com.tech.task.report.entity.user.UserDto;
import com.tech.task.report.entity.user.UserEntity;
import com.tech.task.report.entity.user.UserRole;
import com.tech.task.report.exceptions.ExceptionUtil;
import com.tech.task.report.exceptions.ValidationException;
import com.tech.task.report.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(UserDto userDto) {
        Optional<UserEntity> user = findByEmail(userDto.getEmail());
        if (user.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            UserEntity userEntity = new UserEntity(userDto.getEmail(), encodedPassword);
            userEntity.setRole(UserRole.CLIENT);
            userRepository.save(userEntity);
        } else {
            throw new ValidationException(ExceptionUtil.USER_ALREADY_EXISTS_EXCEPTION);
        }
    }
}
