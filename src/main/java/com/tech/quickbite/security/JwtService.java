package com.tech.quickbite.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tech.quickbite.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60; // 1 hour

    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("role", user.getRole().name());

        String jwt= Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME))
                .and()
                .signWith(getKey())
                .compact();
        
        //System.out.println("Generated Token = " + jwt);
        return jwt;
        
    }

    private SecretKey getKey() {

    	//System.out.println("JWT Secret = " + secretKey);
        byte[] keyBytes =
                secretKey.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {

        return extractClaim(
                token,
                Claims::getSubject);
    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimResolver) {

        Claims claims =
                extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(
            String token,
            UserDetails userDetails) {

        String username =
                extractUserName(token);

        return username.equals(
                userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(
            String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    private Date extractExpiration(
            String token) {

        return extractClaim(
                token,
                Claims::getExpiration);
    }
}