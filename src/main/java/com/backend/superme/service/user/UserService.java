package com.backend.superme.service.user;

import com.backend.superme.dto.user.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void signupUser(UserDto userDto);

    boolean checkEmail(String email);

    String authenticateUser(UserDto userDto);


}
