package com.example.edgeserver.security;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String token;
    private final Long id;
    private final String username;
    private final String name;
    private final String role;

    public AuthenticationResponse(String token, Long id, String username, String name, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}