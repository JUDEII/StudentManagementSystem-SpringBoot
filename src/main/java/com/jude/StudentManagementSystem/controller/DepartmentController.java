package com.jude.StudentManagementSystem.controller;

import com.jude.StudentManagementSystem.domain.Department;
import com.jude.StudentManagementSystem.model.DepartmentEntity;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private final DepartmentServiceImpl departmentServiceImpl;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentServiceImpl) {
        this.departmentServiceImpl = departmentServiceImpl;
    }

    @PostMapping("/create")
    public BaseResponse create(@RequestBody Department department){
        return departmentServiceImpl.create(department);
    }

    @PutMapping("/update/{id}")
    public BaseResponse update(@PathVariable Long id, @RequestBody Department department){
        return departmentServiceImpl.update(id, department);
    }

    @GetMapping("/{id}")
    public Department find(@PathVariable Long id) {
        return departmentServiceImpl.findDepartmentById(id);
    }

    @GetMapping("/name/{name}")
    public Department findByName(@PathVariable String name){
        return departmentServiceImpl.findDepartmentByName(name);
    }

    @DeleteMapping("/{id}")
    public BaseResponse delete(@PathVariable Long id){
        return departmentServiceImpl.delete(id);
    }
}
