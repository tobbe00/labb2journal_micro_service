package com.fullstack.labb2journal.security;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class KeycloakJwtRolesConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        // Extract authorities using the helper method
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

        // You can also extract additional claims such as the username
        String username = jwt.getClaimAsString("preferred_username");

        // Return a JwtAuthenticationToken with the extracted authorities
        return new JwtAuthenticationToken(jwt, authorities, username);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract roles from "realm_access"
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        }

        // Extract roles from "resource_access" (optional)
        Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        if (resourceAccess != null) {
            resourceAccess.forEach((resource, rolesObj) -> {
                if (rolesObj instanceof Map) {
                    Map<String, Object> resourceRoles = (Map<String, Object>) rolesObj;
                    List<String> resourceRolesList = (List<String>) resourceRoles.get("roles");
                    if (resourceRolesList != null) {
                        resourceRolesList.forEach(role ->
                                authorities.add(new SimpleGrantedAuthority("ROLE_" + resource + "_" + role))
                        );
                    }
                }
            });
        }

        return authorities;
    }
}
