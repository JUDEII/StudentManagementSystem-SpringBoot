package com.jude.StudentManagementSystem.dao;

import com.jude.StudentManagementSystem.dao.contracts.LecturerDao;
import com.jude.StudentManagementSystem.dao.util.RowCountMapper;
import com.jude.StudentManagementSystem.model.Lecturer;
import com.jude.StudentManagementSystem.model.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository //@Repository tell us this class handles the database interractions
public class LecturerDaoImpl implements LecturerDao {
    protected SimpleJdbcCall find, create, update, findAll, delete;
    protected JdbcTemplate jdbcTemplate;

    protected final String SINGLE_RESULT = "object";
    protected final String MULTIPLE_RESULT = "list";
    protected static final String RESULT_COUNT = "count";

    @Autowired
    public void setDataSource(@Qualifier("datasource") DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("").withReturnValue();
        update = new SimpleJdbcCall(dataSource).withProcedureName("").withReturnValue();
    }

    @Autowired
    public void setReadOnlyDataSource(@Qualifier(value = "readOnlyDataSource") DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("").returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Lecturer.class));
        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("").returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Lecturer.class));
    }

    @Override
    public Lecturer create(Lecturer model) throws DataAccessException {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        Map<String, Object> m = create.execute(in);
        if(m.isEmpty() || m == null){ return null; }
        long id = (long) m.get("id");
        model.setId(id);
        return model;
    }

    @Override
    public void update(Lecturer model) throws DataAccessException {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        update.execute(in);
    }

    @Override
    public Lecturer find(long id) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
        Map<String, Object> m = find.execute(in);
        List<Lecturer> list = (List<Lecturer>) m.get(SINGLE_RESULT);
        if(list.isEmpty() || list == null){return null;}
        return list.get(0);
    }

    @Override
    public List<Lecturer> findAll() {
        return null;
    }

    @Override
    public Page<Lecturer> findAll(int pageNumber, int pageSize) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("pageNumber", pageNumber).addValue("pageSize", pageSize);
        Map<String, Object> m =findAll.execute(in);
        List<Lecturer> content = (List<Lecturer>) m.get(MULTIPLE_RESULT);
        List<Long> countList = (List<Long>) m.get(RESULT_COUNT);

        long count = 0;
        if(Objects.nonNull(countList) && !countList.isEmpty()){
            count = countList.get(0);
        }
        Page<Lecturer> page = new Page<>(count, content);
        return page;
    }

    @Override
    public boolean delete(Lecturer model) {
        return false;
    }
}
