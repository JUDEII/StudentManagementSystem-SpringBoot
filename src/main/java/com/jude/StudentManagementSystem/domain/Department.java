package com.jude.StudentManagementSystem.domain;

import com.jude.StudentManagementSystem.model.response.BaseResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Department {
    private Long id;
    private String name;
}
