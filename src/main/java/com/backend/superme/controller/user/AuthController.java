package com.backend.superme.controller.user;


import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private UserService userService;


    private UserPrincipal userPrincipal;

    @GetMapping("/api/user")
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String token) {
        // 클라이언트에서 전달된 토큰을 이용하여 이메일을 조회하고 사용자 정보를 반환
        String userEmail = userService.emailFromToken(token);

        return ResponseEntity.ok(userEmail);
    }

    @GetMapping("/get-current-member")
    public String getCurrentMember() {
        return userPrincipal.getUsername();
    }


}
