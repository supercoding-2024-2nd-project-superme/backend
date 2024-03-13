package com.backend.superme.service.user;

import com.backend.superme.dto.user.UserDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signupUser(UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        // 이메일 중복 체크 로직 추가
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        String encryptedPassword = passwordEncoder.encode(password);

        // UserDto / UserEntity로 변환
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(encryptedPassword);
        userEntity.setRole("USER");

        userRepository.save(userEntity);
    }

}
