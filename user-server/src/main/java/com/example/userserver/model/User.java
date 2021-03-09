package com.example.userserver.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Long center;
    @OneToOne(mappedBy = "user")
    private Role role;

    public User(Long id, String username, String password, String name, String lastName, String phoneNumber, Long center) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.center = center;
    }

    public User(String username, String password, String name, String lastName, String phoneNumber, Long center) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.center = center;
    }

    public User(String username, String name, String lastName, String phoneNumber, Long center) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.center = center;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCenter() {
        return center;
    }

    public void setCenter(Long center) {
        this.center = center;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
