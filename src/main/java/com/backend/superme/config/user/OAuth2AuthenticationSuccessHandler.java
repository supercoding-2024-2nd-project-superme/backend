package com.backend.superme.config.user;

import com.backend.superme.constant.user.RoleEnum;
import com.backend.superme.entity.user.UserEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String frontendUrl;

    private final JwtTokenProvider jwtTokenProvider;

    public OAuth2AuthenticationSuccessHandler(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String email = principal.getEmail();
        String name = principal.getFullName();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setUsername(name);
        userEntity.setRole(RoleEnum.USER);
        UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
        String jwtToken = jwtTokenProvider.generateToken(userPrincipal);

        // JWT 토큰을 HTTP Only 쿠키에 저장
        Cookie cookie = new Cookie("accessToken", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        getRedirectStrategy().sendRedirect(request, response, frontendUrl);
    }
}