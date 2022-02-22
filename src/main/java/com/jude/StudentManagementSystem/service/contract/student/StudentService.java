package com.jude.StudentManagementSystem.service.contract.student;

import com.jude.StudentManagementSystem.exception.StudentNotFoundException;
import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.BaseResponse;

import java.util.List;

public interface StudentService {

    BaseResponse registerStudent(Student student);
    Student findStudentById(Long id) throws StudentNotFoundException;
    Student findStudentByRegistrationNumber(String regNum);
    Student findStudentByDepartment(Long id);
//    List<BaseResponse> retrieveStudents(PagingRequest request);
    BaseResponse updateStudent(Student student, Long id);

}
