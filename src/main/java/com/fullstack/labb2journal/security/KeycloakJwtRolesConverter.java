package com.fullstack.labb2journal.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KeycloakJwtRolesConverter extends JwtAuthenticationConverter {

//hey
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract roles from the "realm_access" claim
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }

        // Extract roles from the "resource_access" claim (optional)
        Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        if (resourceAccess != null) {
            resourceAccess.forEach((resource, rolesObj) -> {
                if (rolesObj instanceof Map) {
                    Map<String, Object> resourceRoles = (Map<String, Object>) rolesObj;
                    List<String> resourceRolesList = (List<String>) resourceRoles.get("roles");
                    if (resourceRolesList != null) {
                        for (String role : resourceRolesList) {
                            authorities.add(new SimpleGrantedAuthority("ROLE_" + resource + "_" + role));
                        }
                    }
                }
            });
        }

        return authorities;
    }
}
