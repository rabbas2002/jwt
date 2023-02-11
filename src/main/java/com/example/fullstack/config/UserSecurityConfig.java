package com.example.fullstack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class)
                        .addFilterAfter(new JwtTokenCreatorFilter(), BasicAuthenticationFilter.class)
                        .authorizeRequests().requestMatchers("/user/**").authenticated()
                        .requestMatchers("/register","/login").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .and().httpBasic().and().csrf().disable();
//        http.csrf().disable();
//        http.authorizeRequests().requestMatchers("/register","/login").permitAll().requestMatchers("/user/**").hasAnyAuthority("RETAILER","DISTRIBUTOR","ADMIN").requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated().and().httpBasic();
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
}
