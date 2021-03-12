package com.example.edgeserver.security;

import com.example.edgeserver.controller.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class CustomUserDetails implements UserDetails {
    private UserDTO user;

    public CustomUserDetails(UserDTO user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new HashSet<>();

        String role = user.getRole() != null ? user.getRole().getName().toString() : "";

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
     // (String token, Long id, String username, String name, String role)
    public Long getId() {
        return user.getId();
    }

    public String getName() {
        return user.getName();
    }

    public String getRole() {
        return user.getRole() != null ? user.getRole().getName().toString() : "";
    }

    public Long getCenter() {
        return user.getCenter();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
