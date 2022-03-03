package com.jude.StudentManagementSystem.exception;

public class NotFoundException extends Exception {

    private String code;

//    public NotFoundException(String message) { super(exception); }
    public NotFoundException(String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
