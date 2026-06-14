package com.tech.quickbite.security;

import java.io.IOException;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.
        SecurityContextHolder;

import org.springframework.security.core.userdetails.
        UserDetails;

import org.springframework.security.web.authentication.
        WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.
        OncePerRequestFilter;

import com.tech.quickbite.service.
        CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    private CustomUserDetailsService
            customUserDetailsService;

    public JwtFilter(
            JwtService jwtService,
            CustomUserDetailsService customUserDetailsService) {

        this.jwtService = jwtService;
        this.customUserDetailsService =
                customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/api/auth")) {

            filterChain.doFilter(
                    request,
                    response);

            return;
        }

        String authHeader =
                request.getHeader(
                        "Authorization");

        String token = null;

        String username = null;

        if (authHeader != null
                && authHeader.startsWith(
                        "Bearer ")) {

            token =
                    authHeader.substring(7);
            
            // System.out.println("Received Token = " + token);

            username =
                    jwtService.extractUserName(
                            token);
        }

        if (username != null
                && SecurityContextHolder
                .getContext()
                .getAuthentication() == null) {

            UserDetails userDetails =
                    customUserDetailsService
                            .loadUserByUsername(
                                    username);

            if (jwtService.validateToken(
                    token,
                    userDetails)) {

                UsernamePasswordAuthenticationToken
                        authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(
                                        request));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                authToken);
            }
        }

        filterChain.doFilter(
                request,
                response);
    }
}