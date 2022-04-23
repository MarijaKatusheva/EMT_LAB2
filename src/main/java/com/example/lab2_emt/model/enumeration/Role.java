package com.example.lab2_emt.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,ROLE_LIBRARIAN;
    @Override
    public String getAuthority() {
        return name();
    }
}
