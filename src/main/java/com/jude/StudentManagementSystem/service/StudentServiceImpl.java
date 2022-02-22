package com.jude.StudentManagementSystem.service;

import com.jude.StudentManagementSystem.dao.StudentDao;
import com.jude.StudentManagementSystem.exception.StudentNotFoundException;
import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.service.contract.student.StudentService;
import com.jude.StudentManagementSystem.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

//@service means this class contains our business logic
@Service
public class StudentServiceImpl implements StudentService {
private final StudentDao studentDao;

@Autowired
private ResponseUtil responseUtil;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    BaseResponse baseResponse = new BaseResponse();

    @Override
    public BaseResponse registerStudent(Student student) {
        try{
            int result = studentDao.registerStudent(student);

            if(result < 1){
                baseResponse.setResponseCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
                baseResponse.setResponseMessage("Error processing request");
                return baseResponse;
            }
            baseResponse.setResponseCode("00");
            baseResponse.setResponseMessage("Successful");
            return baseResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error processing the request");
        }
    }

    @Override
    public BaseResponse updateStudent(Student student, Long id){
         try{
             Student existingStudent = findStudentById(id);

             int result = studentDao.updateStudent(student, id);
             if(result < 1){
                 baseResponse.setResponseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
                 baseResponse.setResponseMessage("Error processing request");
                 return baseResponse;
             }
             baseResponse.setResponseCode("00");
             baseResponse.setResponseMessage("Successful");
             baseResponse.setData(student);
             return baseResponse;
         }
         catch (StudentNotFoundException ex) {
             BaseResponse response = responseUtil.returnNotFoundResponse();
             //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
             return new BaseResponse();
         }
         catch (Exception e){
             e.printStackTrace();
             throw new RuntimeException("There was an error processing the request");
         }
    }


    @Override
    public Student findStudentById(Long id) throws StudentNotFoundException {
        try {
            return studentDao.findStudentById(id);
        } catch(EmptyResultDataAccessException e){
            e.printStackTrace();
            throw new StudentNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public Student findStudentByRegistrationNumber(String regNum) {
        return null;
    }

    @Override
    public Student findStudentByDepartment(Long id) {
        return null;
    }

//    @Override
//    public List<BaseResponse> retrieveStudents(PagingRequest request){
//        return studentDao.findAll(request);
//    }

    public Student registerStudent(String firstName, String lastName) {
        return null;
    }

}
