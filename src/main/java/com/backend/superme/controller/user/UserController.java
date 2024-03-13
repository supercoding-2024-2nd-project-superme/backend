package com.backend.superme.controller.user;


import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDto userDto){
        userService.signupUser(userDto);
        return "ok";
    }
}

