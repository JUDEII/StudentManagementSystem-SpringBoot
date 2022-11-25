package com.jude.StudentManagementSystem.dao;

import com.jude.StudentManagementSystem.exception.RequestException;
import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.PagingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

            String sql = "SELECT * FROM dbo.students WHERE created_on >= ? AND created_on <= ? ORDER BY id DESC OFFSET ";
//            String sql = "SELECT * FROM dbo.students";
            //return jdbcTemplate.queryForObject(sql, new Object[]{request.getStartDate(), request.getEndDate()}, new PagingResponseStudentMapper());
            List<Student> response = jdbcTemplate.query(sql, new StudentMapper(), new Object[]{startDate, endDate});
            PagingResponse<Student> studentResponse = new PagingResponse<>();
            studentResponse.setCount(response.size());
            studentResponse.setData(response);
            return studentResponse;
    }


    public int updateStudent(Student student, Long id) {
        String sql = "UPDATE dbo.students SET first_name = ?, last_name = ?, date_of_birth = ?, sex = ?, department_id = ?, registration_number = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getSex(), student.getDepartmentId(), student.getRegistrationNumber(), id);
    }


    public Student findStudentById(Long id) throws RequestException {
        try {
            String sql = "SELECT * FROM dbo.students WHERE id = ? ";
            return jdbcTemplate.queryForObject(sql, new StudentMapper(), new Object[]{id});
        }
        catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            throw new RequestException("Student with id not found");
        }
    }


    public Student findStudentByRegistrationNumber(String regNum) throws RequestException {
        try {
            String sql = "SELECT * FROM dbo.students WHERE registration_number = ?";
            return jdbcTemplate.queryForObject(sql, new StudentMapper(), new Object[]{regNum});
        }
        catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new RequestException("Student with registration number was not found.");
        }
    }


    public Student findStudentByDepartment(Long id) throws RequestException {
        try {
            String sql = "SELECT * FROM dbo.students WHERE department_id = ?";
            return jdbcTemplate.queryForObject(sql, new StudentMapper(), new Object[]{id});
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new RequestException("Student with department id not found");
        }
    }


//    private class PagingResponseStudentMapper implements RowMapper<PagingResponse<Student>> {
//
//        @Override
//        public PagingResponse<Student> mapRow(ResultSet rs, int rowNum) throws SQLException {
//            PagingResponse<Student> students = new PagingResponse<Student>();
//            students.setData(rs.getString("first_name"));
//            return students;
//        }
//    }

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
