package com.fullstack.labb2journal.security;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class JwtAuthConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        // Extract roles from the "realm_access" claim in the JWT
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        // Extract roles (list of roles) from the "realm_access" claim
        List<String> roles = (List<String>) realmAccess.get("roles");

        // Add roles as authorities (e.g., "ROLE_patient", "ROLE_doctor")
        Collection<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // prefix "ROLE_" as per Spring Security convention
                .collect(Collectors.toList());

        // You can also extract additional claims such as the username or any other claim if needed
        String username = jwt.getClaimAsString("preferred_username");

        // Return the JwtAuthenticationToken with authorities and username
        return new JwtAuthenticationToken(jwt, authorities, username);
    }
}