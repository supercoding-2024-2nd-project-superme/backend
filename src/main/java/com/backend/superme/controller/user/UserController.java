package com.backend.superme.controller.user;


import com.backend.superme.config.global.ErrorCode;
import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.user.TokenBlacklistService;
import com.backend.superme.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        String token = userService.authenticateUser(userDto);
        if (token != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("message", "로그인 성공");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ErrorCode.CHECK_LOGIN_ID_OR_PASSWORD.getMessage());
            return ResponseEntity.status(ErrorCode.CHECK_LOGIN_ID_OR_PASSWORD.getStatus()).body(errorResponse);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        try {
            userService.signupUser(userDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "회원가입 성공");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ErrorCode.DUPLICATED_EMAIL.getMessage());
            return ResponseEntity.status(ErrorCode.DUPLICATED_EMAIL.getStatus()).body(errorResponse);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
            return ResponseEntity.ok().body("로그아웃 성공");
        }
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errorMessage", ErrorCode.USER_AUTH_ERROR.getMessage());
        return ResponseEntity.status(ErrorCode.USER_AUTH_ERROR.getStatus()).body(errorResponse);
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<?> withdrawUser(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (authToken == null || !authToken.startsWith("Bearer ")) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ErrorCode.USER_AUTH_ERROR.getMessage());
            return ResponseEntity.status(ErrorCode.USER_AUTH_ERROR.getStatus()).body(errorResponse);
        }
        authToken = authToken.substring(7);

        String userEmail = userService.emailFromToken(authToken);

        try {
            userService.withdrawUser(userEmail);
            return ResponseEntity.ok().body("탈퇴 성공");
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ErrorCode.SERVER_ERROR.getMessage());
            return ResponseEntity.status(ErrorCode.SERVER_ERROR.getStatus()).body(errorResponse);
        }
    }
}




