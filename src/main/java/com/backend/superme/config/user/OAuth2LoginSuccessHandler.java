package com.backend.superme.config.user;

import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.toString());
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
        String email = (String) attributes.get("email");

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        System.out.println("생성 안 됨?");

        if (userEntity.isPresent()) {
            // JWT 토큰 생성
            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            UserPrincipal userPrincipal = UserPrincipal.create(newUser);
            String token = jwtTokenProvider.generateToken(userPrincipal);
            System.out.println(token);
            // 리다이렉트 URL 생성
            String redirectUrl = "http://localhost:8081/?token=" + token;
            response.sendRedirect(redirectUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}