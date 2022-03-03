package com.jude.StudentManagementSystem.service.contract.student;

import com.jude.StudentManagementSystem.exception.NotFoundException;
import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.model.response.PagingResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    BaseResponse registerStudent(Student student);
    BaseResponse findStudentById(Long id) throws NotFoundException;
    Student findStudentByRegistrationNumber(String regNum);
    Student findStudentByDepartment(Long id);
    PagingResponse<Student> retrieveStudents(PagingRequest request);
    ResponseEntity<?> updateStudent(Student student, Long id);

}
