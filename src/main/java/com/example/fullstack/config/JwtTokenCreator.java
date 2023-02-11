package com.example.fullstack.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class JwtTokenCreator {
    Logger logger = LoggerFactory.getLogger(JwtTokenCreator.class);

    public void generateToken(HttpServletRequest request, HttpServletResponse response) {

        //Get the username from authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) { //verify whether user is authenticated
            String username = authentication.getName();
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

            String jwt_token = Jwts.builder()
                    .setIssuer("javainfinite")
                    .setExpiration(new Date((new Date()).getTime() + 300000))
                    .setSubject("javainfinite_token")
                    .claim("username", username)
                    .claim("authorities", getUserRoles((List<GrantedAuthority>) authentication.getAuthorities()))
                    .signWith(key)
                    .compact();

            if (request.getHeader(SecurityConstants.REFRESH_HEADER) == null) {

                String refresh_token = Jwts.builder()
                        .setIssuer("javainfinite")
                        .setExpiration(new Date((new Date()).getTime() + 3000000))
                        .setSubject("javainfinite_token")
                        .claim("username", username)
                        .claim("authorities", getUserRoles((List<GrantedAuthority>) authentication.getAuthorities()))
                        .signWith(key)
                        .compact();

                response.setHeader(SecurityConstants.REFRESH_HEADER, refresh_token);
                logger.info("Refresh Token successfully generated: {}", refresh_token);
            }
            response.setHeader(SecurityConstants.AUTHORIZATION_HEADER, jwt_token);

            logger.info("Token successfully generated: {}", jwt_token);
        }
    }

    private String getUserRoles(List<GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
