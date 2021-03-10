package com.example.edgeserver.controller.dto;

public class NewClientDTO {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String secondSurname;
    private String phoneNumber;
    private Long center;
    private String address;
    private String city;
    private String gender;
    private int age;

    public NewClientDTO(String username, String password, String name, String lastName, String secondSurname, String phoneNumber, Long center, String address, String city, String gender, int age) {
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

    public NewClientDTO() {
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

    public void setSecondSurname(String secondName) {
        this.secondSurname = secondName;
    }
}
