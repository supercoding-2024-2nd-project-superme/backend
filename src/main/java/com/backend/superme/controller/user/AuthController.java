package com.backend.superme.controller.user;


import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "토큰으로 이메일을 확인합니다")
public class AuthController {


    private UserService userService;


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


    @GetMapping("/google/login")
    public String googleLogin() {
        return "redirect:/oauth2/authorization/google";
    }

}
