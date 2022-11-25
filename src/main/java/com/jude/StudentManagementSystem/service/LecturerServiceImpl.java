package com.jude.StudentManagementSystem.service;

import com.jude.StudentManagementSystem.dao.LecturerDaoImpl;
import com.jude.StudentManagementSystem.model.Lecturer;
import com.jude.StudentManagementSystem.model.page.Page;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.service.contract.lecturer.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerServiceImpl implements LecturerService {
    private LecturerDaoImpl lecturerDao;

    @Autowired
    public LecturerServiceImpl(LecturerDaoImpl lecturerDao){
        this.lecturerDao = lecturerDao;
    }

    @Override
    public BaseResponse create(Lecturer lecturer) {
        return null;
    }

    @Override
    public BaseResponse update(Lecturer lecturer) {
        return null;
    }

    @Override
    public Lecturer find(Long id) {
        return null;
    }

    public Lecturer findV2(Long id) {
        return lecturerDao.find(id);
    }

    @Override
    public Page<Lecturer> findAll(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public BaseResponse delete(Long id) {
        return null;
    }
}
