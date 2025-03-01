package com.example.todoapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${jwt-secret}")
    private String secret;

    @Value("${jwt-expiration-milliseconds}")
    private int jwtExpirationDate;

    private Key key; // Khởi tạo key sau khi @Value đã inject

    @PostConstruct
    public void init() {
        System.out.println("Secret: " + secret); // Kiểm tra secret có bị null không
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); // Chỉ khởi tạo key sau khi có secret
    }

    public String generateToken(String email, Long userId){
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        Map<String, Object> claim = new HashMap<>();
        claim.put("userId", userId);
        return Jwts.builder()
                .setSubject(email)
                .addClaims(claim)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    public String getEmailFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public Long getUserIdFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", Long.class);
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
