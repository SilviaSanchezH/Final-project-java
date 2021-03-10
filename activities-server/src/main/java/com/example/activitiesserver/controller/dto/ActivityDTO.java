package com.example.activitiesserver.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ActivityDTO {
    private Long id;
    private String title;
    private String description;
    private String type;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private String time;
    private Long center;
    private List<Long> users;
    private List<Long> workers;

    public ActivityDTO(String title, String description, String type, LocalDate date, String time, Long center, List<Long> users, List<Long> workers) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.date = date;
        this.time = time;
        this.center = center;
        this.users = users;
        this.workers = workers;
    }

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String title, String description, String type, LocalDate date, String time, Long center, List<Long> users, List<Long> workers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.date = date;
        this.time = time;
        this.center = center;
        this.users = users;
        this.workers = workers;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public List<Long> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Long> workers) {
        this.workers = workers;
    }
}
