package com.jude.StudentManagementSystem.model;

import java.time.LocalDateTime;

public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String sex;
    private Long departmentId;
    private String registrationNumber;
    private LocalDateTime createdOn;

    public Student(String firstName, String lastName, String dateOfBirth, String sex, Long departmentId, String registrationNumber, LocalDateTime createdOn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.departmentId = departmentId;
        this.registrationNumber = registrationNumber;
        this.createdOn = createdOn;
    }

    public Student(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
