package com.backend.superme.config.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String createToken(String subject, long ttlMillis) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ttlMillis);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}
