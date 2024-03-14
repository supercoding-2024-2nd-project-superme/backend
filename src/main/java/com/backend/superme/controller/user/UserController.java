package com.backend.superme.controller.user;


import com.backend.superme.dto.user.UserDto;
import com.backend.superme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        String token = userService.authenticateUser(userDto);
        return ResponseEntity.ok(token);
    }


    @GetMapping("/signup")
    public String showSignupPage(@RequestParam(required = false) String error, Model model) {
        if ("duplicate_email".equals(error)) {
            model.addAttribute("error", "duplicate_email");
        }
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

