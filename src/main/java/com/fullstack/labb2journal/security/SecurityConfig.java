package com.fullstack.labb2journal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final KeycloakJwtRolesConverter keycloakJwtRolesConverter;

    public SecurityConfig(KeycloakJwtRolesConverter keycloakJwtRolesConverter) {
        this.keycloakJwtRolesConverter = keycloakJwtRolesConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakJwtRolesConverter))
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.addAllowedOrigin("https://labb2frontend.app.cloud.cbh.kth.se");
                    corsConfig.addAllowedMethod("*");
                    corsConfig.addAllowedHeader("*");
                    return corsConfig;
                }));

        return http.build();
    }
}
