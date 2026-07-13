package com.example.schooldairy.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;



    // GET SIGNING KEY
    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }

    // GENERATE TOKEN
    public String generateToken(
            String username
    ) {

        return Jwts.builder()

                .setSubject(username)

                .setIssuedAt(
                        new Date()
                )

                .setExpiration(

                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )

                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    // EXTRACT USERNAME
    public String extractUsername(
            String token
    ) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    // EXTRACT CLAIM
    public <T> T extractClaim(

            String token,

            Function<Claims, T> resolver
    ) {

        Claims claims =
                extractAllClaims(token);

        return resolver.apply(
                claims
        );
    }

    // EXTRACT ALL CLAIMS
    private Claims extractAllClaims(
            String token
    ) {

        return Jwts.parserBuilder()

                .setSigningKey(
                        getSigningKey()
                )

                .build()

                .parseClaimsJws(token)

                .getBody();
    }

    // VALIDATE TOKEN
    public boolean isTokenValid(

            String token,

            String username
    ) {

        final String extractedUsername =
                extractUsername(token);

        return extractedUsername
                .equals(username);
    }
}