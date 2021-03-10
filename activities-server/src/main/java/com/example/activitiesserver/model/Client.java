package com.example.activitiesserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {
    @Id
    private long clientId;
    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Activity> activity;

    public Client(long clientId, List<Activity> activity) {
        this.clientId = clientId;
        this.activity = activity;
    }

    public Client() {
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public List<Activity> getActivity() {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }
}
