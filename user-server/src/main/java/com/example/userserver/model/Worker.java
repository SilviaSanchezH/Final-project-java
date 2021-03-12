package com.example.userserver.model;

import com.example.userserver.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Worker extends User {

    private String occupation;
    private String professionalNumber;

    public Worker(Long id, String username, String password, String name, String lastName, String secondSurname, String phoneNumber, String occupation, String professionalNumber, Long center, Gender gender) {
        super(id, username, secondSurname, password, name, lastName, phoneNumber, center, gender);
        this.occupation = occupation;
        this.professionalNumber = professionalNumber;
    }

    public Worker(String username, String password, String name, String lastName, String secondSurname, String phoneNumber, String occupation, String professionalNumber, Long center, Gender gender) {
        super(username, password, name, lastName, secondSurname, phoneNumber, center, gender);
        this.occupation = occupation;
        this.professionalNumber = professionalNumber;
    }


    public Worker() {
        super();
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
