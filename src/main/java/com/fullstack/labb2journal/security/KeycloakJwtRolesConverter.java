package com.fullstack.labb2journal.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
/*
public class KeycloakJwtRolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    public static final String PREFIX_REALM_ROLE = "ROLE_realm_";
    public static final String PREFIX_RESOURCE_ROLE = "ROLE_";
    private static final String CLAIM_REALM_ACCESS = "realm_access";
    private static final String CLAIM_RESOURCE_ACCESS = "resource_access";
    private static final String CLAIM_ROLES = "roles";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // Extract realm roles
        Map<String, Object> realmAccess = jwt.getClaim(CLAIM_REALM_ACCESS);
        if (realmAccess != null && realmAccess.containsKey(CLAIM_ROLES)) {
            Collection<String> roles = (Collection<String>) realmAccess.get(CLAIM_ROLES);
            if (roles != null) {
                grantedAuthorities.addAll(roles.stream()
                        .map(role -> new SimpleGrantedAuthority(PREFIX_REALM_ROLE + role))
                        .collect(Collectors.toList()));
            }
        }

        // Extract resource roles
        Map<String, Object> resourceAccess = jwt.getClaim(CLAIM_RESOURCE_ACCESS);
        if (resourceAccess != null) {
            resourceAccess.forEach((resource, resourceClaims) -> {
                if (resourceClaims instanceof Map) {
                    Map<String, Object> claims = (Map<String, Object>) resourceClaims;
                    if (claims.containsKey(CLAIM_ROLES)) {
                        Collection<String> roles = (Collection<String>) claims.get(CLAIM_ROLES);
                        if (roles != null) {
                            roles.forEach(role -> grantedAuthorities.add(
                                    new SimpleGrantedAuthority(PREFIX_RESOURCE_ROLE + resource + "_" + role)));
                        }
                    }
                }
            });
        }

        return grantedAuthorities;
    }
}
*/