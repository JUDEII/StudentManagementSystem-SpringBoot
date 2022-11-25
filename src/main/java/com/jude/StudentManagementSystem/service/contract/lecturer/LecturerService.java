package com.jude.StudentManagementSystem.service.contract.lecturer;

import com.jude.StudentManagementSystem.model.Lecturer;
import com.jude.StudentManagementSystem.model.page.Page;
import com.jude.StudentManagementSystem.model.response.BaseResponse;

public interface LecturerService {

    BaseResponse create(Lecturer lecturer);
    BaseResponse update(Lecturer lecturer);
    Lecturer find(Long id);
    Page<Lecturer> findAll(Integer pageNumber, Integer pageSize);
    BaseResponse delete (Long id);

}
