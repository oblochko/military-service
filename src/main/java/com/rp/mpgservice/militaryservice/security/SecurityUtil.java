package com.rp.mpgservice.militaryservice.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUtil {

    public static String getUserAttribute(String name) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (String) jwt.getClaims().get(name);
    }
}
