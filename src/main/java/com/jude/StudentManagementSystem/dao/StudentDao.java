package com.jude.StudentManagementSystem.dao;

import com.jude.StudentManagementSystem.model.Student;
import com.jude.StudentManagementSystem.model.request.PagingRequest;
import com.jude.StudentManagementSystem.model.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

//    public int retrieveStudents(PagingRequest request){
//        String sql = "SELECT * FROM dbo.students WHERE created_on >= (?) AND created_on <= (?)";
//        return jdbcTemplate.
//    }

    public int updateStudent(Student student, Long id) {
        String sql = "UPDATE dbo.students SET first_name = ?, last_name = ?, date_of_birth = ?, sex = ?, department_id = ?, registration_number = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getSex(), student.getDepartmentId(), student.getRegistrationNumber(), id);
    }

    public Student findStudentById(Long id) {
        String sql = "SELECT * FROM dbo.students WHERE id = ? ";

            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
            //return jdbcTemplate.queryForObject(sql, new StudentMapper());
    }



    private class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
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
