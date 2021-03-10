package com.example.activitiesserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Worker {
    @Id
    private long workerId;
    @ManyToMany(mappedBy = "workers", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Activity> activity;

    public Worker(long workerId, List<Activity> activity) {
        this.workerId = workerId;
        this.activity = activity;
    }

    public Worker() {
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public List<Activity> getActivity() {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }
}
