package com.fullstack.labb2journal.security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure CORS
        http.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API endpoints

                // Configure Authorization
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated() // Secure all requests, require authentication
                )

                // Configure OAuth2 Resource Server with JWT Authentication Converter
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)) // Use custom JWT converter
                )

                // Configure Session Management to be Stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS) // Stateless session management
                );

        return http.build();
    }

    // CORS Configuration Source
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("https://labb2frontend.app.cloud.cbh.kth.se"); // Allow specific frontend origin
        corsConfig.addAllowedHeader("*");  // Allow all headers
        corsConfig.addAllowedMethod(HttpMethod.GET);  // Allow GET requests
        corsConfig.addAllowedMethod(HttpMethod.POST); // Allow POST requests
        corsConfig.addAllowedMethod(HttpMethod.PUT);  // Allow PUT requests
        corsConfig.addAllowedMethod(HttpMethod.DELETE); // Allow DELETE requests

        // Handle preflight requests (OPTIONS)
        corsConfig.addExposedHeader("Access-Control-Allow-Origin");
        corsConfig.addExposedHeader("Access-Control-Allow-Methods");
        corsConfig.addExposedHeader("Access-Control-Allow-Headers");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Apply CORS to all endpoints
        return source;
    }
}
