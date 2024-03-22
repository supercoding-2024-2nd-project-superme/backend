package com.backend.superme.controller.user;


import com.backend.superme.config.user.JwtTokenProvider;
import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "토큰으로 이메일을 확인합니다")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    private UserPrincipal userPrincipal;


    @GetMapping("/api/user")
    @Operation(summary = "토큰으로 유저 이메일을 추출합니다")
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String token) {
        // 클라이언트에서 전달된 토큰을 이용하여 이메일을 조회하고 사용자 정보를 반환
        String userEmail = userService.emailFromToken(token);

        return ResponseEntity.ok(userEmail);
    }

    @GetMapping("/get-current-member")
    public String getCurrentMember() {
        return userPrincipal.getUsername();
    }



//    @GetMapping("/login/google")
//    public ResponseEntity<String> getUser(@AuthenticationPrincipal OAuth2User oAuth2User){
//
////        return ResponseEntity.ok("welcome to my page" + oAuth2User.getName());
//        return ResponseEntity.ok("welcome to my page" + oAuth2User.getAttributes());
//    }

    // 백엔드에서 토큰 반환을 위한 엔드포인트 구현 예시


}
