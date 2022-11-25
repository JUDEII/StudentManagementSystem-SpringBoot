package com.jude.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class Lecturer {
    @JsonIgnore
    private long id;

    @NotNull
    private String name;
    private String sex;
    private long departmentId;
    private String role;
    @JsonIgnore
    private String email;

    public Lecturer(String name, String sex, long departmentId, String role, String email) {
        this.name = name;
        this.sex = sex;
        this.departmentId = departmentId;
        this.role = role;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
