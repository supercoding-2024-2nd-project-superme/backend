package com.backend.superme.service.user;

import com.backend.superme.dto.login.LoginDto;
import com.backend.superme.entity.user.LoginEntity;
import com.backend.superme.repository.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void signupUser(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        // 이메일 중복 체크 로직 추가
        if(loginRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        // LoginDto를 LoginEntity로 변환
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setEmail(email);
        loginEntity.setPassword(encryptedPassword);

        loginRepository.save(loginEntity);
    }

}
