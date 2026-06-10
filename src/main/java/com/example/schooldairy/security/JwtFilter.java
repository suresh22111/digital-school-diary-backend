package com.example.schooldairy.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain

    ) throws ServletException, IOException {

        String path = request.getServletPath();

        if (
                path.startsWith("/api/auth")
                        || path.startsWith("/api/homework")
                        || path.startsWith("/api/attendance")
                        || path.startsWith("/api/marks")
                        || path.startsWith("/api/marksheet")
                        || path.startsWith("/api/events")
                        || path.startsWith("/api/announcements")
                        || path.startsWith("/api/assignments")
                        || path.startsWith("/api/report-card")
                        || path.startsWith("/uploads")
        ) {

            filterChain.doFilter(request, response);
            return;
        }

        // GET HEADER
        String authHeader =
                request.getHeader(
                        "Authorization"
                );

        // IF HEADER NOT PRESENT
        if (
                authHeader == null
                        ||
                        !authHeader.startsWith(
                                "Bearer "
                        )
        ) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        // EXTRACT TOKEN
        String token =
                authHeader.substring(7);

        // EXTRACT USERNAME
        String username =
                jwtService.extractUsername(
                        token
                );

        // VALIDATE
        if (
                username != null
                        &&
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                == null
        ) {

            UserDetails userDetails =
                    new User(

                            username,

                            "",

                            new ArrayList<>()
                    );

            if (
                    jwtService.isTokenValid(
                            token,
                            username
                    )
            ) {

                UsernamePasswordAuthenticationToken authToken =

                        new UsernamePasswordAuthenticationToken(

                                userDetails,

                                null,

                                userDetails.getAuthorities()
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                authToken
                        );
            }
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}