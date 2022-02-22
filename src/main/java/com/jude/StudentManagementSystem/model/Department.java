package com.jude.StudentManagementSystem.model;

import java.time.LocalDateTime;

public class Department {

    private String name;
    private LocalDateTime createdOn;

    public Department(String name, LocalDateTime createdOn) {
        this.name = name;
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
