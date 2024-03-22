package com.backend.superme.controller.user;

import com.backend.superme.config.global.ErrorCode;
import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.adminService.implement.S3Service;
import com.backend.superme.service.user.TokenBlacklistService;
import com.backend.superme.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Tag(name = "로그인, 회원가입 등 유저관련 api입니다", description = "사용자가 회원가입, 로그인을 할 수 있습니다.")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private S3Service s3Service;

    @GetMapping("/")
    @Operation(summary = "index.html 로 이동하는 경로입니다.", description = "메인경로입니다.")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok().body("인덱스 페이지");
    }

//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }



    @GetMapping("/user/login")
    @Operation(summary = "index.html 로 이동하는 경로입니다.", description = "메인경로입니다.")
    public ResponseEntity<?> showLoginPage() {
        return ResponseEntity.ok().body("로그인 페이지");
    }

//    @GetMapping("/user/login")
//    public String showLoginPage() {
//        return "login";
//    }
//


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

//    @GetMapping("/user/signup")
//    @Operation(summary = "회원가입 페이지로 이동합니다.", description = "회원가입 페이지로 이동합니다")
//    public ResponseEntity<?> showSignupPage() {
//        return ResponseEntity.ok().body("회원가입 페이지");
//    }
//
//    @GetMapping("/user/signup")
//    public String showSignupPage() {
//        return "signup";
//    }


//    @GetMapping("/user/signup/check/{email}")
//    @Operation(summary = "이메일 중복을 체크합니다.", description = "중복회원을 확인합니다.")
//    public ResponseEntity<?> checkEmail(@PathVariable String email) {
//        boolean result = userService.checkEmail(email);
//        if (result) {
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("errorMessage", ErrorCode.DUPLICATED_EMAIL.getMessage());
//            return ResponseEntity.status(ErrorCode.DUPLICATED_EMAIL.getStatus()).body(errorResponse);
//        } else {
//            return ResponseEntity.ok().body("사용 가능한 이메일입니다.");
//        }
//    }
//
//    @PostMapping("/user/signup")
//    @Operation(summary = "회원가입 API", description = "사용자가 회원가입하는 API 입니다.")
//    public ResponseEntity<?> signup(UserDto userDto) {
//        try {
//            userService.signupUser(userDto);
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "회원가입 성공");
//            return ResponseEntity.ok(response);
//        } catch (RuntimeException e) {
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("errorMessage", ErrorCode.DUPLICATED_EMAIL.getMessage());
//            return ResponseEntity.status(ErrorCode.DUPLICATED_EMAIL.getStatus()).body(errorResponse);
//        }
//    }


    @PostMapping("/user/signup")
    @Operation(summary = "회원가입 API", description = "사용자가 회원가입하는 API 입니다.")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto, @RequestParam("profileImage") MultipartFile profileImage) {
        String email = userDto.getEmail();

        // 이메일 중복 확인
        boolean isDuplicatedEmail = userService.checkEmail(email);
        if (isDuplicatedEmail) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", ErrorCode.DUPLICATED_EMAIL.getMessage());
            return ResponseEntity.status(ErrorCode.DUPLICATED_EMAIL.getStatus()).body(errorResponse);
        }

        try {
            // 프로필 이미지 업로드
            List<String> imgUrlList = s3Service.upload(Collections.singletonList(profileImage));
            // 업로드된 이미지의 URL을 가져옴 (이 예제에서는 하나의 이미지만 업로드하므로 리스트의 첫 번째 요소만 가져옴)
            String profileImageUrl = imgUrlList.get(0);

            // 회원가입 처리
            userDto.setProfile(profileImageUrl); // 유저 정보에 프로필 이미지 URL 설정
            userService.signupUser(userDto);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "회원가입 성공");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
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




