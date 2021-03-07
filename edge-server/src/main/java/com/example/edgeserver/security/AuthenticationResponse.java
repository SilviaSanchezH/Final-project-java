package com.example.edgeserver.security;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String token;
    private final Long id;
    private final String username;
    private final String name;
    private final String role;
    private final Long center;

    public AuthenticationResponse(String token, Long id, String username, String name, String role, Long center) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.center = center;
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

    public Long getCenter() {
        return center;
    }
}