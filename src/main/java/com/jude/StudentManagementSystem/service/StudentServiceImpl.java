package com.jude.StudentManagementSystem.service;

import com.jude.StudentManagementSystem.dao.StudentDao;
import com.jude.StudentManagementSystem.exception.RequestException;
import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.model.response.PagingResponse;
import com.jude.StudentManagementSystem.model.response.ResponseCodes;
import com.jude.StudentManagementSystem.service.contract.student.StudentService;
import com.jude.StudentManagementSystem.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        try {
            int result = studentDao.registerStudent(student);

            if (result < 1) {
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
    public ResponseEntity<?> updateStudent(Student student, Long id) {
        try {
            BaseResponse studentExist = findStudentById(id);
            if (studentExist.getData() != null && !StringUtils.isEmpty(studentExist.getData())) {
                int result = studentDao.updateStudent(student, id);
                if (result < 1) {
                    baseResponse.setResponseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
                    baseResponse.setResponseMessage("Error processing request");
                    baseResponse.setData(null);
                    return ResponseEntity.internalServerError().body(baseResponse);
                }
                baseResponse.setResponseCode("00");
                baseResponse.setResponseMessage("Successful");
                baseResponse.setData(student);
                return ResponseEntity.ok(baseResponse);
            }
            BaseResponse response = responseUtil.returnNotFoundResponse(studentExist.getResponseMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (RequestException ex) {
            BaseResponse response = responseUtil.returnNotFoundResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @Override
    public BaseResponse findStudentById(Long id) throws RequestException {
        try {
            Student result = studentDao.findStudentById(id);
            baseResponse.setResponseCode("00");
            baseResponse.setResponseMessage("Successful");
            baseResponse.setData(result);
            return baseResponse;
        } catch (RequestException e) {
            BaseResponse response = responseUtil.returnNotFoundResponse(e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            return response;
        }
    }

    @Override
    public BaseResponse findStudentByRegistrationNumber(String regNum) {
        try {
            Student result = studentDao.findStudentByRegistrationNumber(regNum);
            baseResponse.setResponseCode("00");
            baseResponse.setResponseMessage("Successful");
            baseResponse.setData(result);
            return baseResponse;
        } catch (RequestException e) {
            BaseResponse response = responseUtil.returnNotFoundResponse(e.getMessage());
            return response;
        }
    }

    @Override
    public BaseResponse findStudentByDepartment(Long id) {
        try {
            Student result = studentDao.findStudentByDepartment(id);
            baseResponse.setResponseCode("00");
            baseResponse.setResponseMessage("Successful");
            baseResponse.setData(result);
            return baseResponse;
        } catch (RequestException e) {
            BaseResponse response = responseUtil.returnNotFoundResponse(e.getMessage());
            return response;
        }
    }

    @Override
    public PagingResponse<Student> retrieveStudents(PagingRequest request) {
        PagingResponse<Student> response = new PagingResponse<>();
        //PagingResponse<Student> students = null;

        response = studentDao.retrieveStudents(request);
        if (response != null) {
            response.setResponseCode(ResponseCodes.SUCCESS.getCode());
            response.setResponseMessage(ResponseCodes.SUCCESS.getMessage());
        }
        return response;
    }

//    public Student registerStudent(String firstName, String lastName) {
//        return null;
//    }

}
