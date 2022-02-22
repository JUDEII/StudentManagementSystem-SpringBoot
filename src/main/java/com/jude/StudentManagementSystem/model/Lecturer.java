package com.jude.StudentManagementSystem.model;

public class Lecturer {

    private String name;
    private String sex;
    private long departmentId;
    private String role;

    public Lecturer(String name, String sex, long departmentId, String role) {
        this.name = name;
        this.sex = sex;
        this.departmentId = departmentId;
        this.role = role;
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
}
