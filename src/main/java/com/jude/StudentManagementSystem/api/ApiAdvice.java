package com.jude.StudentManagementSystem.api;

import com.jude.StudentManagementSystem.exception.RequestException;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.model.response.ResponseCodes;
import org.hibernate.internal.util.xml.ErrorLogger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ApiAdvice {

    @ExceptionHandler({RequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleRequestException(RequestException e) {
        BaseResponse response = new BaseResponse();
//        String responseCode = StringUtils.hasText(e.getCode()) ? e.getCode() : String.valueOf(HttpStatus.BAD_REQUEST.value());
        String responseCode = StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.RECORD_NOT_FOUND.getCode();
        response.setResponseCode(responseCode);
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleSQLException(SQLException e) {
        BaseResponse response = new BaseResponse();
        String responseCode = ResponseCodes.SYSTEM_ERROR.getCode();
        response.setResponseCode(responseCode);
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public BaseResponse handleDuplicateKeyException(DuplicateKeyException e) {
        BaseResponse response = new BaseResponse();
        String responseCode = ResponseCodes.DUPLICATE_RECORD.getCode();
        response.setResponseCode(responseCode);
        response.setResponseMessage(e.getMessage());
        return response;
    }

//    @ExceptionHandler({DuplicateKeyException.class})
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public BaseResponse handleDuplicateKeyException(DuplicateKeyException e) {
//        BaseResponse response = new BaseResponse();
//        response.setResponseCode(ResponseCodes.DUPLICATE_RECORD.getCode());
//        if (e.getRootCause() != null) {
//            String message = e.getRootCause().getMessage();
//            response.setResponseMessage("Duplicate Exception. " + message.substring(message.indexOf("The")));
//        } else{
//            String message = e.getMostSpecificCause().getMessage();
//            response.setResponseMessage("Duplicate Exception. " + message.substring(message.indexOf("the")));
//        }
//        ErrorLogger.log(e);
//        return response;
//    }



}
