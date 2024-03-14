package com.backend.superme.controller.user;


import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
        // 세션에 토큰 저장 또는 쿠키에 토큰을 담는 등의 작업이 필요
        if (token != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token); // 토큰을 Authorization 헤더에 추가
            headers.add("Location", "/"); // 리다이렉트할 URL을 설정
            return ResponseEntity.ok().headers(headers).body(null);
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
}

