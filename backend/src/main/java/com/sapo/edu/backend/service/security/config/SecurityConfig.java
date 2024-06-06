package com.sapo.edu.backend.service.security.config;

import com.sapo.edu.backend.service.security.UserDetailsServiceImpl;
import com.sapo.edu.backend.service.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor to inject dependencies
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Bean to configure the security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection as we're using JWT
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.GET).permitAll() // Allow all GET requests without authentication
                        .requestMatchers(HttpMethod.POST).permitAll() // Allow all GET requests without authentication
                        .requestMatchers(HttpMethod.PUT).permitAll() // Allow all GET requests without authentication
                        .requestMatchers(HttpMethod.DELETE).permitAll() // Allow all GET requests without authentication
                        
//                        .requestMatchers("/login/**", "/register/**").permitAll() // Allow unauthenticated access to login and register endpoints
//                        .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN") // Require ADMIN authority for POST requests to /admin/**
//                        .requestMatchers(HttpMethod.PUT, "/admin/**").hasAuthority("ADMIN") // Require ADMIN authority for PUT requests to /admin/**
//                        .requestMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority("ADMIN") // Require ADMIN authority for DELETE requests to /admin/**
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .userDetailsService(userDetailsService) // Set the custom UserDetailsService for authentication
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Use stateless session management
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add the JWT filter before the UsernamePasswordAuthenticationFilter
                .build();
    }

    // Bean to provide a PasswordEncoder instance
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean to provide an AuthenticationManager instance
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
