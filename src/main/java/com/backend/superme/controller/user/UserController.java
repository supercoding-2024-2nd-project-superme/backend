package com.backend.superme.controller.user;


import com.backend.superme.config.global.ErrorCode;
import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.user.TokenBlacklistService;
import com.backend.superme.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "로그인, 회원가입 등 유저관련 api입니다",description = "사용자가 회원가입, 로그인을 할 수 있습니다.")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @GetMapping("/")
    @Operation(summary = "index.html 로 이동하는 경로입니다.",description = "메인경로입니다.")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok().body("인덱스 페이지");
    }
    @GetMapping("/user/login")
    @Operation(summary = "index.html 로 이동하는 경로입니다.",description = "메인경로입니다.")
    public ResponseEntity<?> showLoginPage() {
        return ResponseEntity.ok().body("로그인 페이지");
    }

    @PostMapping("/user/login")
    @Operation(summary = "로그인 API", description = "사용자가 로그인하는 api 입니다.")
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

    @GetMapping("/user/signup")
    @Operation(summary = "회원가입 페이지로 이동합니다.", description = "회원가입 페이지로 이동합니다")
    public ResponseEntity<?> showSignupPage() {
        return ResponseEntity.ok().body("회원가입 페이지");
    }

    @GetMapping("/user/signup/check/{email}")
    @Operation(summary = "이메일 중복을 체크합니다.", description = "중복회원을 확인합니다.")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
        boolean result = userService.checkEmail(email);
        if (result) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ErrorCode.DUPLICATED_EMAIL.getMessage());
            return ResponseEntity.status(ErrorCode.DUPLICATED_EMAIL.getStatus()).body(errorResponse);
        } else {
            return ResponseEntity.ok().body("사용 가능한 이메일입니다.");
        }
    }
    @PostMapping("/user/signup")
    @Operation(summary = "회원가입 API", description = "사용자가 회원가입하는 API 입니다.")
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

    @PostMapping("/user/logout")
    @Operation(summary = "로그아웃 API", description = "사용자가 로그아웃하는 API 입니다.")
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

    @DeleteMapping("/user/withdraw")
    @Operation(summary = "회원탈퇴 API", description = "사용자가 탈퇴하는 API 입니다.")
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




