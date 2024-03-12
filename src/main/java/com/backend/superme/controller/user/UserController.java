package com.backend.superme.controller.user;


import com.backend.superme.dto.login.LoginDto;
import com.backend.superme.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public String signup(LoginDto loginDto){
        loginService.signupUser(loginDto);
        return "ok";

    }
}

