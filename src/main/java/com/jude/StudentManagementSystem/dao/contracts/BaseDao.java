package com.jude.StudentManagementSystem.dao.contracts;

import com.jude.StudentManagementSystem.model.page.Page;
import com.jude.StudentManagementSystem.model.response.BaseResponse;

import java.util.List;

public interface BaseDao<T> {
    public T create(T Model);

    public void update(T model);

    public T find(long id);

    public List<T> findAll();

    public Page<T> findAll(int pageNumber, int pageSize);

    public boolean delete(T model);


}
