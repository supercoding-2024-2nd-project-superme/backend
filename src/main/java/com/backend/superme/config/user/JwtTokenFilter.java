package com.backend.superme.config.user;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateJwtToken(token)) {
            // 유효한 토큰인 경우, 헤더에 토큰 추가
            response.addHeader("Authorization", "Bearer " + token);
            // 유효한 토큰인 경우, 필요한 작업 수행
            // 예를 들어, 토큰을 해독하여 사용자 정보를 설정하거나 헤더에 토큰을 추가하는 등의 작업을 수행할 수 있습니다.
        }
        filterChain.doFilter(request, response);
    }


}