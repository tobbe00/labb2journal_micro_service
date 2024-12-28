package com.fullstack.labb2journal.conf;

import com.fullstack.labb2journal.security.KeycloakJwtRolesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // Set up the authorities converter
        DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter =
                new DelegatingJwtGrantedAuthoritiesConverter(
                        new JwtGrantedAuthoritiesConverter(),
                        new KeycloakJwtRolesConverter());

        // Configure the security filter chain to only secure /journals endpoint
        httpSecurity
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(
                                jwtToken -> new JwtAuthenticationToken(jwtToken, authoritiesConverter.convert(jwtToken)))
                        ))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/journals")
                        .hasAnyAuthority(
                                KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "worker",
                                KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "doctor",
                                KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "patient"
                        ) // Allow access to specific roles for /journals endpoint
                        .anyRequest().permitAll() // Allow other endpoints without authentication
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for API endpoints (enable if needed for your app)

        return httpSecurity.build();
    }
}

