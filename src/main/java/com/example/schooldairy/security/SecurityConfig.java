package com.example.schooldairy.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(

            HttpSecurity http

    ) throws Exception {

        http

                // DISABLE CSRF
                .csrf(csrf -> csrf.disable())

                // ENABLE CORS
                .cors(cors -> {})

                // AUTHORIZE
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/homework/**",
                                "/api/attendance/**",
                                "/api/marks/**",
                                "/api/marksheet/**",
                                "/api/events/**",
                                "/api/announcements/**",
                                "/api/assignments/**",
                                "/api/report-card/**",
                                "/api/students/**",
                                "/uploads/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // JWT SESSION
                .sessionManagement(session ->

                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // ADD JWT FILTER
                .addFilterBefore(

                        jwtFilter,

                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
