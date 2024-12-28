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

        DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter =
                new DelegatingJwtGrantedAuthoritiesConverter(
                        new JwtGrantedAuthoritiesConverter(),
                        new KeycloakJwtRolesConverter());

        httpSecurity
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(
                                jwtToken -> new JwtAuthenticationToken(jwtToken, authoritiesConverter.convert(jwtToken)))
                        ))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public").permitAll()  // Public endpoint, no authentication needed
                        .requestMatchers("/**/journal")
                        .hasAnyAuthority(
                                KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "rest-api_worker",
                                KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "rest-api_doctor",
                                KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "rest-api_patient"
                        ) // Allow access to worker, doctor, and patient roles

                        .requestMatchers("worker").hasAuthority(KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "rest-api_worker")
                        .requestMatchers("/doctor").hasAuthority(KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "rest-api_doctor")
                        .requestMatchers("/patient").hasAuthority(KeycloakJwtRolesConverter.PREFIX_RESOURCE_ROLE + "rest-api_patient")
                        .anyRequest().authenticated()) // All other endpoints require authentication
                .csrf(csrf -> csrf.disable()); // Disable CSRF for API endpoints (enable if needed for your app)

        return httpSecurity.build();
    }

}
