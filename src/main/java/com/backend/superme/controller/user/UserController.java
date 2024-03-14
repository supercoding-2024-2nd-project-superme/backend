package com.backend.superme.controller.user;


import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.user.TokenBlacklistService;
import com.backend.superme.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        String token = userService.authenticateUser(userDto);
        if (token != null) {
            // 토큰을 JSON 객체로 반환
            Map<String, String> tokenWrapper = new HashMap<>();
            tokenWrapper.put("token", token);
            return ResponseEntity.ok(tokenWrapper); // JSON 형태로 토큰 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    @GetMapping("/signup/check/{email}")
    @ResponseBody
    public String checkEmail(@PathVariable String email) {
        boolean result = userService.checkEmail(email);
        if (result) {
            return "중복된 이메일입니다.";
        } else {
            return "사용 가능한 이메일입니다.";
        }
        //그냥 값만 주고 표현은 그쪽에서 하도록 해도 될듯
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.signupUser(userDto);
            return "redirect:/login"; // 회원가입 성공 시 로그인 페이지로 이동
        } catch (RuntimeException e) {
            // 중복된 이메일이 있음을 나타내는 파라미터를 URL에 추가하여 회원가입 페이지로 리디렉션
            redirectAttributes.addAttribute("error", "duplicate_email");
            return "redirect:/signup";
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // 클라이언트 사이드에서 로컬 스토리지의 JWT를 삭제하면 되므로,
        // 이 메소드는 실제로는 클라이언트에서 로그아웃을 처리하는 데 필요한 정보를 제공하지 않습니다.
        // 필요하다면, 여기서 JWT 블랙리스트 처리를 할 수 있습니다.
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Missing or invalid Authorization header."); // 단순히 성공 응답 반환
    }
}

