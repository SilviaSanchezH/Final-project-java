package com.example.activitiesserver.model;

import com.example.activitiesserver.enums.ActivityType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.apache.catalina.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.jdbc.Work;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private String time;
    private Long center;

    @JoinTable(
            name = "activities_clients",
            joinColumns = @JoinColumn(name = "activity_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="client_id", nullable = false)
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Client> clients;

    @JoinTable(
            name = "activities_workers",
            joinColumns = @JoinColumn(name = "activity_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="worker_id", nullable = false)
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Worker> workers;

    public Activity(String title, String description, ActivityType type, LocalDate date, String time, Long center) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.date = date;
        this.time = time;
        this.center = center;
    }

    public Activity(Long id, String title, String description, ActivityType type, LocalDate date, String time, Long center) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.date = date;
        this.time = time;
        this.center = center;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getCenter() {
        return center;
    }

    public void setCenter(Long center) {
        this.center = center;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
