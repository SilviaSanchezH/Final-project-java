package com.example.centerserver.controller.dto;

public class WorkerDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String occupation;
    private String professionalNumber;

    public WorkerDTO(Long id, String username, String password, String name, String lastName, String phoneNumber, String occupation, String professionalNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
        this.professionalNumber = professionalNumber;
    }

    public WorkerDTO() {
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getProfessionalNumber() {
        return professionalNumber;
    }

    public void setProfessionalNumber(String professionalNumber) {
        this.professionalNumber = professionalNumber;
    }
}
