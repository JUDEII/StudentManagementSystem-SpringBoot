package com.jude.StudentManagementSystem.utils;

import com.jude.StudentManagementSystem.model.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {


    public BaseResponse returnNotFoundResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode("104");
        baseResponse.setResponseMessage("Record Not Found");
        return baseResponse;
    }
}
