package com.backend.superme.service.user;

import com.backend.superme.config.user.JwtTokenProvider;
import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.dto.user.UserDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    Boolean duplicateEmail = false;

    @Override
    public boolean checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return duplicateEmail = true;
        } else {
            return duplicateEmail = false;
        }
    }

    @Override
    public void signupUser(UserDto userDto) {
        System.out.println(userDto.toString());
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        if (checkEmail(email)) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        String encryptedPassword = passwordEncoder.encode(password);

        // UserDto / UserEntity로 변환
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(encryptedPassword);
        userEntity.setProfile(userDto.getProfile());
        userEntity.setNickname(userDto.getNickname());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setGender(userDto.getGender());


        userEntity.setRole("USER");

        userRepository.save(userEntity);

    }

    @Override
    public String authenticateUser(UserDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        }

        UserEntity userEntity = userEntityOptional.get();

        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
        String token = jwtTokenProvider.generateToken(userPrincipal);

        return token;

    }
}
