package com.jude.StudentManagementSystem.model.response;

public enum ResponseCodes {

    SUCCESS("00", "Successful"),
    STUDENT_NOT_FOUND("404", "Student record not found"),
    RECORD_NOT_FOUND("104", "Record not found"),
    DUPLICATE_RECORD("419", "Record already exists"),
    SYSTEM_ERROR("500", "Something went wrong. Please try again later");

    private final String code;
    private final String message;

    ResponseCodes(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
