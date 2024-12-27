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

@Component
public class KeycloakJwtRolesConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        // Extract authorities from the token
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

        // Extract the username for the principal
        String username = jwt.getClaimAsString("preferred_username");

        // Return a JwtAuthenticationToken with the authorities and username
        return new JwtAuthenticationToken(jwt, authorities, username);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract roles from "realm_access"
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            if (roles != null) {
                roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
            }
        }

        // Extract roles from "resource_access.account"
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            Map<String, Object> account = (Map<String, Object>) resourceAccess.get("account");
            if (account != null) {
                List<String> accountRoles = (List<String>) account.get("roles");
                if (accountRoles != null) {
                    accountRoles.forEach(role ->
                            authorities.add(new SimpleGrantedAuthority("ROLE_account_" + role))
                    );
                }
            }
        }

        return authorities;
    }
}
