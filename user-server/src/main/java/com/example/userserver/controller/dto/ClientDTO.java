package com.example.userserver.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ClientDTO {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min=6, message = "Password must be at least 6 characters long")
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    private String secondSurname;
    @NotEmpty
    @Pattern(regexp = "^[679][0-9]{8}$", message = "Not valid phone number format")
    private String phoneNumber;
    @NotNull
    private Long center;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotNull
    private int age;

    public ClientDTO(String username, String password, String name, String lastName, String secondSurname, String phoneNumber, Long center, String address, String city, String gender, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.secondSurname = secondSurname;
        this.phoneNumber = phoneNumber;
        this.center = center;
        this.address = address;
        this.city = city;
        this.gender = gender;
        this.age = age;
    }

    public ClientDTO() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }
}
