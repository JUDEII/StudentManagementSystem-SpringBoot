package com.jude.StudentManagementSystem.controller;

import com.jude.StudentManagementSystem.exception.NotFoundException;
import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.model.response.PagingResponse;
import com.jude.StudentManagementSystem.service.contract.student.StudentService;
import com.jude.StudentManagementSystem.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //@RequestBody does not work for a GET request
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagingResponse<Student> retrieveStudents(@Valid PagingRequest request) {
        return studentService.retrieveStudents(request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse findById(@PathVariable Long id) throws NotFoundException {
            return studentService.findStudentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Long id){
        return studentService.updateStudent(student, id);
    }



}
