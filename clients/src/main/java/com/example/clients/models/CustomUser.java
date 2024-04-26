package com.example.clients.models;

import lombok.Data;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;


public class CustomUser extends User {
    private Long id;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static CustomUser fromAuthentication(Authentication authentication) {
        if (authentication == null) {
            return null;
        }

        String username = authentication.getName();
        String password = ""; // Пароль не сохраняем в объекте CustomUser
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        CustomUser customUser = new CustomUser(username, password, authorities);

        // Проверяем, является ли аутентифицированный пользователь объектом CustomUser
        if (authentication.getPrincipal() instanceof CustomUser) {
            CustomUser principal = (CustomUser) authentication.getPrincipal();
            customUser.setId(principal.getId());
        }

        return customUser;
    }

}
