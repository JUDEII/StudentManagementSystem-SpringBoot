package com.jude.StudentManagementSystem.dao;

import com.jude.StudentManagementSystem.exception.NotFoundException;
import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import com.jude.StudentManagementSystem.model.response.PagingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//@Repository reference an interaction with the data source (DB)
@Repository
public class StudentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int registerStudent(Student student) {
        String sql = "INSERT INTO dbo.students(first_name, last_name, date_of_birth, sex, department_id, registration_number)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
//        Object[] objectArgs = {student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getSex(), student.getDepartmentId(), student.getRegistrationNumber()};
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getSex(), student.getDepartmentId(), student.getRegistrationNumber());
    }

    public PagingResponse<Student> retrieveStudents(PagingRequest request) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);

            String sql = "SELECT * FROM dbo.students WHERE created_on >= ? AND created_on <= ?";
//            String sql = "SELECT * FROM dbo.students";
            //return jdbcTemplate.queryForObject(sql, new Object[]{request.getStartDate(), request.getEndDate()}, new PagingResponseStudentMapper());
            List<Student> response = jdbcTemplate.query(sql, new StudentMapper(), new Object[]{startDate, endDate});
            PagingResponse<Student> studentResponse = new PagingResponse<>();
            studentResponse.setData(response);
            studentResponse.setCount(response.size());
            return studentResponse;

    }


    public int updateStudent(Student student, Long id) {
        String sql = "UPDATE dbo.students SET first_name = ?, last_name = ?, date_of_birth = ?, sex = ?, department_id = ?, registration_number = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getSex(), student.getDepartmentId(), student.getRegistrationNumber(), id);
    }

    public Student findStudentById(Long id) throws NotFoundException {
        try {
            String sql = "SELECT * FROM dbo.students WHERE id = ? ";

            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
            //return jdbcTemplate.queryForObject(sql, new StudentMapper());
        }
        catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            throw new NotFoundException("Student with id not found");
        }
    }

    private class PagingResponseStudentMapper implements RowMapper<PagingResponse<Student>> {

        @Override
        public PagingResponse<Student> mapRow(ResultSet rs, int rowNum) throws SQLException {
            PagingResponse<Student> students = new PagingResponse<Student>();
            students.setData(rs.getString("first_name"));
            return students;
        }
    }

    private class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setDateOfBirth(rs.getString("date_of_birth"));
            student.setSex(rs.getString("sex"));
            student.setDepartmentId(rs.getLong("department_id"));
            student.setRegistrationNumber(rs.getString("registration_number"));
            student.setCreatedOn(rs.getObject("created_on", LocalDateTime.class));
            return student;
        }
    }



}
