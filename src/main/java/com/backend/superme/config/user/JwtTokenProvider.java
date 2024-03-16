package com.backend.superme.config.user;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
// Spring의 Component 어노테이션을 사용하여, 이 클래스의 인스턴스가 Spring ApplicationContext에 의해 관리되는 빈(bean)임을 나타냅니다.
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;
    // application.properties 파일에서 설정된 JWT 시크릿 키를 주입받습니다. 이 키는 토큰의 서명에 사용됩니다.

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;
    // 토큰의 유효 기간(밀리초 단위)을 설정 파일로부터 주입받습니다.

    public String generateToken(UserPrincipal userPrincipal) {
        // 사용자 인증 정보를 기반으로 JWT 토큰을 생성합니다.

        // UserPrincipal을 얻어, 사용자의 사용자명(username)을 토큰의 주제(subject)로 설정합니다.
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        // 토큰의 발행 시간, 만료 시간을 설정하고, HS256 알고리즘과 시크릿 키를 사용하여 토큰에 서명합니다.
    }

    public boolean validateJwtToken(String authToken) {
        // 주어진 토큰의 유효성을 검증합니다.
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            // 시크릿 키를 사용하여 토큰을 파싱하고, 이 과정에서 문제가 없으면 토큰은 유효한 것으로 간주합니다.
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 토큰 파싱 과정에서 발생한 예외는 유효하지 않은 토큰을 나타냅니다.
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        // HTTP 요청 헤더로부터 JWT 토큰을 추출합니다.
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 접두어를 제거한 토큰 문자열을 반환합니다.
        }
        return null;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }
}




//JwtTokenProvider 클래스는 JWT 토큰 생성, 유효성 검증, 그리고 HTTP 요청으로부터 JWT 토큰을 추출하는 기능을 담당합니다.
// 이 클래스는 JWT 기반 인증 시스템의 핵심적인 부분으로, 스프링 시큐리티 설정에 통합되어 사용자 인증 및 인가 과정에 사용됩니다.

//이 클래스는 다음과 같은 핵심적인 기능을 제공합니다:
//
//JWT 토큰 생성: generateToken 메소드는 인증된 사용자의 정보를 받아, JWT 토큰을 생성하고 반환합니다.
// 토큰은 사용자의 사용자명을 주제로 하며, 설정된 만료 시간을 가집니다.
//JWT 토큰 유효성 검증: validateJwtToken 메소드는 주어진 토큰이 유효한지를 검사합니다.
// 이 과정에서 토큰의 서명이 검증되고, 만료되지 않았는지, 그리고 구조적으로 올바른지를 확인합니다.
//토큰 추출: resolveToken 메소드는 HTTP 요청의 Authorization 헤더로부터 JWT 토큰을 추출합니다.
//JwtTokenProvider 클래스를 사용함으로써, 애플리케이션은 JWT를 이용한 인증 메커니즘을 효율적으로 구현할 수 있습니다.