package com.backend.superme.controller.user;


import com.backend.superme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String token) {
        // 클라이언트에서 전달된 토큰을 이용하여 이메일을 조회하고 사용자 정보를 반환
        String userEmail = userService.emailFromToken(token);

        return ResponseEntity.ok(userEmail);
    }
}
