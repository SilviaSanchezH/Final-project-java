package com.example.centerserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Worker {
    @Id
    private long workerId;
    @ManyToOne
    @JoinColumn(name = "center_id")
    @JsonIgnore
    private Center center;

    public Worker(long workerId, Center center) {
        this.workerId = workerId;
        this.center = center;
    }

    public Worker() {
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
