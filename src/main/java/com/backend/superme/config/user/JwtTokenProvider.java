package com.backend.superme.config.user;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // JWT 생성
    public String generateToken(UserPrincipal userPrincipal) {
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
//
//    // JWT에서 사용자 이름 추출
//    public String getUsernameFromJwtToken(String token) {
//        return Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
//    }
//
//    // JWT 유효성 검증
//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
//
//            return true;
////        catch (SignatureException e) { // 잘못된 JWT 서명
//        } catch (MalformedJwtException e) {
//            // 잘못된 JWT 구조
//        } catch (ExpiredJwtException e) {
//            // 만료된 JWT 토큰
//        } catch (UnsupportedJwtException e) {
//            // 지원되지 않는 JWT 토큰
//        } catch (IllegalArgumentException e) {
//            // 비어있는 JWT claims
//        }
//        return false;
//    }
}