package com.example.userserver.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Client extends User{
    private String address;
    private String city;

    public Client(Long id, String username, String password, String name, String lastName, String phoneNumber, String address, String city, Long center) {
        super(id, username, password, name, lastName, phoneNumber, center);
        this.address = address;
        this.city = city;
    }

    public Client(String username, String password, String name, String lastName, String phoneNumber, String address, String city, Long center) {
        super(username, password, name, lastName, phoneNumber, center);
        this.address = address;
        this.city = city;
    }

    public Client(String username, String name, String lastName, String phoneNumber, String address, String city, Long center) {
        super(username, name, lastName, phoneNumber, center);
        this.address = address;
        this.city = city;
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

}
