package com.backend.superme.service.user;

import com.backend.superme.dto.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void signupUser(UserDto userDto);


}
