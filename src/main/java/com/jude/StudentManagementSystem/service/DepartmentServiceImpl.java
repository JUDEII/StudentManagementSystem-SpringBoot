package com.jude.StudentManagementSystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jude.StudentManagementSystem.domain.Department;
import com.jude.StudentManagementSystem.exception.RequestException;
import com.jude.StudentManagementSystem.model.DepartmentEntity;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.service.contract.department.DepartmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl{
    private final DepartmentRepository departmentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ObjectMapper objectMapper){
        this.departmentRepository = departmentRepository;
        this.objectMapper = objectMapper;
    }

    BaseResponse baseResponse = new BaseResponse();

    public BaseResponse create(Department department) {
        DepartmentEntity departmentEntity = findByName(department.getName());
        if(departmentEntity != null){
            throw new DuplicateKeyException(String.format("Department with name %s already exists", department.getName()));
        }
        departmentEntity = this.objectMapper.convertValue(department, DepartmentEntity.class);
        departmentEntity = departmentRepository.save(departmentEntity);
        baseResponse.setResponseCode("00");
        baseResponse.setResponseMessage("Successful");
        baseResponse.setData(departmentEntity);
        return baseResponse;
    }


    public BaseResponse update(Long id, Department department){
        DepartmentEntity departmentEntity = findById(id);
        if(departmentEntity != null){
            departmentEntity.setId(id);
            departmentEntity.setName(department.getName());
            departmentEntity = departmentRepository.save(departmentEntity);
            Department departmentResponse = this.objectMapper.convertValue(departmentEntity, Department.class);
            baseResponse.setResponseCode("00");
            baseResponse.setResponseMessage("Successful");
            baseResponse.setData(departmentResponse);
            return baseResponse;
        }
        throw new RequestException("Department not found");
    }


    public DepartmentEntity findById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public Department findDepartmentById(Long id) {
        DepartmentEntity departmentEntityResponse = findById(id);
        if (departmentEntityResponse == null){
            throw new RequestException("Department not found");
        }
        Department department = this.objectMapper.convertValue(departmentEntityResponse, Department.class);
        return department;
    }

    public DepartmentEntity findByName(String name){
        return departmentRepository.findByName(name).orElse(null);
    }

    public Department findDepartmentByName(String name){
        DepartmentEntity departmentEntityResponse = findByName(name);
        if(departmentEntityResponse == null){
            throw new RequestException("Department with name " + name + " not found");
        }
        Department department = this.objectMapper.convertValue(departmentEntityResponse, Department.class);
        return department;
    }

    public BaseResponse delete(Long id){
        DepartmentEntity departmentEntity = findById(id);
        if (departmentEntity == null){
            throw new RequestException("Department not found");
        }
        departmentRepository.deleteById(departmentEntity.getId());
        baseResponse.setResponseCode("00");
        baseResponse.setResponseMessage("Successful");
        return baseResponse;
    }

}
