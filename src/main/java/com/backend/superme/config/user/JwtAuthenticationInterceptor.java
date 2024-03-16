package com.backend.superme.config.user;

import com.backend.superme.service.user.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
// JwtAuthenticationInterceptor는 스프링의 HandlerInterceptor 인터페이스를 구현한 컴포넌트입니다.
// 이 클래스는 HTTP 요청이 컨트롤러에 도달하기 전에 요청을 인터셉트하고, 추가적인 처리를 수행합니다.
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenBlacklistService tokenBlacklistService;
    // TokenBlacklistService는 블랙리스트에 포함된 토큰을 관리하는 서비스입니다.
    // 이 서비스를 통해 요청에 포함된 토큰이 블랙리스트에 포함되었는지 확인합니다.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preHandle 메소드는 컨트롤러로 요청이 전달되기 전에 호출됩니다.
        String authHeader = request.getHeader("Authorization");
        // 요청 헤더에서 "Authorization"을 추출합니다. 이 헤더는 "Bearer [토큰]" 형식의 값을 가집니다.

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // "Bearer " 이후의 문자열을 추출하여 실제 토큰 값을 얻습니다.

            if (tokenBlacklistService.isTokenBlacklisted(token)) {
                // 추출된 토큰이 블랙리스트에 포함되어 있는지 확인합니다.
                // 토큰이 블랙리스트에 있다면, 해당 요청은 인증되지 않은 것으로 간주하고 요청 처리를 중단합니다.
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is blacklisted.");
                return false; // 블랙리스트에 포함된 토큰이므로, 요청 처리를 중단하고 401 Unauthorized 응답을 반환합니다.
            }
        }

        return true; // 토큰이 블랙리스트에 포함되지 않았다면, 요청 처리를 계속 진행합니다.
    }
}


//이 JwtAuthenticationInterceptor 클래스는 Spring의 HandlerInterceptor 인터페이스를 구현하여,
// HTTP 요청이 컨트롤러로 전달되기 전에 인터셉트하는 역할을 합니다.
// 주로 JWT 토큰 기반의 인증 시스템에서 토큰의 유효성을 검사하는 데 사용됩니다.
// 클래스 내에서 주요 기능과 역할에 대한 설명은 다음과 같습니다.

//JwtAuthenticationInterceptor 클래스는 클라이언트로부터의 모든 HTTP 요청을 가로채서,
// 해당 요청이 인증된 사용자에 의해 이루어진 것인지 검증하는 중요한 보안 역할을 수행합니다.
// 특히, 요청에 포함된 JWT 토큰이 블랙리스트에 등록되어 있을 경우,
// 요청을 거부하고 401 Unauthorized 응답을 반환하여, 시스템 보안을 강화합니다.