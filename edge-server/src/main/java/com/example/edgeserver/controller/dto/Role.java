package com.example.edgeserver.controller.dto;


import com.example.edgeserver.enums.RoleEnum;

public class Role {

    private Long id;

    private RoleEnum name;
    private UserDTO user;

    public Role(RoleEnum name, UserDTO user) {
        this.name = name;
        this.user = user;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
