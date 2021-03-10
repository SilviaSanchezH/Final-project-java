package com.example.userserver.model;

import com.example.userserver.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Client extends User{
    private String address;
    private String city;
    private int age;

    public Client(Long id, String username, String password, String name, String lastName, String secondSurname, String phoneNumber, String address, String city, Long center, Gender gender, int age) {
        super(id, username, secondSurname, password, name, lastName, phoneNumber, center, gender);
        this.address = address;
        this.city = city;
        this.age = age;
    }

    public Client(String username, String password, String name, String lastName, String secondSurname, String phoneNumber, String address, String city, Long center, Gender gender, int age) {
        super(username, password, name, lastName, secondSurname, phoneNumber, center, gender);
        this.address = address;
        this.city = city;
        this.age = age;
    }

    public Client(String username, String name, String lastName, String secondSurname, String phoneNumber, String address, String city, Long center, Gender gender, int age) {
        super(username, name, lastName, secondSurname, phoneNumber, center, gender);
        this.address = address;
        this.city = city;
        this.age = age;
    }

    public Client() {
        super();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
