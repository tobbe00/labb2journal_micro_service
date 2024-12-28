package com.fullstack.labb2journal.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;

@Configuration
public class WebSecurityConfig {

    // CORS Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure CORS and CSRF
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .csrf(csrf -> csrf.disable()) // Disable CSRF if not needed
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated() // Require authentication for all requests
                );

        return http.build();
    }

    // CORS Configuration Source
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("http://labb2frontend.app.cloud.cbh.kth.se"); // Allow specific frontend origin
        corsConfig.addAllowedHeader("*");  // Allow all headers
        corsConfig.addAllowedMethod(HttpMethod.GET);  // Allow GET requests
        corsConfig.addAllowedMethod(HttpMethod.POST); // Allow POST requests
        corsConfig.addAllowedMethod(HttpMethod.PUT);  // Allow PUT requests
        corsConfig.addAllowedMethod(HttpMethod.DELETE); // Allow DELETE requests

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Apply CORS to all endpoints
        return source;
    }
}

