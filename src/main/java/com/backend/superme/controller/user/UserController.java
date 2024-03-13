package com.backend.superme.controller.user;


import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String signup(@RequestBody UserDto userDto) {
        userService.signupUser(userDto);
        return "ok";
    }
}

