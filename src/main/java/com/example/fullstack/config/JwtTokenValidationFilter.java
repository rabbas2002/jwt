package com.example.fullstack.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenValidationFilter extends OncePerRequestFilter{
    Logger logger = LoggerFactory.getLogger(JwtTokenValidationFilter.class);

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        logger.info("Request received for Jwt token validation");
        JwtTokenValidator validator = new JwtTokenValidator();
        validator.validateJwtToken(request, response, false);
        filterChain.doFilter(request, response);
    }
}
