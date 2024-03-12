package com.backend.superme.service.user;

import com.backend.superme.dto.login.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    void signupUser(LoginDto loginDto);


}
